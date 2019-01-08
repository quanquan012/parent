package com.client.model.ao;

import com.client.model.consts.Code2SessionConsts;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author lihao
 * @date 2018-12-03 14:35
 */
@Data
public class Code2SessionAo implements Serializable {

    private String appid = Code2SessionConsts.APP_ID;

    private String secret = Code2SessionConsts.APP_SECRET;

    private String js_code;

    private String grant_type = Code2SessionConsts.GRANT_TYPE;

}