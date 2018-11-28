package com.pc.model.vo;

import com.pc.model.dto.AuthDto;
import lombok.Data;

import java.util.List;

/**
 * @author lihao
 * @date 2018-11-23 12:00
 */
@Data
public class AccountAuthVo {

    private List<AuthDto> left;

    private List<Long> right;

}