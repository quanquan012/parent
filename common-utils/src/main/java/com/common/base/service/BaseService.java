package com.common.base.service;


import com.common.base.model.BaseDto;

import java.util.List;

public interface BaseService<T extends BaseDto> {

    List<T> list();

    int save(T t);

    int update(T t);

    int delete(Long id);

}
