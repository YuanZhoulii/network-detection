package io.renren.modules.net.vo;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/3/13 - 0:12
 */
@Data
public class UserManageSaveVo {
    /**
     * 用户账号
     */
    //注解规则冲突
//    @NotEmpty(message = "账号不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$",message = "账号必须以字母开头，之后允许字母数字下划线，总长度5-16")
    private String username;
//    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$",message = "密码至少分别包含一个大、小写字母和数字，总长度8-16")
    private String password;
    /**
     * 用户邮箱
     */
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 昵称
     */
    @Size(max = 20,min = 0,message = "昵称长度不超过20")
    private String nickname;
    /**
     * 手机号
     */
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$",message = "请输入正确的手机号")
    private String mobile;
    /**
     * 性别
     */
    //min,max,size,length
    @Pattern(regexp = "(男|女|\\s)?",message = "请输入性别或留空（男/女）")
    private String gender;
    /**
     * 注册时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
