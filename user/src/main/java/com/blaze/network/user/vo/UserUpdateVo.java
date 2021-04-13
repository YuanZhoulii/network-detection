package com.blaze.network.user.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author yuanzhouli
 * @date 2021/4/7 - 3:33
 */
@Data
@ToString
public class UserUpdateVo {
    private Integer userId;
    /**
     * 用户邮箱
     */
    @NotBlank
    private String email;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别
     */
    private String gender;

}
