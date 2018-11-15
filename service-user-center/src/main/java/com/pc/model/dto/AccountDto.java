package com.pc.model.dto;

import com.common.base.model.BaseDto;
import lombok.Data;

/**
 *
 *
 * @author li.hao
 * @date 2018/11/15 10:59
 */
@Data
public class AccountDto extends BaseDto {

    private String accountName;

    private String accountPass;

    private String accountCode;

    private String accountPhone;

    private String salt;
}
