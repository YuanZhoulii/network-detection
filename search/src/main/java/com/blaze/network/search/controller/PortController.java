package com.blaze.network.search.controller;

import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.R;
import com.blaze.network.search.entity.PortEntity;
import com.blaze.network.search.service.PortService;
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
@RequestMapping("search/port")
public class PortController {
    @Autowired
    private PortService portService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = portService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{portId}")
    public R info(@PathVariable("portId") Integer portId){
		PortEntity port = portService.getById(portId);

        return R.ok().put("port", port);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PortEntity port){
		portService.save(port);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PortEntity port){
		portService.updateById(port);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] portIds){
		portService.removeByIds(Arrays.asList(portIds));

        return R.ok();
    }

}
