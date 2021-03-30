package com.blaze.network.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/3/14 - 2:42
 */
@Data
public class UserSaveVo {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String mobile;
    private String gender;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
