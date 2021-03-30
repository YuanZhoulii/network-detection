package com.blaze.network.save.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;

import com.blaze.network.save.dao.PortDao;
import com.blaze.network.save.entity.PortEntity;
import com.blaze.network.save.service.PortService;


@Service("portService")
public class PortServiceImpl extends ServiceImpl<PortDao, PortEntity> implements PortService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PortEntity> page = this.page(
                new Query<PortEntity>().getPage(params),
                new QueryWrapper<PortEntity>()
        );

        return new PageUtils(page);
    }

}