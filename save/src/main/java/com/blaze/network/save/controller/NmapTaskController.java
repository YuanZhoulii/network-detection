package com.blaze.network.save.controller;

import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.R;
import com.blaze.network.save.entity.NmapTaskEntity;
import com.blaze.network.save.service.NmapTaskService;
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
@RequestMapping("save/nmaptask")
public class NmapTaskController {
    @Autowired
    private NmapTaskService nmapTaskService;


    @RequestMapping("/mysave")
    public void mysave(){
//        MySAXParser.parse("nmap_output.xml");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = nmapTaskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{nmapTaskId}")
    public R info(@PathVariable("nmapTaskId") Integer nmapTaskId){
		NmapTaskEntity nmapTask = nmapTaskService.getById(nmapTaskId);

        return R.ok().put("nmapTask", nmapTask);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NmapTaskEntity nmapTask){
		nmapTaskService.save(nmapTask);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody NmapTaskEntity nmapTask){
		nmapTaskService.updateById(nmapTask);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] nmapTaskIds){
		nmapTaskService.removeByIds(Arrays.asList(nmapTaskIds));

        return R.ok();
    }

}
