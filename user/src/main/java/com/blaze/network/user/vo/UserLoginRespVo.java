package com.blaze.network.user.vo;

import lombok.Data;

/**
 * @author yuanzhouli
 * @date 2021/3/27 - 18:38
 */
@Data
public class UserLoginRespVo {
    private Integer userId;
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
}
