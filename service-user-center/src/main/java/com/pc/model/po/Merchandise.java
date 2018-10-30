package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author: li.hao
 * @date: 2018/10/30 16:20
 */
@Data
public class Merchandise extends BaseModel {

    @Id
    private Long merchandiseId;

    @Column(name = "merchant_code")
    private String merchantCode;

    @Column(name = "merchandise_name")
    private String merchandiseName;

    @Column(name = "merchandise_code")
    private String merchandiseCode;

    @Column(name = "merchandise_detail_price")
    private BigDecimal merchandiseDetailPrice;

    @Column(name = "merchandise_prime_costs")
    private BigDecimal merchandisePrimeCosts;

    @Column(name = "merchandise_image_url")
    private String merchandiseImageUrl;

    @Column(name = "merchandise_video_url")
    private String merchandiseVideoUrl;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_code")
    private String categoryCode;

}
