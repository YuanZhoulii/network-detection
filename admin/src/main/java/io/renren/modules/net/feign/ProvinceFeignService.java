package io.renren.modules.net.feign;

import com.blaze.network.common.utils.R;
import io.renren.modules.net.to.ProvinceFeignTo;
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
@RequestMapping("search/province")
public interface ProvinceFeignService {
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params);
    @RequestMapping("/info/{provinceId}")
    public R info(@PathVariable("provinceId") Integer provinceId);
    @RequestMapping("/save")
    public R save(@RequestBody ProvinceFeignTo province);
    @RequestMapping("/update")
    public R update(@RequestBody ProvinceFeignTo province);
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] provinceIds);
}
