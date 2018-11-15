package com.pc.model.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * login account AO
 *
 * @author li.hao
 * @date 2018/11/15 10:54
 */
@Data
public class AccountAo implements Serializable {

    private String accountName;

    private String accountPass;

}
