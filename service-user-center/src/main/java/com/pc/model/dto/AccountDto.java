package com.pc.model.dto;

import lombok.Data;

/**
 *
 *
 * @author li.hao
 * @date 2018/11/15 10:59
 */
@Data
public class AccountDto {

    private String accountName;

    private String accountPass;

    private String salt;
}
