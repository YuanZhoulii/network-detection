package com.blaze.network.detect.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 7:51
 */
@FeignClient("search")
@RequestMapping("search/host")
public interface SearchFeignService {
    @RequestMapping("/count")
    Integer count();
    @RequestMapping("/address")
    String address(@RequestBody Integer id);
}
