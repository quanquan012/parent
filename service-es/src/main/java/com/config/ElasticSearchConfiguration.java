package com.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author lihao
 * @date 2018-11-21 15:33
 */
@Configuration
@Data
public class ElasticSearchConfiguration {

    @Value("${es.hosts}")
    private String hosts;

}