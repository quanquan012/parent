package com.pc.model.ao;

import com.common.base.model.BaseAo;
import lombok.Data;

/**
 * @author lihao
 * @date 2018-11-22 13:18
 */
@Data
public class AuthAo extends BaseAo {

    private String authName;

    private String authCode;

    private Boolean disable;

    private Boolean status;

}