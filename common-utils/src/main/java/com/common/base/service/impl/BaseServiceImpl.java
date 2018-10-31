package com.common.base.service.impl;

import com.common.base.dao.BaseDao;
import com.common.base.model.BaseDto;
import com.common.base.model.BaseModel;
import com.common.base.service.BaseService;
import com.common.utils.model.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseDto, D extends BaseModel, M extends BaseDao<D>> implements BaseService<T> {

    @Autowired
    protected M mapper;

    private Class<T> dtoClazz;

    private Class<D> modelClazz;

    protected void currentModleClass() {
        if (null == dtoClazz) {
            this.dtoClazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        if (null == modelClazz) {
            this.modelClazz = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
    }

    @Override
    public List<T> list() {
        List<? extends BaseModel> list = mapper.selectAll();
        currentModleClass();
        return CopyUtils.copyList(list, dtoClazz);
    }

    @Override
    public void save(T dto) {
        currentModleClass();
        mapper.insert(CopyUtils.copyObject(dto, modelClazz));
    }

    @Override
    public void update(T dto) {
        currentModleClass();
        mapper.updateByPrimaryKey(CopyUtils.copyObject(dto, modelClazz));
    }

    @Override
    public void delete(Long id) {
        currentModleClass();
        BaseModel baseModel = null;
        try {
            baseModel = modelClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        baseModel.setPrimaryKey(id);
        mapper.deleteByPrimaryKey(baseModel);
    }

}
