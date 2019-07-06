/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 考核评分Entity
 * @author BusinessPlanScore
 * @version 2019-07-02
 */
@Table(name="business_plan_score", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="check_plan_id", attrName="checkPlanId", label="考核计划ID"),
		@Column(name="check_plan_user_id", attrName="checkPlanUserId", label="考核名单ID", comment="考核名单ID(部门ID）"),
		@Column(name="plan_total_score", attrName="planTotalScore", label="最新总分数", comment="最新总分数(结果）"),
		@Column(name="description", attrName="description", label="说明"),
		@Column(name="check_status", attrName="checkStatus", label="审批状态 通过、退回、作废"),
		@Column(name="plan_total_score_old", attrName="planTotalScoreOld", label="原始得分"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="audit_by", attrName="auditBy", label="审批者"),
		@Column(name="audit_date", attrName="auditDate", label="审批时间"),
		@Column(name="target_id", attrName="targetId", label="考核明显ID"),
		@Column(name="dept_id", attrName="deptId", label="考核部门ID"),
		@Column(name="stage_id", attrName="stageId", label="考核部门ID"),
	}, orderBy="a.update_date DESC"
)
public class BusinessPlanScore extends DataEntity<BusinessPlanScore> {
	
	private static final long serialVersionUID = 1L;
	private String checkPlanId;		// 考核计划ID
	private String checkPlanUserId;		// 考核名单ID(部门ID）
	private String planTotalScore;		// 最新总分数(结果）
	private String description;		// 说明
	private Integer checkStatus;		// 审批状态 通过、退回、作废
	private String planTotalScoreOld;		// 原始得分
	private String auditBy;		// 审批者
	private Date auditDate;		// 审批时间
	private String targetId;		// 考核明显ID
	private String deptId;		// 考核部门ID
	@Setter
	@Getter
	private String stageId;		//期数

	public BusinessPlanScore() {
		this(null);
	}

	public BusinessPlanScore(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="考核计划ID长度不能超过 50 个字符")
	public String getCheckPlanId() {
		return checkPlanId;
	}

	public void setCheckPlanId(String checkPlanId) {
		this.checkPlanId = checkPlanId;
	}
	
	@Length(min=0, max=50, message="考核名单ID长度不能超过 50 个字符")
	public String getCheckPlanUserId() {
		return checkPlanUserId;
	}

	public void setCheckPlanUserId(String checkPlanUserId) {
		this.checkPlanUserId = checkPlanUserId;
	}
	
	@Length(min=0, max=255, message="最新总分数长度不能超过 255 个字符")
	public String getPlanTotalScore() {
		return planTotalScore;
	}

	public void setPlanTotalScore(String planTotalScore) {
		this.planTotalScore = planTotalScore;
	}
	
	@Length(min=0, max=255, message="说明长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	@Length(min=0, max=255, message="原始得分长度不能超过 255 个字符")
	public String getPlanTotalScoreOld() {
		return planTotalScoreOld;
	}

	public void setPlanTotalScoreOld(String planTotalScoreOld) {
		this.planTotalScoreOld = planTotalScoreOld;
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
	
	@Length(min=0, max=50, message="考核明显ID长度不能超过 50 个字符")
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	@Length(min=0, max=50, message="考核部门ID长度不能超过 50 个字符")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}