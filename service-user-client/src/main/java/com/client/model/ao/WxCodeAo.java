package com.client.model.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lihao
 * @date 2018-12-03 13:02
 */
@Data
public class WxCodeAo implements Serializable {

    private String code;

    private String nickName;

    private String avatarUrl;

    private Boolean gender;

    private String country;

    private String province;

    private String city;

}