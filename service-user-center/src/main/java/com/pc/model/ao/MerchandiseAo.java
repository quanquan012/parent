package com.pc.model.ao;

import com.common.base.model.BaseAo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MerchandiseAo extends BaseAo {

    private Long merchandiseId;

    private String merchantCode;

    private String merchandiseName;

    private String merchandiseCode;

    private BigDecimal merchandiseDetailPrice;

    private BigDecimal merchandisePrimeCosts;

    private String merchandiseImageUrl;

    private String merchandiseVideoUrl;

    private String categoryName;

    private String categoryCode;

}
