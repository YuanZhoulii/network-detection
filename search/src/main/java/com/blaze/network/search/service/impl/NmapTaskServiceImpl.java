package com.blaze.network.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;
import com.blaze.network.search.dao.NmapTaskDao;
import com.blaze.network.search.entity.NmapTaskEntity;
import com.blaze.network.search.service.NmapTaskService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("nmapTaskService")
public class NmapTaskServiceImpl extends ServiceImpl<NmapTaskDao, NmapTaskEntity> implements NmapTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<NmapTaskEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("nmap_task_id");
        IPage<NmapTaskEntity> page = this.page(
                new Query<NmapTaskEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}