package com.pc.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MerchandiseDto {

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
