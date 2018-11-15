package com.pc.controller;

import com.common.base.condition.Conditions;
import com.common.base.condition.Order;
import com.common.base.condition.SearchFilter;
import com.common.base.controller.BaseController;
import com.common.base.model.BaseAo;
import com.pc.model.ao.AccountAo;
import com.pc.model.dto.AccountDto;
import com.pc.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseController<AccountAo, AccountDto, AccountService> {

    /**
     * 分页添加数据日期GT(>)条件
     *
     * @return 分页条件
     */
    @Override
    protected Conditions pageConditions(AccountAo accountAo){
        Conditions conditions = new Conditions();
        conditions.addOrders(Order.desc(BaseAo.CREATE_TIME));
        conditions.setPageNum(accountAo.getPageNum());
        conditions.setPageSize(accountAo.getPageSize());
        if(null != accountAo.getCreateTime()){
            conditions.addSearchFilters(SearchFilter.gt(BaseAo.CREATE_TIME, accountAo.getCreateTime()));
        }
        return conditions;
    }
}
