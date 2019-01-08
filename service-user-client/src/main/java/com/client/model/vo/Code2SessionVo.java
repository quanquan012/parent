package com.client.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lihao
 * @date 2018-12-03 14:35
 */
@Data
public class Code2SessionVo implements Serializable {
    private String openid;

    private String session_key;

    private String unionid;

    private String errcode;

    private String errmsg;
}