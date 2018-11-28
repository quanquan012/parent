package com.pc.model.ao;

import com.pc.model.dto.AuthDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihao
 * @date 2018-11-23 13:34
 */
@Data
public class AccountAuthAo implements Serializable {

    private List<AuthDto> left;

    private List<Long> right;

    private String accountCode;

}