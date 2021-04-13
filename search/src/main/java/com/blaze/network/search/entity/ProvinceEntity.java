package com.blaze.network.search.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author yuanzhouli
 * @email yuanzhouli1997@163.com
 * @date 2021-04-09 23:34:06
 */
@Data
@TableName("save_province")
public class ProvinceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer provinceId;
	/**
	 * 省份名
	 */
	private String name;

}
