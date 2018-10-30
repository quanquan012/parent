package com.wx.service;

import com.wx.model.dto.UserRegistrationDto;
import com.wx.model.po.User;

public interface UserService {

    void registerUser(UserRegistrationDto userRegistrationDto);

    boolean checkRegisteredUser(User user);

    void updateUserByOpenId(UserRegistrationDto userRegistrationDto);

}
