package com.blaze.network.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blaze.network.user.entity.UserEntity;
import com.blaze.network.user.vo.UserListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-02-06 00:57:04
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    IPage<UserListVo> selectPageVo(Page<?> page, @Param("params") Map<String, Object> params);
    IPage<UserListVo> selectPageVo2(IPage<?> page, @Param("params") Map<String, Object> params);

}
