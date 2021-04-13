package com.blaze.network.search.controller;

import com.blaze.network.common.utils.R;
import com.blaze.network.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 1:34
 */
@RequestMapping("search/search")
@RestController
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping("/map")
    public R map(){
        return R.ok().setData(searchService.getMapData());
    }

    @RequestMapping("/bar")
    public R bar(){
        return R.ok().setData(searchService.getBarData());
    }

    @RequestMapping("/pie")
    public R pie(){
        return R.ok().setData(searchService.getPieData());
    }
}
