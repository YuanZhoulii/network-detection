package com.blaze.network.authserver.vo;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author yuanzhouli
 * @date 2021/2/19 - 17:26
 */
@Data
public class AuthLoginVo {
//    @NotEmpty(message = "用户名必须提交")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$",message = "账号必须以字母开头，之后允许字母数字下划线，总长度5-16")
    String username;
//    @NotEmpty(message = "密码必须提交")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$",message = "密码至少分别包含一个大、小写字母和数字，此外可以使用特殊字符，总长度8-16")
    String password;
}
