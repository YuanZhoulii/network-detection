package io.renren.modules.net.controller;


import com.blaze.network.common.utils.R;
import io.renren.modules.net.feign.HostFeignService;
import io.renren.modules.net.to.HostFeignTo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-04-09 23:34:06
 */
@RestController
@RequestMapping("net/host")
public class HostController {
    @Autowired
    private HostFeignService hostFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("net:host:list")
    public R list(@RequestParam Map<String, Object> params){
        return hostFeignService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{hostId}")
    @RequiresPermissions("net:host:info")
    public R info(@PathVariable("hostId") Integer hostId){
		return hostFeignService.info(hostId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("net:host:save")
    public R save(@RequestBody HostFeignTo host){
		return hostFeignService.save(host);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("net:host:update")
    public R update(@RequestBody HostFeignTo host){
		return hostFeignService.update(host);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("net:host:delete")
    public R delete(@RequestBody Integer[] hostIds){
		return hostFeignService.delete(hostIds);
    }

}
