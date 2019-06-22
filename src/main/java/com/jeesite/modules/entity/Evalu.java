/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 民主测评Entity
 * @author sanye
 * @version 2019-05-16
 */
@Table(name="biz_evalu", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="title", attrName="title", label="标题", queryType=QueryType.LIKE),
		@Column(name="start_time", attrName="startTime", label="获奖时间"),
		@Column(name="end_time", attrName="endTime", label="获奖时间", isQuery=false),
		@Column(name="dept_id", attrName="deptId", label="参评单位", isQuery=false),
		@Column(name="exe_user", attrName="exeUser", label="实施人员", isQuery=false),
		@Column(name="score", attrName="score", label="总分"),
		@Column(name="has_opinion", attrName="hasOpinion", label="是否收集测评意见", isQuery=false),
		@Column(name="form_template", attrName="formTemplate", label="测评模板", isQuery=false),
		@Column(includeEntity=DataEntity.class),
		@Column(name="audit_by", attrName="auditBy", label="审批者", isInsert=false, isQuery=false),
		@Column(name="audit_date", attrName="auditDate", label="审批时间", isInsert=false, isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class Evalu extends DataEntity<Evalu> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private Date startTime;		// 获奖时间
	private Date endTime;		// 获奖时间
	private String deptId;		// 参评单位
	private String exeUser;		// 实施人员
	private Double score;		// 总分
	private String hasOpinion;		// 是否收集测评意见
	private String formTemplate;		// 测评模板
	private String auditBy;		// 审批者
	private Date auditDate;		// 审批时间
	
	public Evalu() {
		this(null);
	}

	public Evalu(String id){
		super(id);
	}
	
	@NotBlank(message="标题不能为空")
	@Length(min=0, max=200, message="标题长度不能超过 200 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="获奖时间不能为空")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="获奖时间不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@NotBlank(message="参评单位不能为空")
	@Length(min=0, max=500, message="参评单位长度不能超过 500 个字符")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@NotBlank(message="实施人员不能为空")
	@Length(min=0, max=500, message="实施人员长度不能超过 500 个字符")
	public String getExeUser() {
		return exeUser;
	}

	public void setExeUser(String exeUser) {
		this.exeUser = exeUser;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	@NotBlank(message="是否收集测评意见不能为空")
	@Length(min=0, max=3, message="是否收集测评意见长度不能超过 3 个字符")
	public String getHasOpinion() {
		return hasOpinion;
	}

	public void setHasOpinion(String hasOpinion) {
		this.hasOpinion = hasOpinion;
	}
	
	@NotBlank(message="测评模板不能为空")
	@Length(min=0, max=100, message="测评模板长度不能超过 100 个字符")
	public String getFormTemplate() {
		return formTemplate;
	}

	public void setFormTemplate(String formTemplate) {
		this.formTemplate = formTemplate;
	}
	
	@Length(min=0, max=64, message="审批者长度不能超过 64 个字符")
	public String getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	public Date getStartTime_gte() {
		return sqlMap.getWhere().getValue("start_time", QueryType.GTE);
	}

	public void setStartTime_gte(Date startTime) {
		sqlMap.getWhere().and("start_time", QueryType.GTE, startTime);
	}
	
	public Date getStartTime_lte() {
		return sqlMap.getWhere().getValue("start_time", QueryType.LTE);
	}

	public void setStartTime_lte(Date startTime) {
		sqlMap.getWhere().and("start_time", QueryType.LTE, startTime);
	}
	
}