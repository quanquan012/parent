package com.wx.controller;

import com.common.utils.model.CopyUtils;
import com.wx.model.ao.UserRegistrationAo;
import com.wx.model.dto.UserRegistrationDto;
import com.wx.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * WX login
 *
 * @author : li.hao
 * @date : 2018/10/24
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(params = "type=register")
    public void registerUser(@RequestParam String code){
        log.info("-------------"+code);
    }
}
