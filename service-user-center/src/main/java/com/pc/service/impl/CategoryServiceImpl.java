package com.pc.service.impl;

import com.common.utils.model.CopyUtils;
import com.pc.dao.CategoryDao;
import com.pc.model.dto.CategoryDto;
import com.pc.model.po.Category;
import com.pc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: li.hao
 * @date: 2018/10/30 17:06
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<CategoryDto> listCategoriesByType(String categoryType) {
        List<Category> categories = categoryDao.listCategoriesByType(categoryType);
        List<CategoryDto> categoryDtos = CopyUtils.copyList(categories, CategoryDto.class);
        return categoryDtos;
    }
}
