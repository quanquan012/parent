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

public abstract class BaseServiceImpl<T extends BaseDto, B extends BaseDao<? extends BaseModel>> implements BaseService {

    @Autowired
    private B mapper;

    @Override
    public List<T> list(){
        List<? extends BaseModel> list = mapper.selectAll();
        Type type = ((ParameterizedType)list.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return CopyUtils.copyList(list, (Class<T>)type);
    }

    @Override
    public void delete(Long id){

    }
}
