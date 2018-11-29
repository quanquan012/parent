package com.config;

import com.filter.TokenFilter;

/**
 * @author: li.hao
 * @date: 2018/11/01 11:17
 */
//@Configuration
public class FilterBeans {

//    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

}
