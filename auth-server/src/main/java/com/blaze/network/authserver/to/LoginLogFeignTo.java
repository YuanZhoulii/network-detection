package com.blaze.network.authserver.to;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author yuanzhouli
 * @date 2021/4/10 - 1:44
 */
@Data
@ToString
public class LoginLogFeignTo {
    private Integer loginLogId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 登录时间
     */
    private Date time;
    /**
     *
     */
    private String ip;
}
