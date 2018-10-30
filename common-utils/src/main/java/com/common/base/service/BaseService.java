package com.common.base.service;

import com.common.base.model.BaseDto;

import java.util.List;

public interface BaseService<T extends BaseDto> {

    List<T> list();

    void save(T t);

    void update(T t);

    void delete(Long id);

}
