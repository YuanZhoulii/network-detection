package com.blaze.network.save.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;

import com.blaze.network.save.dao.TaskHostRelationDao;
import com.blaze.network.save.entity.TaskHostRelationEntity;
import com.blaze.network.save.service.TaskHostRelationService;


@Service("taskHostRelationService")
public class TaskHostRelationServiceImpl extends ServiceImpl<TaskHostRelationDao, TaskHostRelationEntity> implements TaskHostRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TaskHostRelationEntity> page = this.page(
                new Query<TaskHostRelationEntity>().getPage(params),
                new QueryWrapper<TaskHostRelationEntity>()
        );

        return new PageUtils(page);
    }

}