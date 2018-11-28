package com.pc.api;

import com.pc.api.hystrix.CategoryHystrix;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author li.hao
 * @date 2018/11/01 13:55
 */
@FeignClient(name = "service-user-center", fallback = CategoryHystrix.class)
public interface CategoryApi {

}
