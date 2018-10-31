package com.pc.service;

import com.common.base.service.BaseService;
import com.pc.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService extends BaseService<CategoryDto> {

    List<CategoryDto> listCategoriesByType(String categoryType);

}
