package com.blaze.network.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;
import com.blaze.network.search.dao.HostDao;
import com.blaze.network.search.entity.HostEntity;
import com.blaze.network.search.service.HostService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("hostService")
public class HostServiceImpl extends ServiceImpl<HostDao, HostEntity> implements HostService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<HostEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("host_id");
//        queryWrapper.select(HostEntity.class,i -> !i.getColumn().equals("create_time")
//                        && !i.getColumn().equals("nmap_task_id"));

//        String service= (String) params.get(RequestConstant.SERVICE);
//        String address= (String) params.get(RequestConstant.ADDRESS);
//        String area= (String) params.get(RequestConstant.AREA);
//        String os= (String) params.get(RequestConstant.OPERATING_SYSTEM);
//        String port= (String) params.get(RequestConstant.PORT);
//        if(StringUtils.isNotBlank(service)){
//            queryWrapper.or(q->{
//                q.like(RequestConstant.SERVICE,service);
//            });
//        }
//        if(StringUtils.isNotBlank(address)){
//            queryWrapper.or(q->{
//                q.like(RequestConstant.ADDRESS,address);
//            });
//        }
//        if(StringUtils.isNotBlank(area)){
//            queryWrapper.or(q->{
//                q.like(RequestConstant.AREA,area);
//            });
//        }
//        if(StringUtils.isNotBlank(os)){
//            queryWrapper.or(q->{
//                q.like(RequestConstant.OPERATING_SYSTEM,os);
//            });
//        }
//        if(StringUtils.isNotBlank(port)){
//            queryWrapper.or(q->{
//                q.like(RequestConstant.PORT,port);
//            });
//        }
        IPage<HostEntity> page = this.page(
                new Query<HostEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}