package com.config.web;

import com.config.jwt.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * filters configuration
 *
 * @author li.hao
 * @date 2018/11/15 10:46
 */
@Configuration
public class FiltersConfiguration {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    /**
     * JWT filter registration
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterJwtAuthentication() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(jwtAuthenticationFilter());
        return registrationBean;
    }

}
