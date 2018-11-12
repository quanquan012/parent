package com.common.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDto {
    private Integer pageNum;

    private Integer pageSize;

    private Long primaryKey;

    private String openId;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;
}
