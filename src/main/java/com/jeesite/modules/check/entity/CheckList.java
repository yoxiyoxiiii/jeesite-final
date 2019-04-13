/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.check.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 考核名单Entity
 * @author 考核名单
 * @version 2019-04-10
 */
@Table(name="check_list", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="plan_id", attrName="planId", label="考核计划ID"),
		@Column(name="name", attrName="name", label="名称", queryType= QueryType.LIKE),
		@Column(name="start_time", attrName="startTime", label="开始时间"),
		@Column(name="end_time", attrName="endTime", label="结束时间"),
		@Column(name="type", attrName="type", label="类型"),
		@Column(name="check_year", attrName="checkYear", label="考核年份"),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="修改时间", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class CheckList extends DataEntity<CheckList> {
	
	private static final long serialVersionUID = 1L;
	private String planId;		// 考核计划ID
	private String name;		// 名称
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Integer type;		// 类型
	private String checkYear;		// 考核年份
	
	public CheckList() {
		this(null);
	}

	public CheckList(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="考核计划ID长度不能超过 50 个字符")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	@Length(min=0, max=255, message="名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="考核年份长度不能超过 255 个字符")
	public String getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(String checkYear) {
		this.checkYear = checkYear;
	}
	
}