/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jobs.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 目标对应的定时任务（定时采集/定时考核）Entity
 * @author 目标对应的定时任务（定时采集/定时考核）
 * @version 2019-04-10
 */
@Table(name="jobs", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="corn_type", attrName="cornType", label="corn表达式类型 ", comment="corn表达式类型 （月，季度，年）作为初始数据"),
		@Column(name="corn", attrName="corn", label="定时任务表达式"),
	}, orderBy="a.id DESC"
)
public class Jobs extends DataEntity<Jobs> {
	
	private static final long serialVersionUID = 1L;
	private Integer cornType;		// corn表达式类型 （月，季度，年）作为初始数据
	private String corn;		// 定时任务表达式
	
	public Jobs() {
		this(null);
	}

	public Jobs(String id){
		super(id);
	}
	
	public Integer getCornType() {
		return cornType;
	}

	public void setCornType(Integer cornType) {
		this.cornType = cornType;
	}
	
	@Length(min=0, max=255, message="定时任务表达式长度不能超过 255 个字符")
	public String getCorn() {
		return corn;
	}

	public void setCorn(String corn) {
		this.corn = corn;
	}
	
}