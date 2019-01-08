package com.client.controller;

import com.client.api.Code2Session;
import com.client.model.ao.Hello;
import com.client.model.ao.WxCodeAo;
import com.client.model.ao.WxUserAo;
import com.client.model.consts.Code2SessionConsts;
import com.client.model.dto.WxUserDto;
import com.client.model.vo.Code2SessionVo;
import com.client.model.vo.Greeting;
import com.client.service.WxUserService;
import com.common.base.controller.BaseController;
import com.common.utils.model.CopyUtils;
import com.common.web.Message;
import com.common.web.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

/**
 * WX login
 *
 * @author : li.hao
 * @date : 2018/10/24
 */
@RestController
@RequestMapping("/users")
@Slf4j
@Api(tags = {"user-client wx用户管理"})
public class WxUserController extends BaseController<WxUserAo, WxUserDto, WxUserService> {
    @Autowired
    private Code2Session code2Session;

    @PostMapping(value = "/wx", params = "type=register")
    @ApiOperation(value = "user register", notes = "用户注册、登陆接口")
    @ApiImplicitParams(
            {@ApiImplicitParam(paramType = "path", name = "type", value = "register", required = true)})
    public Message registerUser(@RequestBody WxCodeAo wxCodeAo) {
        Code2SessionVo vo = checkCode(wxCodeAo);
        if (null != vo) {
            WxUserDto dto = service.selectUserByOpenId(vo.getOpenid());
            if (null == dto) {
                dto = new WxUserDto();
                dto = setProperties(dto, vo, wxCodeAo);
                dto = service.insert(dto);
            } else {
                WxUserAo ao = CopyUtils.copyObject(wxCodeAo, WxUserAo.class);
                dto = setProperties(dto, vo, wxCodeAo);
                service.update(dto, updateConditions(ao));
            }
            return successMessage(dto);
        }
        return warnMessage(MessageConstant.SYSTEM_BUSY);
    }

    /**
     * 微信服务端返回数据校验
     * TODO 后续补齐详细验证逻辑
     *
     * @param wxCodeAo
     * @return
     */
    private Code2SessionVo checkCode(WxCodeAo wxCodeAo) {
        Code2SessionVo code2SessionVo = code2Session.test(Code2SessionConsts.APP_ID, Code2SessionConsts.APP_SECRET, wxCodeAo.getCode(), Code2SessionConsts.GRANT_TYPE);
        log.info(code2SessionVo.getOpenid());
        if (null != code2SessionVo) {
            log.info("errorCode:[" + code2SessionVo.getErrcode() + "]");
            String errCode = code2SessionVo.getErrcode();
            String openId = code2SessionVo.getOpenid();
            if (StringUtils.isNotEmpty(openId)) {
                return code2SessionVo;
            }
            log.info("errorCode:[" + errCode + "]--openId:[" + openId + "]");
        }
        return null;
    }

    /**
     * 设置属性
     *
     * @param dto
     * @param vo
     * @param wxCodeAo
     * @return
     */
    private WxUserDto setProperties(WxUserDto dto, Code2SessionVo vo, WxCodeAo wxCodeAo){
        dto.setNickName(wxCodeAo.getNickName());
        dto.setAvatarUrl(wxCodeAo.getAvatarUrl());
        dto.setProvince(wxCodeAo.getProvince());
        dto.setCountry(wxCodeAo.getCountry());
        dto.setCity(wxCodeAo.getCity());
        dto.setErrCode(vo.getErrcode());
        dto.setOpenId(vo.getOpenid());
        dto.setErrMsg(vo.getErrmsg());
        dto.setUnionId(vo.getUnionid());
        dto.setSessionKey(vo.getSession_key());
        return dto;
    }
}
