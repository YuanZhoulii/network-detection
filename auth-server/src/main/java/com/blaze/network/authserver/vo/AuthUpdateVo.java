package com.blaze.network.authserver.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author yuanzhouli
 * @date 2021/2/22 - 18:49
 */
@Data
@ToString
public class AuthUpdateVo {
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    /**
     * 用户邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    /**
     * 手机号
     */
//    @NotBlank(message = "手机号不能为空")
    private String mobile;
    /**
     * 旧密码
     */
    @Pattern(regexp = "((^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$)|\\s)?",message = "至少分别包含一个大、小写字母和数字")
    private String oldPassword;
    /**
     * 新密码
     */
    @Pattern(regexp = "((^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$)|\\s)?",message = "至少分别包含一个大、小写字母和数字")
    private String newPassword;
    /**
     * 性别
     */
    @Pattern(regexp = "(男|女|\\s)?",message = "请输入性别或留空（男/女）")
    private String gender;

}
