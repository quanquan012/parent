package com.common.base.controller;

import com.common.base.model.BaseAo;
import com.common.base.model.BaseDto;
import com.common.base.service.BaseService;
import com.common.utils.model.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author: li.hao
 * @date: 2018/10/31 10:20
 */
public abstract class BaseController<D extends BaseDto, S extends BaseService> {

    @Autowired
    protected S service;

    private Type model;

    protected void currentModleClass() {
        if (null == model) {
            this.model = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }


    @GetMapping
    public <T extends BaseDto> List<T> list() {
        return listAll();
    }

    @PostMapping
    public <T extends BaseAo> void save(@RequestBody T t) {
        insert(t);
    }

    @PutMapping
    public <T extends BaseAo> void update(@RequestBody T t) {
        modify(t);
    }

    @DeleteMapping(value = "/{id}")
    public <T extends BaseDto> void delete(@PathVariable("id") Long id) {
        deleteByPrimaryKey(id);
    }

    protected <T extends BaseDto> List<T> listAll() {
        return service.list();
    }

    protected <T extends BaseAo> void insert(T t) {
        currentModleClass();
        service.save(CopyUtils.copyObject(t, (Class<D>) model));
    }

    protected <T extends BaseAo> void modify(T t) {
        currentModleClass();
        service.update(CopyUtils.copyObject(t, (Class<D>) model));
    }

    protected <T extends BaseDto> void deleteByPrimaryKey(Long id) {
        BaseDto dto = new BaseDto();
        dto.setPrimaryKey(id);
        service.delete((T) dto);
    }

}
