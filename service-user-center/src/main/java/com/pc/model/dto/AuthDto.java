package com.pc.model.dto;

import com.common.base.model.BaseDto;
import lombok.Data;

/**
 * @author lihao
 * @date 2018-11-22 13:17
 */
@Data
public class AuthDto extends BaseDto {

    private String authName;

    private String authCode;

    private Boolean disable;

    private Boolean status;

}