package com.common.base.service;

import com.common.base.condition.Conditions;
import com.common.base.model.BaseDto;

import java.util.List;

public interface BaseSelectService<T extends BaseDto> {

    /**
     * 查询所有
     *
     * @return 实体列表
     */
    List<T> selectAll();

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 根据条件查询
     *
     * @param conditions 搜索条件
     * @return 实体列表
     */
    List<T> selectList(Conditions conditions);

}
