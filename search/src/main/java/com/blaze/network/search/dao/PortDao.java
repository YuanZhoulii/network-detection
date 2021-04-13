package com.blaze.network.search.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blaze.network.search.entity.PortEntity;
import com.blaze.network.search.vo.pie.PieDataItemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-17 23:36:42
 */
@Mapper
public interface PortDao extends BaseMapper<PortEntity> {

    List<PieDataItemVo> queryForServiceData();
    List<PieDataItemVo> queryForPortData();
}
