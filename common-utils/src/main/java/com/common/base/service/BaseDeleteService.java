package com.common.base.service;

import com.common.base.condition.Conditions;
import com.common.base.model.BaseDto;

public interface BaseDeleteService<T extends BaseDto> {

    /**
     * 根据主键删除数据
     *
     * @param primaryKey 数据主键
     * @return 数据库执行结果
     */
    int deleteByPrimaryKey(Long primaryKey);

    /**
     * 根据条件删除数据
     *
     * @param t 实体Model
     * @param conditions 删除条件
     * @return 数据库执行结果
     */
    int deleteByConditions(T t, Conditions conditions);

}
