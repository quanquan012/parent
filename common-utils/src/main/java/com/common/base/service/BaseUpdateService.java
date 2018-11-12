package com.common.base.service;

import com.common.base.condition.Conditions;
import com.common.base.model.BaseDto;

public interface BaseUpdateService<T extends BaseDto> {

    /**
     * 更新数据
     *
     * @param t 实体Model
     * @param conditions 更新条件
     * @return 数据库中更新的实体Model
     */
    T update(T t, Conditions conditions);

}
