package io.renren.modules.net.feign;

import com.blaze.network.common.utils.R;
import io.renren.modules.net.to.HostFeignTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author yuanzhouli
 * @date 2021/4/9 - 23:46
 */
@FeignClient("search")
@RequestMapping("search/host")
public interface HostFeignService {
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params);
    @RequestMapping("/info/{hostId}")
    public R info(@PathVariable("hostId") Integer hostId);
    @RequestMapping("/save")
    public R save(@RequestBody HostFeignTo host);
    @RequestMapping("/update")
    public R update(@RequestBody HostFeignTo host);
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] hostIds);

}
