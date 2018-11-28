package com.common.utils.conditions;

import com.common.base.condition.Conditions;
import com.common.base.condition.Order;
import com.common.base.condition.SearchFilter;
import com.common.base.enums.Operator;
import com.common.base.exception.DaoException;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author lihao
 * @date 2018-11-23 11:49
 */
public class ConditionsUtils {

    public static Example getExampleByConditions(Conditions conditions, Class modelClazz) {
        Example example = new Example(modelClazz);
        if (null != conditions) {
            if (!conditions.getSearchFilters().isEmpty()) {
                setSearchFilters(example, conditions.getSearchFilters());
            }
            if (!conditions.getOrders().isEmpty()) {
                setOrders(example, conditions.getOrders());
            }
        }
        return example;
    }

    /**
     * 设置排序
     *
     * @param example
     * @param orders  排序列表
     */
    public static void setOrders(Example example, List<Order> orders) {
        if (example == null) {
            throw new DaoException("setOrders:example must not be empty");
        }
        if (orders == null) {
            throw new DaoException("setOrders:orders must not be empty");
        }
        Example.OrderBy ob = null;
        for (Order order : orders) {
            if (ob == null) {
                ob = example.orderBy(order.getProperty());
            } else {
                ob.orderBy(order.getProperty());
            }

            switch (order.getDirection()) {
                case asc:
                    ob.asc();
                    break;
                case desc:
                    ob.desc();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置过滤条件
     *
     * @param example
     * @param searchFilters 过滤列表
     */
    public static void setSearchFilters(Example example, List<SearchFilter> searchFilters) {
        if (example == null) {
            throw new DaoException("setOrders:example must not be empty");
        }
        if (CollectionUtils.isEmpty(searchFilters)) {
            throw new DaoException("setOrders:searchFilters must not be empty或空集合");
        }
        Example.Criteria createCriteria = example.createCriteria();
        for (SearchFilter searchFilter : searchFilters) {
            String property = searchFilter.getProperty();
            Object value = searchFilter.getValue();
            // 目前忽略大小写这个功能暂时没有方法去实现
            Operator operator = searchFilter.getOperator();
            switch (operator) {
                case EQ:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andEqualTo(property, value);
                    break;
                case NE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andNotEqualTo(property, value);
                    break;
                case GT:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andGreaterThan(property, value);
                    break;
                case GE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andGreaterThanOrEqualTo(property, value);
                    break;
                case LT:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andLessThan(property, value);
                    break;
                case LE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andLessThanOrEqualTo(property, value);
                    break;
                case LIKE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andLike(property, (String) value);
                    break;
                case NOTLIKE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andNotLike(property, (String) value);
                    break;
                case IN:
                    if (value instanceof List) {
                        List list = (List) value;
                        if (CollectionUtils.isEmpty(list)) {
                            createCriteria.andIsNull(property);
                            break;
                        }
                    }
                    createCriteria.andIn(property, (Iterable) value);
                    break;
                case NOTIN:
                    if (value instanceof List) {
                        List list = (List) value;
                        if (CollectionUtils.isEmpty(list)) {
                            createCriteria.andIsNull(property);
                            break;
                        }
                    }
                    createCriteria.andNotIn(property, (Iterable) value);
                    break;
                case ISNULL:
                    createCriteria.andIsNull(property);
                    break;
                case ISNOTNULL:
                    createCriteria.andIsNotNull(property);
                    break;
                case BT:
                    createCriteria.andBetween(property, ((List<Object>) value).get(0), ((List<Object>) value).get(1));
                    break;
                case NOTBT:
                    createCriteria.andNotBetween(property, ((List<Object>) value).get(0), ((List<Object>) value).get(1));
                    break;
                default:
                    break;
            }
        }
    }

}