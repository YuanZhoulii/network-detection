package com.blaze.network.save.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.save.entity.NmapTaskEntity;

import java.util.Map;

/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-17 23:36:42
 */
public interface NmapTaskService extends IService<NmapTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

