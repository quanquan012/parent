package com.pc.model.ao;

import com.common.base.model.BaseAo;
import lombok.Data;

import java.io.Serializable;

/**
 * login account AO
 *
 * @author li.hao
 * @date 2018/11/15 10:54
 */
@Data
public class AccountAo extends BaseAo {

    private String accountName;

    private String accountPass = "123";

    private String accountCode;

    private String accountPhone;

    private String salt = "salt123";

}
