package com.blaze.network.detect.feign;

import com.blaze.network.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 9:27
 */
@FeignClient("save")
@RequestMapping("save/save")
public interface SaveFeignService {
    @RequestMapping("/all")
    public R all(@RequestBody String filePath);

}
