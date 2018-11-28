package com.pc.model.dto;

import com.common.base.model.BaseDto;
import lombok.Data;

/**
 * @author lihao
 * @date 2018-11-23 13:33
 */
@Data
public class AccountAuthDto extends BaseDto {

    private String accountCode;

    private String authCode;

}