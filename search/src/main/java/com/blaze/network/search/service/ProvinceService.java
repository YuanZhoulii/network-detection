package com.blaze.network.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.search.entity.ProvinceEntity;

import java.util.Map;

/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-04-09 23:34:06
 */
public interface ProvinceService extends IService<ProvinceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

