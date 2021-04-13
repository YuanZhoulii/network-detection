package com.blaze.network.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;
import com.blaze.network.user.dao.LoginLogDao;
import com.blaze.network.user.entity.LoginLogEntity;
import com.blaze.network.user.service.LoginLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service("loginLogService")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLogEntity> implements LoginLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LoginLogEntity> page = this.page(
                new Query<LoginLogEntity>().getPage(params),
                new QueryWrapper<LoginLogEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveLoginLog(Integer userId, String realIp) {
            //保存登录日志
            LoginLogEntity loginLogEntity = new LoginLogEntity();
            loginLogEntity.setIp(realIp);
            loginLogEntity.setTime(new Date());
            loginLogEntity.setUserId(userId);
            this.save(loginLogEntity);
    }

}