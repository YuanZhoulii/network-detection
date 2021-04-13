package com.blaze.network.search.controller;

import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.R;
import com.blaze.network.search.entity.ProvinceEntity;
import com.blaze.network.search.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-04-09 23:34:06
 */
@RestController
@RequestMapping("search/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = provinceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{provinceId}")
    public R info(@PathVariable("provinceId") Integer provinceId) {
        ProvinceEntity province = provinceService.getById(provinceId);

        return R.ok().put("province", province);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ProvinceEntity province) {
        provinceService.save(province);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ProvinceEntity province) {
        provinceService.updateById(province);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] provinceIds) {
        provinceService.removeByIds(Arrays.asList(provinceIds));

        return R.ok();
    }

}
