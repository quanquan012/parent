package com.pc.model.po;

import com.common.base.model.BaseModel;
import lombok.Data;

/**
 * account model
 *
 * @author li.hao
 * @date 2018/11/15 11:16
 */
@Data
public class Account extends BaseModel {

    private String accountName;

    private String accountPass;

    private String salt;

}
