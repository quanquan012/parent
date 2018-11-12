package com.pc.controller;

import com.common.base.condition.Conditions;
import com.common.base.condition.Order;
import com.common.base.condition.SearchFilter;
import com.common.base.controller.BaseController;
import com.common.base.model.BaseAo;
import com.pc.model.ao.MerchantAo;
import com.pc.model.dto.MerchantDto;
import com.pc.service.MerchantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchants")
public class MerchantController extends BaseController<MerchantAo, MerchantDto, MerchantService> {

    /**
     * 分页添加数据日期GT(>)条件
     *
     * @return 分页条件
     */
    @Override
    protected Conditions pageConditions(MerchantAo merchantAo){
        Conditions conditions = new Conditions();
        conditions.addOrders(Order.desc(BaseAo.CREATE_TIME));
        conditions.addSearchFilters(SearchFilter.gt(BaseAo.CREATE_TIME, merchantAo.getCreateTime()));
        return conditions;
    }

}
