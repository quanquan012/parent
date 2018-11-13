package com.common.base.controller;

import com.common.base.condition.Conditions;
import com.common.base.condition.Order;
import com.common.base.condition.SearchFilter;
import com.common.base.model.BaseAo;
import com.common.base.model.BaseDto;
import com.common.base.service.BaseService;
import com.common.web.Message;
import com.common.web.MessageConstant;
import com.common.utils.model.CopyUtils;
import com.github.pagehelper.PageInfo;
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

    protected void currentModelClass() {
        if (null == aoClazz) {
            this.aoClazz = (Class<A>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        if (null == dtoClazz) {
            this.dtoClazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
    }

    @GetMapping(params = "type=page")
    public Message page(A a) {
        return successMessage(searchPage(a));
    }

    @GetMapping
    public Message list() {
        return successMessage(listAll());
    }

    @PostMapping
    public Message save(@RequestBody A a) {
        if (null != insert(a)) {
            return successMessage(MessageConstant.MESSAGE_SUCCESS_SAVE);
        }
        return warnMessage(MessageConstant.MESSAGE_ERROR_SAVE);
    }

    @PutMapping
    public Message update(@RequestBody A a) {
        if (null != modify(a)) {
            return successMessage(MessageConstant.MESSAGE_SUCCESS_UPDATE);
        }
        return warnMessage(MessageConstant.MESSAGE_ERROR_UPDATE);
    }

    @DeleteMapping(value = "/{id}")
    public Message delete(@PathVariable("id") Long id) {
        if (deleteByPrimaryKey(id) > 0) {
            return successMessage(MessageConstant.MESSAGE_SUCCESS_DELETE);
        }
        return warnMessage(MessageConstant.MESSAGE_ERROR_DELETE);
    }

    protected PageInfo<T> searchPage(A a){
        currentModelClass();
        return service.page(CopyUtils.copyObject(a, dtoClazz), pageConditions(a));
    }

    protected List<T> listAll() {
        return service.selectList(searchConditions());
    }

    protected T insert(A a) {
        currentModelClass();
        return service.insert(CopyUtils.copyObject(a, dtoClazz));
    }

    protected T modify(A a) {
        currentModelClass();
        return service.update(CopyUtils.copyObject(a, dtoClazz), null);
    }

    protected int deleteByPrimaryKey(Long id) {
        return service.deleteByPrimaryKey(id);
    }

    /**
     * 数据条件查询 子类自行实现
     *
     * @return 条件查询
     */
    protected Conditions searchConditions(){
        return null;
    }

    /**
     * 数据创建日期倒序
     *
     * @return 分页查询条件
     */
    protected Conditions pageConditions(A a){
        return null;
    }

}
