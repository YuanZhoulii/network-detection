package com.blaze.network.save.entity;

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
 * @date 2021-03-17 23:36:42
 */
@Data
@TableName("save_port")
public class PortEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 端口id
	 */
	@TableId
	private Integer portId;
	/**
	 * 协议（tcp/udp等）
	 */
	private String protocol;
	/**
	 * 端口号
	 */
	private Integer num;
	/**
	 * 端口状态（open/close/filtered等）
	 */
	private String state;
	/**
	 * 保持端口状态原因（例如syn-ack）
	 */
	private String stateReason;
	/**
	 * ttl
	 */
	private Integer stateReasonTtl;
	/**
	 * 端口服务名
	 */
	private String serviceName;
	/**
	 * 端口服务版本
	 */
	private String serviceVersion;
	/**
	 * 对应主机id
	 */
	private Integer hostId;

}
