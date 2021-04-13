package com.blaze.network.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.search.entity.HostEntity;

import java.util.Map;

/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-17 23:36:42
 */
public interface HostService extends IService<HostEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

