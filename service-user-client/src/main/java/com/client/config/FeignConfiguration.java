package com.client.config;

import com.client.converter.WxMessageConverter;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lihao
 * @date 2018-12-03 15:54
 */
@Configuration
public class FeignConfiguration {
    /**
     * 大坑的接口
     *
     * 解决微信返回参数为[text/plain] 无法转化为json
     */
    @Bean
    public Decoder feignDecoder() {
        WxMessageConverter wxConverter = new WxMessageConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(wxConverter);
        return new SpringDecoder(objectFactory);
    }
}