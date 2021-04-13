package io.renren.modules.net.controller;


import com.blaze.network.common.utils.R;
import io.renren.modules.net.feign.NmapTaskFeignService;
import io.renren.modules.net.service.NmapTaskService;
import io.renren.modules.net.to.NmapTaskFeignTo;
import io.renren.modules.net.vo.NmapTaskVo;
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
@RequestMapping("net/nmaptask")
public class NmapTaskController {
    @Autowired
    private NmapTaskFeignService nmapTaskFeignService;

    @Autowired
    NmapTaskService nmapTaskService;
    @RequestMapping("/detect")
    public R detect(@RequestBody NmapTaskVo nmapTaskVo){
        nmapTaskService.createNmapTask(nmapTaskVo);
        return R.ok();
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("net:nmaptask:list")
    public R list(@RequestParam Map<String, Object> params){
        return nmapTaskFeignService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{nmapTaskId}")
    @RequiresPermissions("net:nmaptask:info")
    public R info(@PathVariable("nmapTaskId") Integer nmapTaskId){
		return nmapTaskFeignService.info(nmapTaskId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("net:nmaptask:save")
    public R save(@RequestBody NmapTaskFeignTo nmapTask){
		return nmapTaskFeignService.save(nmapTask);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("net:nmaptask:update")
    public R update(@RequestBody NmapTaskFeignTo nmapTask){
		return nmapTaskFeignService.update(nmapTask);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("net:nmaptask:delete")
    public R delete(@RequestBody Integer[] nmapTaskIds){
		return nmapTaskFeignService.delete(nmapTaskIds);
    }

}
