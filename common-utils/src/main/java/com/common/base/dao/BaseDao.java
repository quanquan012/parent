package com.common.base.dao;

import com.common.base.model.BaseModel;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 *
 * base dao
 *
 * @author : li.hao
 * @date : 2018/10/30
 */
public interface BaseDao<T extends BaseModel> extends Mapper<T>, InsertListMapper<T> {

}
