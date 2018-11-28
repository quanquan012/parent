package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author lihao
 * @date 2018-11-22 13:15
 */
@Data
public class Auth extends BaseModel {

    @Column(name = "auth_name")
    private String authName;

    @Column(name = "auth_code")
    private String authCode;

    @Column(name = "auth_disable")
    private Boolean disable;

    @Column(name = "data_status")
    private Boolean status;

}