/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplan.entity;

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
 * 考核计划Entity
 * @author BusinessCheckPlan
 * @version 2019-04-25
 */
@Table(name="business_check_plan", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="temp_id", attrName="tempId", label="模板"),
		@Column(name="plan_major_type", attrName="planMajorType", label="专业类型"),
		@Column(name="plan_name", attrName="planName", label="计划名称", queryType=QueryType.LIKE),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="plan_start_time", attrName="planStartTime", label="开始时间"),
		@Column(name="plan_end_time", attrName="planEndTime", label="结束时间"),
		@Column(name="plan_scoring_start_time", attrName="planScoringStartTime", label="评分开始时间"),
		@Column(name="plan_scoring_end_time", attrName="planScoringEndTime", label="评分结束时间"),
		@Column(name="plan_check_user_id", attrName="planCheckUserId", label="负责人"),
		@Column(name="plan_duty_user_id", attrName="planDutyUserId", label="责任人"),
		@Column(name="plan_work_results", attrName="planWorkResults", label="工作成果"),
		@Column(name="plan_key", attrName="planKey", label="关键措施分析"),
		@Column(name="plan_status", attrName="planStatus", label="状态"),
		@Column(name="plan_weight", attrName="planWeight", label="权重%"),
	}, orderBy="a.update_date DESC"
)
public class BusinessCheckPlan extends DataEntity<BusinessCheckPlan> {
	
	private static final long serialVersionUID = 1L;
	private String tempId;		// 模板
	private Integer planMajorType;		// 专业类型
	private String planName;		// 计划名称
	private Date planStartTime;		// 开始时间
	private Date planEndTime;		// 结束时间
	private Date planScoringStartTime;		// 评分开始时间
	private Date planScoringEndTime;		// 评分结束时间
	private String planCheckUserId;		// 负责人
	private String planDutyUserId;		// 责任人
	private String planWorkResults;		// 工作成果
	private String planKey;		// 关键措施分析
	private Integer planStatus;		// 状态
	private Integer planWeight;		// 权重%
	
	public BusinessCheckPlan() {
		this(null);
	}

	public BusinessCheckPlan(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="模板长度不能超过 50 个字符")
	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	
	public Integer getPlanMajorType() {
		return planMajorType;
	}

	public void setPlanMajorType(Integer planMajorType) {
		this.planMajorType = planMajorType;
	}
	
	@Length(min=0, max=255, message="计划名称长度不能超过 255 个字符")
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanScoringStartTime() {
		return planScoringStartTime;
	}

	public void setPlanScoringStartTime(Date planScoringStartTime) {
		this.planScoringStartTime = planScoringStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanScoringEndTime() {
		return planScoringEndTime;
	}

	public void setPlanScoringEndTime(Date planScoringEndTime) {
		this.planScoringEndTime = planScoringEndTime;
	}
	
	@Length(min=0, max=50, message="负责人长度不能超过 50 个字符")
	public String getPlanCheckUserId() {
		return planCheckUserId;
	}

	public void setPlanCheckUserId(String planCheckUserId) {
		this.planCheckUserId = planCheckUserId;
	}
	
	@Length(min=0, max=50, message="责任人长度不能超过 50 个字符")
	public String getPlanDutyUserId() {
		return planDutyUserId;
	}

	public void setPlanDutyUserId(String planDutyUserId) {
		this.planDutyUserId = planDutyUserId;
	}
	
	@Length(min=0, max=255, message="工作成果长度不能超过 255 个字符")
	public String getPlanWorkResults() {
		return planWorkResults;
	}

	public void setPlanWorkResults(String planWorkResults) {
		this.planWorkResults = planWorkResults;
	}
	
	@Length(min=0, max=255, message="关键措施分析长度不能超过 255 个字符")
	public String getPlanKey() {
		return planKey;
	}

	public void setPlanKey(String planKey) {
		this.planKey = planKey;
	}
	
	public Integer getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}
	
	public Integer getPlanWeight() {
		return planWeight;
	}

	public void setPlanWeight(Integer planWeight) {
		this.planWeight = planWeight;
	}
	
}