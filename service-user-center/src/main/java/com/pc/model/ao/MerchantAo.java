package com.pc.model.ao;

import lombok.Data;

import java.io.Serializable;

@Data
public class MerchantAo implements Serializable {

    private Long merchantId;

    private String merchantName;

    private String merchantCode;

    private String merchantPhone;

    private String merchantAddress;

}
