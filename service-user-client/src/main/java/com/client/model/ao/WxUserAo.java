package com.client.model.ao;

import com.common.base.model.BaseAo;
import lombok.Data;

/**
 * @author lihao
 * @date 2018-12-03 18:05
 */
@Data
public class WxUserAo extends BaseAo {
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