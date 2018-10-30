package com.pc.model.ao;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MerchandiseAo implements Serializable {

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
