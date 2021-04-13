package io.renren.modules.net.controller;


import com.blaze.network.common.utils.R;
import io.renren.modules.net.feign.PortFeignService;
import io.renren.modules.net.to.PortFeignTo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-04-09 23:34:07
 */
@RestController
@RequestMapping("net/port")
public class PortController {
    @Autowired
    private PortFeignService portFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("net:port:list")
    public R list(@RequestParam Map<String, Object> params){
        return portFeignService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{portId}")
    @RequiresPermissions("net:port:info")
    public R info(@PathVariable("portId") Integer portId){
		return portFeignService.info(portId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("net:port:save")
    public R save(@RequestBody PortFeignTo port){
		return portFeignService.save(port);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("net:port:update")
    public R update(@RequestBody PortFeignTo port){
		return portFeignService.update(port);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("net:port:delete")
    public R delete(@RequestBody Integer[] portIds){
		return portFeignService.delete(portIds);
    }

}
