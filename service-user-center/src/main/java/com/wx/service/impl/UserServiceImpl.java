package com.wx.service.impl;

import com.wx.dao.UserDao;
import com.common.utils.model.CopyUtils;
import com.wx.model.dto.UserRegistrationDto;
import com.wx.model.po.User;
import com.wx.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : li.hao
 * @date : 2018/10/24
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        User user = CopyUtils.copyObject(userRegistrationDto, User.class);
        if(checkRegisteredUser(user)){
            updateUserByOpenId(userRegistrationDto);
        }else{
            userDao.insert(user);
        }
    }

    @Override
    public boolean checkRegisteredUser(User user) {
        List<User> userList = userDao.select(user);
        return userList.size() == 0 ? false : true;
    }

    @Override
    public void updateUserByOpenId(UserRegistrationDto userRegistrationDto) {
        User user = CopyUtils.copyObject(userRegistrationDto, User.class);
        userDao.updateUserByOpenId(user);
    }
}
