package com.pc.model.ao;

import com.common.base.model.BaseAo;
import lombok.Data;

@Data
public class MerchantAo extends BaseAo {

    private Long merchantId;

    private String merchantName;

    private String merchantCode;

    private String merchantPhone;

    private String merchantAddress;

}
