package base.dao;

import base.BaseTestNG;
import com.wx.dao.UserDao;
import com.wx.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author: li.hao
 * @date: 2018/10/25 09:19
        */
    public class UserDaoTest extends BaseTestNG {
        @Autowired
        private UserDao mapper;

    @Test
    public void updateUserByOpenId(){
        User user = new User();
        user.setOpenId("1001");
        user.setPhone("15567861122");
        user.setName("666");
        mapper.updateUserByOpenId(user);

    }
}
