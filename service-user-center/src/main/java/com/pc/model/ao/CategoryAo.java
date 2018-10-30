package com.pc.model.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: li.hao
 * @date: 2018/10/30 17:29
 */
@Data
public class CategoryAo implements Serializable {

    private Long categoryId;

    private String categoryName;

    private String categoryCode;

    private String categoryType;

}
