package com.common.base.controller;

import com.common.base.model.BaseAo;
import com.common.base.model.BaseDto;
import com.common.base.service.BaseService;
import com.common.web.Message;
import com.common.web.MessageConstant;
import com.common.utils.model.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author: li.hao
 * @date: 2018/10/31 10:20
 */
public abstract class BaseController<A extends BaseAo, T extends BaseDto, S extends BaseService<T>> extends StandardController {

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
    public Message list() {
        return successMessage(listAll());
    }

    @PostMapping
    public Message save(@RequestBody A a) {
        if (insert(a) > 0){
            return successMessage(MessageConstant.MESSAGE_SUCCESS_SAVE);
        }
        return warnMessage(MessageConstant.MESSAGE_ERROR_SAVE);
    }

    @PutMapping
    public Message update(@RequestBody A a) {
        if (modify(a) > 0){
            return successMessage(MessageConstant.MESSAGE_SUCCESS_UPDATE);
        }
        return warnMessage(MessageConstant.MESSAGE_ERROR_UPDATE);
    }

    @DeleteMapping(value = "/{id}")
    public Message delete(@PathVariable("id") Long id) {
        if (deleteByPrimaryKey(id) > 0){
            return successMessage(MessageConstant.MESSAGE_SUCCESS_DELETE);
        }
        return warnMessage(MessageConstant.MESSAGE_ERROR_DELETE);
    }

    protected List<T> listAll() {
        return service.list();
    }

    protected int insert(A a) {
        currentModleClass();
        return service.save(CopyUtils.copyObject(a, dtoClazz));
    }

    protected int modify(A a) {
        currentModleClass();
        return service.update(CopyUtils.copyObject(a, dtoClazz));
    }

    protected int deleteByPrimaryKey(Long id) {
        return service.delete(id);
    }

}
