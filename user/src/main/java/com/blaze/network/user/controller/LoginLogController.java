package com.blaze.network.user.controller;

import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.R;
import com.blaze.network.user.entity.LoginLogEntity;
import com.blaze.network.user.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-12 23:02:43
 */
@RestController
@RequestMapping("user/loginlog")
public class LoginLogController {
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = loginLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{loginLogId}")
    public R info(@PathVariable("loginLogId") Integer loginLogId){
		LoginLogEntity loginLog = loginLogService.getById(loginLogId);

        return R.ok().put("loginLog", loginLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LoginLogEntity loginLog){
		loginLogService.save(loginLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LoginLogEntity loginLog){
		loginLogService.updateById(loginLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] loginLogIds){
		loginLogService.removeByIds(Arrays.asList(loginLogIds));

        return R.ok();
    }

}
