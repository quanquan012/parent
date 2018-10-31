package com.pc.controller;

import com.common.base.controller.BaseController;
import com.pc.model.dto.CategoryDto;
import com.pc.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: li.hao
 * @date: 2018/10/30 17:17
 */
@RestController(value = "/categories")
public class CategoryController extends BaseController<CategoryDto, CategoryService> {

    @GetMapping(params = "type=listCategoriesByType")
    public List<CategoryDto> listCategoriesByType(@RequestParam String categoryType) {
        return service.listCategoriesByType(categoryType);
    }

}
