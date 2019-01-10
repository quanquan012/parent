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
import com.common.utils.conditions.ConditionsUtils;
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
        currentModelClass();
        Example example = ConditionsUtils.getExampleByConditions(conditions, modelClazz);
        PageInfo<T> pageInfo = PageHelper.startPage(conditions.getPageNum(), conditions.getPageSize()).doSelectPageInfo(() -> mapper.selectByExample(example));
        return pageInfo;
    }

    @Override
    public List<T> selectAll() {
        List<? extends BaseModel> list = mapper.selectAll();
        currentModelClass();
        return CopyUtils.copyList(list, dtoClazz);
    }

    @Override
    public T selectByPrimaryKey(Long id) {
        currentModelClass();
        return CopyUtils.copyObject(mapper.selectByPrimaryKey(id), dtoClazz);
    }

    @Override
    public List<T> selectList(Conditions conditions) {
        currentModelClass();
        List<? extends BaseModel> list = mapper.selectByExample(ConditionsUtils.getExampleByConditions(conditions, modelClazz));
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
    public int insertList(List<T> list) {
        currentModelClass();
        int result = mapper.insertList(CopyUtils.copyList(list, modelClazz));
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public T update(T dto, Conditions conditions) {
        currentModelClass();
        int result = mapper.updateByExample(CopyUtils.copyObject(dto, modelClazz), ConditionsUtils.getExampleByConditions(conditions, modelClazz));
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
    public int deleteByConditions(Conditions conditions) {
        currentModelClass();
        int result = mapper.deleteByExample(ConditionsUtils.getExampleByConditions(conditions, modelClazz));
        return result;
    }
}
