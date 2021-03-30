package com.blaze.network.user.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author yuanzhouli
 * @date 2021/2/17 - 22:49
 */
@Data
@ToString
public class UserRegistVo {
    private String username;
    private String password;
    private String email;
}
