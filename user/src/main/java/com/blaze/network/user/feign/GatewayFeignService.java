package com.blaze.network.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanzhouli
 * @date 2021/2/7 - 22:26
 */
@RequestMapping("/test")
@FeignClient("gateway")
public interface GatewayFeignService {
    @RequestMapping("/test")
    public void test();
}
