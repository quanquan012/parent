package com.common.base.service.impl;

import com.common.base.condition.Conditions;
import com.common.base.condition.Order;
import com.common.base.condition.SearchFilter;
import com.common.base.dao.BaseDao;
import com.common.base.enums.Operator;
import com.common.base.exception.DaoException;
import com.common.base.exception.ServiceException;
import com.common.base.model.BaseDto;
import com.common.base.model.BaseModel;
import com.common.base.service.BaseService;
import com.common.utils.model.CopyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseDto, D extends BaseModel, M extends BaseDao<D>> implements BaseService<T> {

    @Autowired
    protected M mapper;

    private Class<T> dtoClazz;

    private Class<D> modelClazz;

    protected void currentModelClass() {
        if (null == dtoClazz) {
            this.dtoClazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        if (null == modelClazz) {
            this.modelClazz = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
    }

    @Override
    public PageInfo<T> page(T t, Conditions conditions) {
        PageInfo<T> pageInfo = PageHelper.startPage(conditions.getPageNum(), conditions.getPageSize()).doSelectPageInfo(() -> mapper.selectByExample(getExampleByConditions(conditions)));
        return pageInfo;
    }

    @Override
    public List<T> selectAll() {
        List<? extends BaseModel> list = mapper.selectAll();
        currentModelClass();
        return CopyUtils.copyList(list, dtoClazz);
    }

    @Override
    public List<T> selectList(Conditions conditions) {
        List<? extends BaseModel> list = mapper.selectByExample(getExampleByConditions(conditions));
        return CopyUtils.copyList(list, dtoClazz);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public T insert(T dto) {
        currentModelClass();
        int result = mapper.insertSelective(CopyUtils.copyObject(dto, modelClazz));
        if (result > 0) {
            return CopyUtils.copyObject(mapper.selectByPrimaryKey(result), dtoClazz);
        }
        try {
            return dtoClazz.newInstance();
        } catch (Exception e) {
            throw new ServiceException("dtoClazz newInstance failure");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public T update(T dto, Conditions conditions) {
        int result = mapper.updateByExample(CopyUtils.copyObject(dto, modelClazz), getExampleByConditions(conditions));
        if (result > 0) {
            return CopyUtils.copyObject(mapper.select(CopyUtils.copyObject(dto, modelClazz)), dtoClazz);
        }
        try {
            return dtoClazz.newInstance();
        } catch (Exception e) {
            throw new ServiceException("dtoClazz newInstance failure");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteByConditions(T dto, Conditions conditions) {
        int result = mapper.deleteByExample(getExampleByConditions(conditions));
        return result;
    }

    protected Example getExampleByConditions(Conditions conditions) {
        currentModelClass();
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
    private void setOrders(Example example, List<Order> orders) {
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
    private void setSearchFilters(Example example, List<SearchFilter> searchFilters) {
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
