package com.pc.dao;

import com.common.base.dao.BaseDao;
import com.pc.model.po.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends BaseDao<Category> {

    List<Category> listCategoriesByType(String categoryType);

}
