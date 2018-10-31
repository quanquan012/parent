package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author: li.hao
 * @date: 2018/10/30 16:20
 */
@Data
public class Merchant extends BaseModel {

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_code")
    private String merchantCode;

    @Column(name = "merchant_phone")
    private String merchantPhone;

    @Column(name = "merchant_address")
    private String merchantAddress;

}
