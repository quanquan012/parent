package com.pc.model.dto;

import com.common.base.model.BaseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NavigatorDto extends BaseDto {

    private String navigatorName;

    private String navigatorPath;

    private String navigatorCode;

    private String navigatorIcon;

    private String parentCode;

    private List<NavigatorDto> children = new ArrayList<>();

}
