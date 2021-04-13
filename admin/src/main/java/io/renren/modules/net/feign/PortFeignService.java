package io.renren.modules.net.feign;

import com.blaze.network.common.utils.R;
import io.renren.modules.net.to.PortFeignTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author yuanzhouli
 * @date 2021/4/9 - 23:47
 */
@FeignClient("search")
@RequestMapping("search/port")
public interface PortFeignService {
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params);
    @RequestMapping("/info/{portId}")
    public R info(@PathVariable("portId") Integer portId);
    @RequestMapping("/save")
    public R save(@RequestBody PortFeignTo port);
    @RequestMapping("/update")
    public R update(@RequestBody PortFeignTo port);
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] portIds);
}
