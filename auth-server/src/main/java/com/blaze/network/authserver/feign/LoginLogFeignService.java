package com.blaze.network.authserver.feign;

import com.blaze.network.authserver.to.LoginLogFeignTo;
import com.blaze.network.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author yuanzhouli
 * @date 2021/4/10 - 1:42
 */
@FeignClient("user")
@RequestMapping("user/loginlog")
public interface LoginLogFeignService {
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params);
    @RequestMapping("/info/{loginLogId}")
    public R info(@PathVariable("loginLogId") Integer loginLogId);
    @RequestMapping("/save")
    public R save(@RequestBody LoginLogFeignTo loginLog);
    @RequestMapping("/update")
    public R update(@RequestBody LoginLogFeignTo loginLog);
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] loginLogIds);
}
