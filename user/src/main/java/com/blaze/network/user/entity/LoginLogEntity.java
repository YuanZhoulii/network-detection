package com.blaze.network.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-12 23:02:43
 */
@Data
@TableName("user_login_log")
public class LoginLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 登录日志id
	 */
	@TableId
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
