package com.pc.controller;

import com.common.base.condition.Conditions;
import com.common.base.condition.Order;
import com.common.base.condition.SearchFilter;
import com.common.base.controller.BaseController;
import com.common.utils.redis.RedisUtil;
import com.common.web.Message;
import com.pc.model.ao.AuthNavigatorAo;
import com.pc.model.ao.NavigatorAo;
import com.pc.model.consts.AuthConsts;
import com.pc.model.consts.LoginConsts;
import com.pc.model.consts.NavigatorConsts;
import com.pc.model.dto.AccountAuthDto;
import com.pc.model.dto.AccountDto;
import com.pc.model.dto.AuthNavigatorDto;
import com.pc.model.dto.NavigatorDto;
import com.pc.model.vo.AuthNavigatorVo;
import com.pc.service.AccountAuthService;
import com.pc.service.AuthNavigatorService;
import com.pc.service.NavigatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 导航
 *
 * @author li.hao
 * @date 2018/11/14 16:56
 */
@RestController
@RequestMapping("/navigators")
public class NavigatorController extends BaseController<NavigatorAo, NavigatorDto, NavigatorService> {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AuthNavigatorService authNavigatorService;

    @Autowired
    private AccountAuthService accountAuthService;

    private List<NavigatorDto> child(List<NavigatorDto> roots, List<NavigatorDto> list) {
        for (NavigatorDto dto : roots) {
            Iterator<NavigatorDto> iterator = list.iterator();
            List children = dto.getChildren();
            while (iterator.hasNext()) {
                NavigatorDto child = iterator.next();
                if (dto.getNavigatorCode().equals(child.getParentCode())) {
                    children.add(child);
                    iterator.remove();
                }
            }
            child(children, list);
        }

        return roots;
    }

    private List<NavigatorDto> tree(List<NavigatorDto> list) {
        List<NavigatorDto> roots = null;
        if (!list.isEmpty()) {
            roots = new ArrayList<>();
            Iterator<NavigatorDto> iterator = list.iterator();
            while (iterator.hasNext()) {
                NavigatorDto dto = iterator.next();
                if ("0".equals(dto.getParentCode())) {
                    roots.add(dto);
                    iterator.remove();
                }
            }
            roots = child(roots, list);
        }
        return roots;
    }

    /**
     * 根据当前用户权限筛选导航数据
     *
     * @return
     */
    @Override
    protected Conditions searchConditions() {
        Conditions conditions = new Conditions();
        conditions.addOrders(Order.asc(NavigatorAo.PRIORITY));
        return conditions;
    }

    @GetMapping(params = "type=byCurrentAuth")
    public Message listByCurrentAuth(HttpServletRequest request){
        String token = request.getHeader(LoginConsts.TOKEN);
        AccountDto accountDto = (AccountDto)redisUtil.get(token);
        /* 根据当前登陆账户查询权限编码 */
        List<AccountAuthDto> accountAuthDtos = accountAuthService.listAuthCodeByAccountCode(accountDto.getAccountCode());
        List<AuthNavigatorDto> authNavigatorDtos = authNavigatorService.listNavigatorCodeByAuthCodes(listAuthCodes(accountAuthDtos));
        List<NavigatorDto> navigatorDtos = service.listNavigatorsByCodes(listNavigatorCodes(authNavigatorDtos));
        return successMessage(tree(navigatorDtos));
    }

    @GetMapping(params = "type=byAuthCode")
    public Message listByAuthCode(@RequestParam String authCode) {
        Conditions conditions = new Conditions();
        conditions.addSearchFilters(SearchFilter.eq(AuthConsts.AUTH_CODE, authCode));
        /* 查询权限关联导航编码 */
        List<AuthNavigatorDto> rightAuthNavigators = authNavigatorService.selectList(conditions);
        conditions.getSearchFilters().clear();
        conditions.addSearchFilters(SearchFilter.in(NavigatorConsts.NAVIGATOR_CODE, listNavigatorCodes(rightAuthNavigators)));
        /* 查询账户关联权限 */
        List<NavigatorDto> rightNavigators = service.selectList(conditions);
        /* 查询全部导航 */
        List<NavigatorDto> leftAuths = service.selectAll();
        AuthNavigatorVo vo = new AuthNavigatorVo();
        vo.setLeft(leftAuths);
        vo.setRight(listIds(rightNavigators));
        return successMessage(vo);
    }

    @PutMapping(value = "/authNavigators")
    public Message updateAuthNavigators(@RequestBody AuthNavigatorAo authNavigatorAo) {
        String authCode = authNavigatorAo.getAuthCode();
        Conditions conditions = new Conditions();
        conditions.addSearchFilters(SearchFilter.eq(AuthConsts.AUTH_CODE, authCode));
        authNavigatorService.deleteByConditions(conditions);
        List<Long> right = authNavigatorAo.getRight();
        int result = 0;
        if (right.size() > 0) {
            List<NavigatorDto> left = authNavigatorAo.getLeft();
            result = authNavigatorService.insertList(transfer(left, right, authCode));
        }
        return Message.success("result: " + result);
    }

    /**
     * 集合转换
     *
     * @param sourceList
     * @param right
     * @param authCode
     * @return
     */
    private List<AuthNavigatorDto> transfer(List<NavigatorDto> sourceList, List<Long> right, String authCode) {
        List<AuthNavigatorDto> target = new ArrayList<>(sourceList.size());
        for (NavigatorDto vo : sourceList) {
            for (Long id : right) {
                if (id.equals(vo.getPrimaryKey())) {
                    AuthNavigatorDto dto = new AuthNavigatorDto();
                    dto.setAuthCode(authCode);
                    dto.setNavigatorCode(vo.getNavigatorCode());
                    dto.setOpenId(vo.getOpenId());
                    target.add(dto);
                }
            }
        }
        return target;
    }

    private List<String> listAuthCodes(List<AccountAuthDto> sourceList){
        List<String> codes = new ArrayList<>();
        for (AccountAuthDto dto : sourceList) {
            codes.add(dto.getAuthCode());
        }
        return codes;
    }

    /**
     * 获取导航编码集合
     *
     * @param sourceList
     * @return
     */
    private List<String> listNavigatorCodes(List<AuthNavigatorDto> sourceList) {
        List<String> codes = new ArrayList<>();
        for (AuthNavigatorDto dto : sourceList) {
            codes.add(dto.getNavigatorCode());
        }
        return codes;
    }

    /**
     * 获取导航ID集合
     *
     * @param sourceList
     * @return
     */
    private List<Long> listIds(List<NavigatorDto> sourceList) {
        List<Long> target = new ArrayList<>();
        for (NavigatorDto dto : sourceList) {
            target.add(dto.getPrimaryKey());
        }
        return target;
    }

}
