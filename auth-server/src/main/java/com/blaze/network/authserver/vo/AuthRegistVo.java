package com.blaze.network.authserver.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author yuanzhouli
 * @date 2021/2/17 - 22:49
 */
@Data
@ToString
public class AuthRegistVo {

    //以下两种注解会冲突
//    @NotEmpty(message = "用户名必须提交")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$",message = "账号必须以字母开头，之后允许字母数字下划线，总长度5-16")
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$",message = "密码至少分别包含一个大、小写字母和数字，此外可以使用特殊字符，总长度8-16")
    private String password;

    private String checkPass;
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}
