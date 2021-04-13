package com.blaze.network.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;
import com.blaze.network.search.dao.PortDao;
import com.blaze.network.search.entity.PortEntity;
import com.blaze.network.search.service.PortService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("portService")
public class PortServiceImpl extends ServiceImpl<PortDao, PortEntity> implements PortService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PortEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("port_id");
        IPage<PortEntity> page = this.page(
                new Query<PortEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}