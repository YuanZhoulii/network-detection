package com.blaze.network.search.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-03-17 23:36:42
 */
@Data
@TableName("save_nmap_task")
public class NmapTaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * nmap任务id
	 */
	@TableId
	private Integer nmapTaskId;
	/**
	 * 该任务执行的nmap命令
	 */
	private String args;
	/**
	 * 扫描类型（syn/ack等）
	 */
	private String scanType;
	/**
	 * 扫描协议（TCP/UDP等）
	 */
	private String protocol;
	/**
	 * 任务耗时
	 */
	private String elapsed;
	/**
	 * 任务开始时间
	 */
	private Date startTime;
	/**
	 * 任务结束时间
	 */
	private Date finishTime;
	/**
	 * 任务总结
	 */
	private String summary;
	/**
	 * 任务运行状态
	 */
	private String runStatus;
	/**
	 * 本次扫描存活主机数
	 */
	private Integer hostsUp;
	/**
	 * 本次扫描未存活主机数
	 */
	private Integer hostsDown;

}
