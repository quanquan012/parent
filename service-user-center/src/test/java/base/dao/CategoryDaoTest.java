package base.dao;

import base.BaseTestNG;
import com.pc.dao.CategoryDao;
import com.pc.model.po.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @program: parent
 * @description:
 * @author: li.hao
 * @date: 2018/10/31 13:23
 */
public class CategoryDaoTest extends BaseTestNG {

    @Autowired
    private CategoryDao mapper;

    @Test
    public void save(){
        Category category = new Category();
        category.setCategoryCode("A");
        category.setCategoryName("A类商品");
        category.setCategoryType("A");
        category.setOpenId("1001");
        int result = mapper.insert(category);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void update(){
        Category category = new Category();
        category.setPrimaryKey(1L);
        category.setCategoryCode("B");
        category.setCategoryName("B类商品");
        category.setCategoryType("B");
        category.setOpenId("1001");
        int result = mapper.updateByPrimaryKey(category);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void list(){
        List<Category> list = mapper.selectAll();
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void delete(){
        Category category = new Category();
        category.setPrimaryKey(1L);
        int result = mapper.deleteByPrimaryKey(category);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void listCategoriesByType(){
        List<Category> list = mapper.listCategoriesByType("A");
        Assert.assertEquals(list.size(), 1);
    }
}
