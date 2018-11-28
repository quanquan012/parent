package com.pc.model.dto;

import com.common.base.model.BaseDto;
import lombok.Data;

/**
 * @author lihao
 * @date 2018-11-23 17:08
 */
@Data
public class AuthNavigatorDto extends BaseDto {

    private String authCode;

    private String navigatorCode;

}