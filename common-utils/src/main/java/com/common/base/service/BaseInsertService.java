package com.common.base.service;

import com.common.base.model.BaseDto;

public interface BaseInsertService<T extends BaseDto> {

    /**
     * 插入数据
     *
     * @param t 实体Model
     * @return 数据库中插入的实体Model
     */
    T insert(T t);

}
