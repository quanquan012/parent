package com.common.base.service;


import com.common.base.condition.Conditions;
import com.common.base.model.BaseDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseService<T extends BaseDto> extends BaseInsertService<T>, BaseUpdateService<T>, BaseDeleteService<T>, BaseSelectService<T> {

    PageInfo<T> page(T t, Conditions conditions);

}
