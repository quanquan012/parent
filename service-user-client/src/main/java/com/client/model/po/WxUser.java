package com.client.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author lihao
 * @date 2018-12-03 17:40
 */
@Data
public class WxUser extends BaseModel {

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "country")
    private String country;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "union_id")
    private String unionId;

    @Column(name = "session_key")
    private String sessionKey;

    @Column(name = "err_code")
    private Long errCode;

    @Column(name = "err_msg")
    private String errMsg;

}