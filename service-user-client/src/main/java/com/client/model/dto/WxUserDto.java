package com.client.model.dto;

import com.common.base.model.BaseDto;
import lombok.Data;

/**
 * @author lihao
 * @date 2018-12-03 18:05
 */
@Data
public class WxUserDto extends BaseDto {

    private String nickName;

    private String avatarUrl;

    private Boolean gender;

    private String country;

    private String province;

    private String city;

    private String unionId;

    private String sessionKey;

    private String errCode;

    private String errMsg;

}