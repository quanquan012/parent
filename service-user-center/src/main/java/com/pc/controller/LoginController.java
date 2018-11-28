package com.pc.controller;

import com.common.base.controller.StandardController;
import com.common.utils.jwt.JwtUtils;
import com.common.utils.redis.RedisUtil;
import com.pc.model.ao.AccountAo;
import com.pc.model.consts.LoginConsts;
import com.pc.model.dto.AccountDto;
import com.pc.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * login
 *
 * @author li.hao
 * @date 2018/11/15 10:06
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController extends StandardController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping
    public Object login(@RequestBody AccountAo accountAo) {
        AccountDto dto = checkAccountByPass(accountAo);
        if (null != dto) {
            String jwt = JwtUtils.generateToken(dto.getAccountName());
            HashMap<String, String> map = new HashMap(1);
            map.put(LoginConsts.TOKEN, jwt);
            redisUtil.set(jwt, dto, LoginConsts.EXPIRE_TIME);
            return map;
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping
    public void logout(HttpServletRequest request){
        String token = request.getHeader(LoginConsts.TOKEN);
        redisUtil.del(token);
        log.info(token);
    }

    /**
     * 密码验证
     *
     * @param accountAo
     * @return AccountDto
     */
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
