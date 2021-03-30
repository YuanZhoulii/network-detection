package com.blaze.network.save.controller;

import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.R;
import com.blaze.network.save.entity.HostEntity;
import com.blaze.network.save.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-17 23:36:42
 */
@RestController
@RequestMapping("save/host")
public class HostController {
    @Autowired
    private HostService hostService;

    @RequestMapping("/search")
    public R search(@RequestParam Map<String,Object> params){

        return null;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = hostService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{hostId}")
    public R info(@PathVariable("hostId") Integer hostId){
		HostEntity host = hostService.getById(hostId);

        return R.ok().put("host", host);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HostEntity host){
		hostService.save(host);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody HostEntity host){
		hostService.updateById(host);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] hostIds){
		hostService.removeByIds(Arrays.asList(hostIds));

        return R.ok();
    }

}
