package com.blaze.network.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blaze.network.common.utils.PageUtils;
import com.blaze.network.user.entity.UserEntity;
import com.blaze.network.user.common.exception.EmailExistException;
import com.blaze.network.user.common.exception.UserNameExistException;
import com.blaze.network.user.vo.*;

import java.util.Map;

/**
 * 
 *
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-02-06 00:57:04
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void regist(UserRegistVo userRegistVo);
    void checkEmailUnique(String email) throws EmailExistException;
    void checkUserNameUnique(String username) throws UserNameExistException;

    UserLoginRespVo login(UserLoginVo userLoginVo);

    void save(UserSaveVo info);
    UserLoginRespVo myUpdateById(UserUpdateVo userUpdateVo);
}

