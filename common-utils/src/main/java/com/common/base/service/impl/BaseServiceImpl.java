package com.common.base.service.impl;

import com.common.base.dao.BaseDao;
import com.common.base.model.BaseDto;
import com.common.base.model.BaseModel;
import com.common.base.service.BaseService;
import com.common.utils.model.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseDto, M extends BaseDao> implements BaseService<T> {

    @Autowired
    protected M mapper;

    private Type model;

    protected void currentModleClass() {
        if (null == model) {
            this.model = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }

    @Override
    public List<T> list() {
        List<? extends BaseModel> list = mapper.selectAll();
        currentModleClass();
        return CopyUtils.copyList(list, (Class<T>) model);
    }

    @Override
    public void save(T dto) {
        currentModleClass();
        mapper.insert(CopyUtils.copyObject(dto, (Class<T>) model));
    }

    @Override
    public void update(T dto) {
        if (null != dto.getPrimaryKey()) {
            currentModleClass();
            mapper.updateByPrimaryKey(CopyUtils.copyObject(dto, (Class<T>) model));
        }
    }

    @Override
    public void delete(T dto) {
        currentModleClass();
        mapper.deleteByPrimaryKey(CopyUtils.copyObject(dto, (Class<T>) model));
    }

}
