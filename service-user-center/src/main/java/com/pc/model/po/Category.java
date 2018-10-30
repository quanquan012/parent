package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author: li.hao
 * @date: 2018/10/30 16:21
 */
@Data
public class Category extends BaseModel {

    @Id
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "category_type")
    private String categoryType;

}
