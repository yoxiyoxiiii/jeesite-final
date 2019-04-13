/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

/**
 * 目的的定时任务（定时采集/定时考核）Entity
 * @author 目的的定时任务（定时采集/定时考核）
 * @version 2019-04-10
 */
@Table(name="target_jobs", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="jobs_id", attrName="jobsId", label="关联的job"),
		@Column(name="target_id", attrName="targetId", label="关联的目标"),
		@Column(name="job_name", attrName="jobName", label="关联 框架中的 js_sys_job", queryType= QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class TargetJobs extends DataEntity<TargetJobs> {
	
	private static final long serialVersionUID = 1L;
	private String jobsId;		// 关联的job
	private String targetId;		// 关联的目标
	private String jobName;		// 关联 框架中的 js_sys_job
	
	public TargetJobs() {
		this(null);
	}

	public TargetJobs(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="关联的job长度不能超过 50 个字符")
	public String getJobsId() {
		return jobsId;
	}

	public void setJobsId(String jobsId) {
		this.jobsId = jobsId;
	}
	
	@Length(min=0, max=50, message="关联的目标长度不能超过 50 个字符")
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	@Length(min=0, max=255, message="关联 框架中的 js_sys_job长度不能超过 255 个字符")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
}