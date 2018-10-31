package com.common.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: li.hao
 * @date: 2018/10/30 16:27
 */
@Data
public class BaseModel {

    @Id
    private Long primaryKey;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "create_time")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

}
