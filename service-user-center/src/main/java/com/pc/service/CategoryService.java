package com.pc.service;

import com.pc.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> listCategoriesByType(String categoryType);

}
