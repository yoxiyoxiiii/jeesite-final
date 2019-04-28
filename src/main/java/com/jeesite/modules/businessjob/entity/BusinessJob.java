/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessjob.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 定时任务Entity
 * @author BusinessJob
 * @version 2019-04-28
 */
@Table(name="business_job", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="targetId", label="考核目标"),
		@Column(name="plan_id", attrName="planId", label="考核计划"),
		@Column(name="corn", attrName="corn", label="执行周期"),
		@Column(name="job_name", attrName="jobName", label="任务名称", queryType=QueryType.LIKE),
		@Column(name="job_group", attrName="jobGroup", label="任务分组"),
		@Column(name="bean_name", attrName="beanName", label="bean名称", queryType=QueryType.LIKE),
		@Column(name="job_status", attrName="jobStatus", label="任务状态"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="update_time", attrName="updateTime", label="更新时间"),
	}, orderBy="a.id DESC"
)
public class BusinessJob extends DataEntity<BusinessJob> {
	
	private static final long serialVersionUID = 1L;
	private String targetId;		// 考核目标
	private String planId;		// 考核计划
	private String corn;		// 执行周期
	private String jobName;		// 任务名称
	private String jobGroup;		// 任务分组
	private String beanName;		// bean名称
	private String jobStatus;		// 任务状态
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public BusinessJob() {
		this(null);
	}

	public BusinessJob(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="考核目标长度不能超过 50 个字符")
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	@Length(min=0, max=50, message="考核计划长度不能超过 50 个字符")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	@Length(min=0, max=255, message="执行周期长度不能超过 255 个字符")
	public String getCorn() {
		return corn;
	}

	public void setCorn(String corn) {
		this.corn = corn;
	}
	
	@Length(min=0, max=255, message="任务名称长度不能超过 255 个字符")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Length(min=0, max=255, message="任务分组长度不能超过 255 个字符")
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	@Length(min=0, max=255, message="bean名称长度不能超过 255 个字符")
	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	@Length(min=0, max=255, message="任务状态长度不能超过 255 个字符")
	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}