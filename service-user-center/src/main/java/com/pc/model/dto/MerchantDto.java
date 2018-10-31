package com.pc.model.dto;

import com.common.base.model.BaseDto;
import lombok.Data;

@Data
public class MerchantDto extends BaseDto {

    private String merchantName;

    private String merchantCode;

    private String merchantPhone;

    private String merchantAddress;

}
