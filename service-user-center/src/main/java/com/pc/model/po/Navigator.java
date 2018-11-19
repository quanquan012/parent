package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;

@Data
public class Navigator extends BaseModel {

    @Column(name = "navigator_name")
    private String navigatorName;

    @Column(name = "navigator_path")
    private String navigatorPath;

    @Column(name = "navigator_code")
    private String navigatorCode;

    @Column(name = "parent_code")
    private String parentCode;

    @Column(name = "navigator_icon")
    private String navigatorIcon;

    @Column(name = "priority")
    private long priority;

}
