package com.blaze.network.save.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;
import com.blaze.network.save.dao.NmapTaskDao;
import com.blaze.network.save.entity.NmapTaskEntity;
import com.blaze.network.save.service.NmapTaskService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("nmapTaskService")
public class NmapTaskServiceImpl extends ServiceImpl<NmapTaskDao, NmapTaskEntity> implements NmapTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NmapTaskEntity> page = this.page(
                new Query<NmapTaskEntity>().getPage(params),
                new QueryWrapper<NmapTaskEntity>()
        );

        return new PageUtils(page);
    }

}