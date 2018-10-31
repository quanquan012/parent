package com.common.base.controller;

import com.common.base.model.BaseAo;
import com.common.base.model.BaseDto;
import com.common.base.service.BaseService;
import com.common.utils.model.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author: li.hao
 * @date: 2018/10/31 10:20
 */
public abstract class BaseController<A extends BaseAo, T extends BaseDto, S extends BaseService<T>> {

    @Autowired
    protected S service;

    private Class<A> aoClazz;

    private Class<T> dtoClazz;

    protected void currentModleClass() {
        if (null == aoClazz) {
            this.aoClazz = (Class<A>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        if (null == dtoClazz) {
            this.dtoClazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
    }


    @GetMapping
    public List<? extends BaseDto> list() {
        return listAll();
    }

    @PostMapping
    public void save(@RequestBody A a) {
        insert(a);
    }

    @PutMapping
    public void update(@RequestBody A a) {
        modify(a);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        deleteByPrimaryKey(id);
    }

    protected List<T> listAll() {
        return service.list();
    }

    protected void insert(A a) {
        currentModleClass();
        service.save(CopyUtils.copyObject(a, dtoClazz));
    }

    protected void modify(A a) {
        currentModleClass();
        service.update(CopyUtils.copyObject(a, dtoClazz));
    }

    protected void deleteByPrimaryKey(Long id) {
        service.delete(id);
    }

}
