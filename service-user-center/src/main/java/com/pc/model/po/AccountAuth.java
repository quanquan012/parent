package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author lihao
 * @date 2018-11-23 11:28
 */
@Data
public class AccountAuth extends BaseModel {

    @Column(name = "account_code")
    private String accountCode;

    @Column(name = "auth_code")
    private String authCode;

}