package com.pc.controller;

import com.common.base.condition.Conditions;
import com.common.base.condition.SearchFilter;
import com.common.base.controller.BaseController;
import com.common.web.Message;
import com.pc.model.ao.AccountAuthAo;
import com.pc.model.ao.AuthAo;
import com.pc.model.consts.AccountConsts;
import com.pc.model.consts.AuthConsts;
import com.pc.model.dto.AccountAuthDto;
import com.pc.model.dto.AuthDto;
import com.pc.model.vo.AccountAuthVo;
import com.pc.service.AccountAuthService;
import com.pc.service.AuthNavigatorService;
import com.pc.service.AuthService;
import com.pc.utils.SearchFilterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihao
 * @date 2018-11-22 13:24
 */
@RestController
@RequestMapping("/auths")
public class AuthController extends BaseController<AuthAo, AuthDto, AuthService> {

    @Autowired
    private AccountAuthService accountAuthService;

    @Autowired
    private AuthNavigatorService authNavigatorService;

    /**
     * 查询账户权限-穿梭框
     *
     * @param accountCode
     * @return
     */
    @GetMapping(params = "type=byAccountCode")
    public Message listByAccountId(@RequestParam String accountCode) {
        Conditions conditions = new Conditions();
        conditions.addSearchFilters(SearchFilter.eq(AccountConsts.ACCOUNT_CODE, accountCode));
        /* 查询账户关联权限编码 */
        List<AccountAuthDto> rightAccountAuths = accountAuthService.selectList(conditions);
        conditions.getSearchFilters().clear();
        conditions.addSearchFilters(SearchFilter.eq(AuthConsts.DATA_STATUS, Boolean.TRUE), SearchFilter.in(AuthConsts.AUTH_CODE, listCodes(rightAccountAuths)));
        /* 查询账户关联权限 */
        List<AuthDto> rightAuths = service.selectList(conditions);
        conditions.getSearchFilters().clear();
        conditions.addSearchFilters(SearchFilter.eq(AuthConsts.DATA_STATUS, Boolean.TRUE));
        /* 查询全部权限 */
        List<AuthDto> leftAuths = service.selectList(conditions);
        for (AuthDto dto : leftAuths) {
            dto.setDisable(!dto.getDisable());
        }
        AccountAuthVo vo = new AccountAuthVo();
        vo.setLeft(leftAuths);
        vo.setRight(listIds(rightAuths));
        return successMessage(vo);
    }

    @PutMapping(value = "/accountAuths")
    public Message updateAccountAuth(@RequestBody AccountAuthAo accountAuthAo) {
        String accountCode = accountAuthAo.getAccountCode();
        Conditions conditions = new Conditions();
        conditions.addSearchFilters(SearchFilter.eq(AccountConsts.ACCOUNT_CODE, accountCode));
        accountAuthService.deleteByConditions(conditions);
        List<Long> right = accountAuthAo.getRight();
        int result = 0;
        if (right.size() > 0) {
            List<AuthDto> left = accountAuthAo.getLeft();
            result = accountAuthService.insertList(transfer(left, right, accountCode));
        }
        return Message.success("result: " + result);
    }

    /**
     * 分页添加数据日期GT(>)条件
     *
     * @return 分页条件
     */
    @Override
    protected Conditions pageConditions(AuthAo merchantAo) {
        return SearchFilterUtils.pageConditionWithDateGt(merchantAo);
    }

    /**
     * 删除权限关联的账户
     *
     * @param id
     * @return
     */
    @Override
    protected int deleteByPrimaryKey(Long id) {
        AuthDto dto = service.selectByPrimaryKey(id);
        accountAuthService.deleteByAuthCode(dto.getAuthCode());
        authNavigatorService.deleteByAuthCode(dto.getAuthCode());
        return service.deleteByPrimaryKey(id);
    }

    /**
     * 集合转换
     *
     * @param sourceList
     * @param right
     * @param accountCode
     * @return
     */
    private List<AccountAuthDto> transfer(List<AuthDto> sourceList, List<Long> right, String accountCode) {
        List<AccountAuthDto> target = new ArrayList<>(sourceList.size());
        for (AuthDto vo : sourceList) {
            for (Long id : right) {
                if (id.equals(vo.getPrimaryKey())) {
                    AccountAuthDto dto = new AccountAuthDto();
                    dto.setAccountCode(accountCode);
                    dto.setAuthCode(vo.getAuthCode());
                    dto.setOpenId(vo.getOpenId());
                    target.add(dto);
                }
            }
        }
        return target;
    }

    /**
     * 获取权限编码集合
     *
     * @param sourceList
     * @return
     */
    private List<String> listCodes(List<AccountAuthDto> sourceList) {
        List<String> codes = new ArrayList<>();
        for (AccountAuthDto dto : sourceList) {
            codes.add(dto.getAuthCode());
        }
        return codes;
    }

    /**
     * 获取权限ID集合
     *
     * @param sourceList
     * @return
     */
    private List<Long> listIds(List<AuthDto> sourceList) {
        List<Long> target = new ArrayList<>();
        for (AuthDto dto : sourceList) {
            target.add(dto.getPrimaryKey());
        }
        return target;
    }
}