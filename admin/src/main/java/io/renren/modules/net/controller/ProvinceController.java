package io.renren.modules.net.controller;


import com.blaze.network.common.utils.R;
import io.renren.modules.net.feign.ProvinceFeignService;
import io.renren.modules.net.to.ProvinceFeignTo;
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
@RequestMapping("net/province")
public class ProvinceController {
    @Autowired
    private ProvinceFeignService provinceFeignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("net:province:list")
    public R list(@RequestParam Map<String, Object> params){
        return provinceFeignService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{provinceId}")
    @RequiresPermissions("net:province:info")
    public R info(@PathVariable("provinceId") Integer provinceId){
		return provinceFeignService.info(provinceId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("net:province:save")
    public R save(@RequestBody ProvinceFeignTo province){
		return provinceFeignService.save(province);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("net:province:update")
    public R update(@RequestBody ProvinceFeignTo province){
		return provinceFeignService.update(province);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("net:province:delete")
    public R delete(@RequestBody Integer[] provinceIds){
		return provinceFeignService.delete(provinceIds);
    }

}
