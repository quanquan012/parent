package com.pc.model.ao;

import com.pc.model.dto.NavigatorDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihao
 * @date 2018-11-23 17:09
 */
@Data
public class AuthNavigatorAo implements Serializable {

    private List<NavigatorDto> left;

    private List<Long> right;

    private String authCode;

}