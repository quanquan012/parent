package com.wx.controller;

import com.common.utils.model.CopyUtils;
import com.wx.model.ao.UserRegistrationAo;
import com.wx.model.dto.UserRegistrationDto;
import com.wx.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : li.hao
 * @date : 2018/10/24
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("users")
    public void registerUser(UserRegistrationAo userRegistrationAo){
        UserRegistrationDto userRegistrationDto = CopyUtils.copyObject(userRegistrationAo, UserRegistrationDto.class);
        userService.registerUser(userRegistrationDto);
    }
}
