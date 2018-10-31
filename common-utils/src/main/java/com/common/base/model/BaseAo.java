package com.common.base.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: li.hao
 * @date: 2018/10/31 10:26
 */
@Data
public class BaseAo implements Serializable {
    private Long primaryKey;

    private String openId;

    private Date createTime;
}
