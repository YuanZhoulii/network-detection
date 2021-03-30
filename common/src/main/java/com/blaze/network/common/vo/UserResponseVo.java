package com.blaze.network.common.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author yuanzhouli
 * @date 2021/2/20 - 14:23
 */
@Data
@ToString
public class UserResponseVo  {


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
