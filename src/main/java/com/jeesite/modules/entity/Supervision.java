/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 督查督办Entity
 * @author sanye
 * @version 2019-06-04
 */
@Table(name="biz_supervision", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="job_id", attrName="jobId", label="监督事项"),
		@Column(name="requirement", attrName="requirement", label="督查督办工作要求"),
		@Column(name="limit_date", attrName="limitDate", label="督办时限"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Supervision extends DataEntity<Supervision> {
	
	private static final long serialVersionUID = 1L;
	private String jobId;		// 监督事项
	private String requirement;		// 督查督办工作要求
	private Date limitDate;		// 督办时限
	
	public Supervision() {
		this(null);
	}

	public Supervision(String id){
		super(id);
	}
	
	@NotBlank(message="监督事项不能为空")
	@Length(min=0, max=64, message="监督事项长度不能超过 64 个字符")
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	@NotBlank(message="督查督办工作要求不能为空")
	@Length(min=0, max=2000, message="督查督办工作要求长度不能超过 2000 个字符")
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	
}