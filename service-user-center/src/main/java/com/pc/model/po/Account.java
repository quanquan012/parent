package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;

/**
 * account model
 *
 * @author li.hao
 * @date 2018/11/15 11:16
 */
@Data
public class Account extends BaseModel {

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_pass")
    private String accountPass;

    @Column(name = "account_code")
    private String accountCode;

    @Column(name = "account_phone")
    private String accountPhone;

    @Column(name = "salt")
    private String salt;

}
