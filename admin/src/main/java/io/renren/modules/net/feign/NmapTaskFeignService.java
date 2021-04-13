package io.renren.modules.net.feign;

import com.blaze.network.common.utils.R;
import io.renren.modules.net.to.NmapTaskFeignTo;
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
@RequestMapping("search/nmaptask")
public interface NmapTaskFeignService {
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params);
    @RequestMapping("/info/{nmapTaskId}")
    public R info(@PathVariable("nmapTaskId") Integer nmapTaskId);
    @RequestMapping("/save")
    public R save(@RequestBody NmapTaskFeignTo nmapTask);
    @RequestMapping("/update")
    public R update(@RequestBody NmapTaskFeignTo nmapTask);
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] nmapTaskIds);
}
