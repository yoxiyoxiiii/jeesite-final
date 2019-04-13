/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 任务Entity
 * @author 任务
 * @version 2019-04-10
 */
@Table(name="task", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="任务名称", queryType= QueryType.LIKE),
		@Column(name="plan_id", attrName="planId", label="该任务关联的考核计划"),
		@Column(name="user_id", attrName="userId", label="该任务关联的人/部门/岗位"),
		@Column(name="target_id", attrName="targetId", label="该任务关联的指标id 冗余字段"),
		@Column(name="description", attrName="description", label="任务描述信息"),
		@Column(name="status", attrName="status", label="任务处理结果  ", comment="任务处理结果  （已报、退回、查看）", isUpdate=false),
		@Column(name="report_status", attrName="reportStatus", label="任务上报情况 ", comment="任务上报情况 （应报、已报、未报）"),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="start_time", attrName="startTime", label="任务开始时间"),
		@Column(name="end_time", attrName="endTime", label="任务结束时间"),
	}, orderBy="a.update_date DESC"
)
public class Task extends DataEntity<Task> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 任务名称
	private String planId;		// 该任务关联的考核计划
	private Long userId;		// 该任务关联的人/部门/岗位
	private String targetId;		// 该任务关联的指标id 冗余字段
	private String description;		// 任务描述信息
	private String reportStatus;		// 任务上报情况 （应报、已报、未报）
	private Date startTime;		// 任务开始时间
	private Date endTime;		// 任务结束时间
	
	public Task() {
		this(null);
	}

	public Task(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="任务名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="该任务关联的考核计划长度不能超过 50 个字符")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=50, message="该任务关联的指标id 冗余字段长度不能超过 50 个字符")
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	@Length(min=0, max=255, message="任务描述信息长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="任务上报情况 长度不能超过 255 个字符")
	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
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
	
}