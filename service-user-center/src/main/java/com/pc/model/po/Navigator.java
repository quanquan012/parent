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

}
