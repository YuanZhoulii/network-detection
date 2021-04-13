package com.blaze.network.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;
import com.blaze.network.search.dao.ProvinceDao;
import com.blaze.network.search.entity.ProvinceEntity;
import com.blaze.network.search.service.ProvinceService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("provinceService")
public class ProvinceServiceImpl extends ServiceImpl<ProvinceDao, ProvinceEntity> implements ProvinceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProvinceEntity> page = this.page(
                new Query<ProvinceEntity>().getPage(params),
                new QueryWrapper<ProvinceEntity>()
        );

        return new PageUtils(page);
    }

}