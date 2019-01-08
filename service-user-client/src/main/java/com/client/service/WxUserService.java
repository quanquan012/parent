package com.client.service;

import com.client.model.dto.WxUserDto;
import com.common.base.service.BaseService;

/**
 * @author lihao
 * @date 2018-12-03 18:07
 */
public interface WxUserService extends BaseService<WxUserDto> {
    /**
     * 根据OPEN_ID查询用户信息
     *
     * @param openId
     * @return
     */
    WxUserDto selectUserByOpenId(String openId);
}