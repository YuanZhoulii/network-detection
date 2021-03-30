package com.blaze.network.authserver.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/2/22 - 18:49
 */
@Data
@ToString
public class UserVo {
    private Integer userId;
    /**
     * 用户密码，使用MD5算法加密
     */
    private String password;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别
     */
    private String gender;
    /**
     * 注册时间
     */
    private Date createTime;
}
