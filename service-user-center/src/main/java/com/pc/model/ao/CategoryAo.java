package com.pc.model.ao;

import com.common.base.model.BaseAo;
import lombok.Data;

/**
 * @author: li.hao
 * @date: 2018/10/30 17:29
 */
@Data
public class CategoryAo extends BaseAo {

    private String categoryName;

    private String categoryCode;

    private String categoryType;

}
