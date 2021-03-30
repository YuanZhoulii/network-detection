package com.blaze.network.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;
import com.blaze.network.user.common.constant.RequestConstant;
import com.blaze.network.user.common.constant.SqlConstant;
import com.blaze.network.user.dao.UserDao;
import com.blaze.network.user.entity.UserEntity;
import com.blaze.network.user.exception.EmailExistException;
import com.blaze.network.user.exception.UserNameExistException;
import com.blaze.network.user.service.UserService;
import com.blaze.network.user.util.NickName;
import com.blaze.network.user.vo.UserLoginRespVo;
import com.blaze.network.user.vo.UserLoginVo;
import com.blaze.network.user.vo.UserRegistVo;
import com.blaze.network.user.vo.UserSaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;


@Service("infoService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Transactional
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /**
         * 1.使用XML自定义SQL
         */
        /**
         * 第一种：手动封装参数
         */
//        //获取当前页码
//        long current= Long.valueOf(String.valueOf(params.get("page")));
//        //获取每页显示条数
//        long size=Long.valueOf(String.valueOf(params.get("limit")));
//        Page<UserListVo> page = new Page<>(current,size);
//        IPage<UserListVo> result = baseMapper.selectPageVo(page,params);
//        return new PageUtils(result);

        /**
         * 第二种：利用工具类封装当前页码、每页显示条数、排序字段等参数
         */
//        IPage<UserListVo> init = new Query<UserListVo>().getPage(params);
//        IPage<UserListVo> page = baseMapper.selectPageVo2(init, params);
//        return new PageUtils(page);

//        wrapper.select(SqlConstant.SELECT_FIELDS);
        /**
         * 2.使用QueryWrapper构造查询条件
         */
        QueryWrapper<UserEntity> wrapper=new QueryWrapper<>(null,SqlConstant.SELECT_FIELDS);
        //参数包含查询条件时根据username模糊查询
        String key = (String) params.get(RequestConstant.KEY);
        if(!StringUtils.isEmpty(key)){
            wrapper.like(SqlConstant.USERNAME,key);
        }
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public void regist(UserRegistVo userRegistVo) {
        //检查用户名和邮箱是否唯一,不唯一抛异常
        checkEmailUnique(userRegistVo.getEmail());
        checkUserNameUnique(userRegistVo.getUsername());

        //使用MD5加密密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(userRegistVo.getPassword());
        userRegistVo.setPassword(encode);

        //设置默认值，注册日期为当前时间，随机昵称
        UserEntity userEntity = new UserEntity();
        userEntity.setCreateTime(new Date());
        userEntity.setNickname(NickName.generateName());

        //复制vo中剩下的属性到entity中
        BeanUtils.copyProperties(userRegistVo, userEntity);

        //插入entity数据
        baseMapper.insert(userEntity);
    }

    @Override
    public void checkEmailUnique(String email) throws EmailExistException {
        //select count(*) from `user_info` where email=?
        Integer count = baseMapper.selectCount(new QueryWrapper<UserEntity>().eq("email", email));
        if (count > 0) {
            throw new EmailExistException();
        }
    }

    @Override
    public void checkUserNameUnique(String username) throws UserNameExistException {
        //select count(*) from `user_info` where username=?
        Integer count = baseMapper.selectCount(new QueryWrapper<UserEntity>().eq("username", username));
        if (count > 0) {
            throw new UserNameExistException();
        }
    }

    @Override
    public UserLoginRespVo login(UserLoginVo userLoginVo) {
        String username = userLoginVo.getUsername();
        String password = userLoginVo.getPassword();
        //SELECT * FROM `user_info` where username=? or email=?
        UserEntity userEntity = baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username).or().eq("email", username));
        if (userEntity == null) {
            return null;
        } else {
            //获取到数据库中保存的密码
            String passwordDb = userEntity.getPassword();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            //第一个参数是明文密码，第二个参数是加密后的密码
            //密码匹配
            boolean matches = bCryptPasswordEncoder.matches(password, passwordDb);
            if (matches) {
                UserLoginRespVo respVo = new UserLoginRespVo();
                BeanUtils.copyProperties(userEntity,respVo);
                return respVo;
            } else {
                return null;
            }
        }
    }

    @Override
    public void save(UserSaveVo info) {
        //检查邮箱和账号是否已存在
        checkEmailUnique(info.getEmail());
        checkUserNameUnique(info.getUsername());
        //MD5加密密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(info.getPassword());
        info.setPassword(encode);
        //没有输入用户昵称设置默认值
        String nickname = info.getNickname();
        if (nickname == null || nickname.trim().length() == 0) {
            info.setNickname(NickName.generateName());
        }
        //设置账号创建时间
        info.setCreateTime(new Date());
        //拷贝vo的属性到entity中
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(info, userEntity);
        //插入一条entity记录
        baseMapper.insert(userEntity);
    }

}