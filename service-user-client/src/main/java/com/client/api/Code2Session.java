package com.client.api;

import com.client.hystrix.Code2SessionHystrix;
import com.client.model.vo.Code2SessionVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "txcode2session", url = "https://api.weixin.qq.com", fallback = Code2SessionHystrix.class)
public interface Code2Session {
    @RequestMapping(value = "/sns/jscode2session", method = RequestMethod.GET)
    @ResponseBody
    Code2SessionVo test(@RequestParam("appid") String appid, @RequestParam("secret") String secret, @RequestParam("js_code") String js_code, @RequestParam("grant_type") String grant_type);
}
