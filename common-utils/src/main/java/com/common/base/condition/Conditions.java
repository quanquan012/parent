package com.common.base.condition;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 查询条件
 *
 * @author: li.hao
 * @date: 2018/11/12 11:25
 */
@Data
public class Conditions {

    private List<SearchFilter> searchFilters;

    private List<Order> orders;

    public Conditions() {
        this.searchFilters = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    /**
     * 添加排序
     *
     * @param orders
     */
    public void addOrders(Order... orders) {
        Collections.addAll(this.orders, orders);
    }

    /**
     * 添加查询条件
     *
     * @param filters
     */
    public void addSearchFilters(SearchFilter... filters) {
        Collections.addAll(this.searchFilters, filters);
    }

}
