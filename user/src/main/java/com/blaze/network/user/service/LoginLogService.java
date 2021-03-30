package com.blaze.network.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.user.entity.LoginLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-12 23:02:43
 */
public interface LoginLogService extends IService<LoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

