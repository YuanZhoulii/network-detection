package com.blaze.network.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/3/13 - 0:40
 */
@Data
public class UserListVo {

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
    /**
     * 注册时间
     */

//    //前端传给后端的日期字符串自动转换为指定格式的Date
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    //后端传给前端时自动转换为指定格式字符串，时区必须设置
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
