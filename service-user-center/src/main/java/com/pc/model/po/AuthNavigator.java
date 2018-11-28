package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author lihao
 * @date 2018-11-23 17:04
 */
@Data
public class AuthNavigator extends BaseModel {

    @Column(name = "auth_code")
    private String authCode;

    @Column(name = "navigator_code")
    private String navigatorCode;

}