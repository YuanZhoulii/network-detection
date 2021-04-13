package io.renren.modules.net.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanzhouli
 * @date 2021/4/10 - 3:05
 */
@FeignClient("detect")
@RequestMapping("detect/nmap")
public interface DetectFeignService {
    @RequestMapping("/os/address")
    public void osByAddress(@RequestBody String address);
    @RequestMapping("/host/address")
    public void hostByAddress(@RequestBody String address);
    @RequestMapping("/all/address")
    public void allByAddress(@RequestBody String address);
}
