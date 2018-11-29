package com.pc.controller;

import com.common.base.condition.Conditions;
import com.common.base.condition.SearchFilter;
import com.common.base.controller.BaseController;
import com.pc.model.ao.AccountAo;
import com.pc.model.consts.AccountConsts;
import com.pc.model.dto.AccountDto;
import com.pc.service.AccountAuthService;
import com.pc.service.AccountService;
import com.pc.utils.SearchFilterUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseController<AccountAo, AccountDto, AccountService> {
    @Autowired
    private AccountAuthService accountAuthService;

    /**
     * 分页添加数据日期GT(>)条件
     *
     * @return 分页条件
     */
    @Override
    protected Conditions pageConditions(AccountAo accountAo) {
        Conditions conditions = SearchFilterUtils.pageConditionWithDateGt(accountAo);
        String accountName = accountAo.getAccountName();
        if (StringUtils.isNotBlank(accountName)) {
            conditions.addSearchFilters(SearchFilter.like(AccountConsts.ACCOUNT_NAME, ("%"+accountName+"%")));
        }
        String accountPhone = accountAo.getAccountPhone();
        if (StringUtils.isNotBlank(accountPhone)) {
            conditions.addSearchFilters(SearchFilter.like(AccountConsts.ACCOUNT_PHONE, ("%"+accountPhone+"%")));
        }
        return conditions;
    }

    /**
     * 删除账户关联的权限
     *
     * @param id
     * @return
     */
    @Override
    protected int deleteByPrimaryKey(Long id) {
        AccountDto dto = service.selectByPrimaryKey(id);
        accountAuthService.deleteByAccountCode(dto.getAccountCode());
        return service.deleteByPrimaryKey(id);
    }
}
