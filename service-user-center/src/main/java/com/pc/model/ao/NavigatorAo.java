package com.pc.model.ao;

import com.common.base.model.BaseAo;
import lombok.Data;

@Data
public class NavigatorAo extends BaseAo {

    public static final String PRIORITY = "priority";

    private String navigatorName;

    private String navigatorPath;

}
