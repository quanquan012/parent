package com.pc.model.vo;

import com.pc.model.dto.NavigatorDto;
import lombok.Data;

import java.util.List;

/**
 * @author lihao
 * @date 2018-11-23 17:10
 */
@Data
public class AuthNavigatorVo {
    private List<NavigatorDto> left;

    private List<Long> right;

}