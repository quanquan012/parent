package com.pc.model.dto;

import com.common.base.model.BaseDto;
import lombok.Data;

/**
 * @author: li.hao
 * @date: 2018/10/30 17:34
 */
@Data
public class CategoryDto extends BaseDto {

    private String categoryName;

    private String categoryCode;

    private String categoryType;

}
