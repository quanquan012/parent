package com.pc.controller;

import com.common.base.controller.StandardController;
import com.common.utils.jwt.JwtUtils;
import com.common.web.Message;
import com.common.web.MessageConstant;
import com.pc.model.ao.AccountAo;
import com.pc.model.dto.AccountDto;
import com.pc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * login
 *
 * @author li.hao
 * @date 2018/11/15 10:06
 */
@RestController
@RequestMapping("/login")
public class LoginController extends StandardController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Object what(@RequestBody AccountAo accountAo) {
        AccountDto dto = checkAccountByPass(accountAo);
        if (null != dto) {
            String jwt = JwtUtils.generateToken(dto.getAccountName());
            return new HashMap<String, String>() {{
                put("token", jwt);
            }};
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    private AccountDto checkAccountByPass(AccountAo accountAo) {
        if (!StringUtils.isEmpty(accountAo.getAccountName()) && !StringUtils.isEmpty(accountAo.getAccountPass())) {
            return loginService.login(accountAo.getAccountName(), accountAo.getAccountPass());
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public void options() {

    }

}
