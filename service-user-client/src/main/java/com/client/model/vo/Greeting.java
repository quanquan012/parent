package com.client.model.vo;

import lombok.Data;

/**
 * @author lihao
 * @date 2018-12-06 17:15
 */
@Data
public class Greeting {

    public Greeting(String name){
        this.name = name;
    }

    private String name;
}