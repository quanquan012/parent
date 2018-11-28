package com.common.base.model;

import lombok.Data;

/**
 * @author lihao
 * @date 2018-11-23 13:39
 */
@Data
public class BaseTransferVo {

    private Long key;

    private String label;

    private String code;

    private Boolean disabled;

}