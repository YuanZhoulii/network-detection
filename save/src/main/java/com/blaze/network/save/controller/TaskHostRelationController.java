package com.blaze.network.save.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blaze.network.save.entity.TaskHostRelationEntity;
import com.blaze.network.save.service.TaskHostRelationService;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.R;



/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-17 23:36:42
 */
@RestController
@RequestMapping("save/taskhostrelation")
public class TaskHostRelationController {
    @Autowired
    private TaskHostRelationService taskHostRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = taskHostRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{taskHostRelationId}")
    public R info(@PathVariable("taskHostRelationId") Integer taskHostRelationId){
		TaskHostRelationEntity taskHostRelation = taskHostRelationService.getById(taskHostRelationId);

        return R.ok().put("taskHostRelation", taskHostRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TaskHostRelationEntity taskHostRelation){
		taskHostRelationService.save(taskHostRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TaskHostRelationEntity taskHostRelation){
		taskHostRelationService.updateById(taskHostRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] taskHostRelationIds){
		taskHostRelationService.removeByIds(Arrays.asList(taskHostRelationIds));

        return R.ok();
    }

}
