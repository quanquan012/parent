package com.pc.controller;

import com.pc.model.ao.CategoryAo;
import com.pc.model.dto.CategoryDto;
import com.pc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: li.hao
 * @date: 2018/10/30 17:17
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories", params = "type=listCategoriesByType")
    public List<CategoryDto> listCategoriesByType(String categoryType) {
        return null;
    }

    @GetMapping(value = "/categories")
    public List<CategoryDto> list() {
        return null;
    }

    @PostMapping(value = "/categories")
    public void save(CategoryAo categoryAo) {

    }

    @PutMapping(value = "/categories/{id}")
    public void update(CategoryAo categoryAo) {

    }

    @DeleteMapping(value = "/categories/{id}")
    public void delete(@PathVariable Long id) {

    }


}
