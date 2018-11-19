package com.pc.utils;

import com.common.base.condition.Conditions;
import com.common.base.condition.Order;
import com.common.base.condition.SearchFilter;
import com.common.base.model.BaseAo;

import java.util.Date;

/**
 * 搜索条件组装工具类
 *
 * @author li.hao
 * @date 2018/11/16 09:12
 */
public class SearchFilterUtils {

    public static Conditions pageCondition(BaseAo baseAo){
        Conditions conditions = new Conditions();
        conditions.addOrders(Order.desc(BaseAo.CREATE_TIME));
        conditions.setPageNum(baseAo.getPageNum());
        conditions.setPageSize(baseAo.getPageSize());
        return conditions;
    }

    public static Conditions pageConditionWithDateGt(BaseAo baseAo){
        Conditions conditions = new Conditions();
        conditions.addOrders(Order.desc(BaseAo.CREATE_TIME));
        conditions.setPageNum(baseAo.getPageNum());
        conditions.setPageSize(baseAo.getPageSize());
        if(null != baseAo.getCreateTime()){
            conditions.addSearchFilters(SearchFilter.gt(BaseAo.CREATE_TIME, baseAo.getCreateTime()));
        }
        return conditions;
    }

}
