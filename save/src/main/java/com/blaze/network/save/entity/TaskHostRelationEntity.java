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
@TableName("save_task_host_relation")
public class TaskHostRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer taskHostRelationId;
	/**
	 * 
	 */
	private Integer hostId;
	/**
	 * 
	 */
	private Integer taskId;

}
