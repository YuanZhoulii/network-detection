package com.blaze.network.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.common.utils.Query;
import com.blaze.network.common.utils.StringUtils;
import com.blaze.network.user.common.constant.RequestConstant;
import com.blaze.network.user.common.constant.SqlConstant;
import com.blaze.network.user.dao.LoginLogDao;
import com.blaze.network.user.dao.UserDao;
import com.blaze.network.user.entity.UserEntity;
import com.blaze.network.user.common.exception.*;
import com.blaze.network.user.service.UserService;
import com.blaze.network.user.common.util.NickName;
import com.blaze.network.user.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("infoService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    LoginLogDao loginLogDao;

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
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>(null, SqlConstant.SELECT_FIELDS);
        //参数包含查询条件时根据username模糊查询
        String key = (String) params.get(RequestConstant.KEY);
        if (StringUtils.isNotBlank(key)) {
            wrapper.like(SqlConstant.USERNAME, key);
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
                BeanUtils.copyProperties(userEntity, respVo);
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

    @Override
    public UserLoginRespVo myUpdateById(UserUpdateVo userUpdateVo) {
        //查询出用户输入的昵称、邮箱、手机是否已被使用，如果已使用抛异常
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.ne("user_id", userUpdateVo.getUserId());
        wrapper.and((w) -> {
            w.eq("nickname", userUpdateVo.getNickname())
                    .or().eq("email", userUpdateVo.getEmail())
                    .or().eq("mobile", userUpdateVo.getMobile());
        });
        List<UserEntity> userEntities = userDao.selectList(wrapper);
        if (userEntities != null) {
            for (UserEntity userEntity : userEntities) {
                if (userEntity.getNickname().equals(userUpdateVo.getNickname())) {
                    throw new NicknameExistException();
                }
                if (userEntity.getEmail().equals(userUpdateVo.getEmail())) {
                    throw new EmailExistException();
                }
                if (userEntity.getMobile().equals(userUpdateVo.getMobile())) {
                    throw new MobileExistException();
                }

            }
        }

        UserEntity userEntity = userDao.selectById(userUpdateVo.getUserId());

        //匹配用户输入的旧密码是否正确
        String oldPassword = userUpdateVo.getOldPassword();
        String newPassword = userUpdateVo.getNewPassword();
        if (StringUtils.isNotBlank(oldPassword)&&StringUtils.isNotBlank(newPassword)) {
            String oldPasswordDb = userEntity.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean matches = encoder.matches(oldPassword, oldPasswordDb);
            //旧密码不正确
            if (!matches) {
                throw new WrongOldPasswordException();
            }

            //新密码与原密码相同
            if (newPassword.equals(oldPassword)) {
                throw new NewPasswordEqualsOldPasswordException();
            }
            userEntity.setPassword(encoder.encode(newPassword));
        }
        if(StringUtils.isNotBlank(userUpdateVo.getEmail())){
            String entityEmail = userEntity.getEmail();
            userEntity.setEmail(userUpdateVo.getEmail());
        }
        if(StringUtils.isNotBlank(userUpdateVo.getMobile())){
            userEntity.setMobile(userUpdateVo.getMobile());
        }
        if(StringUtils.isNotBlank(userUpdateVo.getNickname())){
            userEntity.setNickname(userUpdateVo.getNickname());
        }
        if(StringUtils.isNotBlank(userUpdateVo.getGender())){
            userEntity.setGender(userUpdateVo.getGender());
        }


        userDao.updateById(userEntity);
        UserLoginRespVo loginRespVo = new UserLoginRespVo();
        BeanUtils.copyProperties(userEntity, loginRespVo);
        return loginRespVo;
    }
}