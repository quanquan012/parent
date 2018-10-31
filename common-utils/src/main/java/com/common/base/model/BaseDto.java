package com.common.base.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDto {
    private Long primaryKey;

    private String openId;

    private Date createTime;
}
