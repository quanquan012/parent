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

    /**
     * JWT filter registration
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterJwtAuthentication() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }

}
