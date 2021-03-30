package com.blaze.network.save.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-17 23:36:42
 */
@Data
@TableName("save_host")
public class HostEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer hostId;
	/**
	 * 主机地址
	 */
	private String address;
	/**
	 * 地址类型（IPV4/IPV6）
	 */
	private String addressType;
	/**
	 * 主机存活状态（up/down）
	 */
	private String statusState;
	/**
	 * 处于某种存活状态的原因
	 */
	private String statusReason;
	/**
	 * ICMP包的转发次数（跳数）
	 */
	private Integer reasonTtl;
	/**
	 * 所属地区
	 */
	private String area;
	/**
	 * 操作系统
	 */
	private String os;

}
