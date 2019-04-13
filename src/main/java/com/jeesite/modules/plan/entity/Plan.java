/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 考核计划Entity
 * @author 考核计划
 * @version 2019-04-10
 */
@Table(name="plan", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="计划名称", queryType= QueryType.LIKE),
		@Column(name="plan_type", attrName="planType", label="考核计划分类"),
		@Column(name="create_year", attrName="createYear", label="计划创建年"),
		@Column(name="create_month", attrName="createMonth", label="创建月"),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="department_id", attrName="departmentId", label="部门Id，考核计划归属的部门"),
		@Column(name="order", attrName="order", label="序号"),
		@Column(name="major_type", attrName="majorType", label="专业类型 ", comment="专业类型 （1 管理计划、2 管控体系、3 制度管理）"),
		@Column(name="user_id", attrName="userId", label="负责人"),
		@Column(name="duty_id", attrName="dutyId", label="责任人"),
		@Column(name="complete_time", attrName="completeTime", label="完成时间"),
		@Column(name="key_step", attrName="keyStep", label="关键措施"),
		@Column(name="result", attrName="result", label="工作成果"),
		@Column(name="status", attrName="status", label="计划状态 1 未启动、2 已完成、3 执行中、4 废除", isUpdate=false),
		@Column(name="urgent", attrName="urgent", label="紧急程度"),
		@Column(name="is_approval", attrName="isApproval", label="审核是否通过 1 通过 0 不通过"),
		@Column(name="approval_msg", attrName="approvalMsg", label="审批备注"),
		@Column(name="approval_by", attrName="approvalBy", label="审批人"),
		@Column(name="check_status", attrName="checkStatus", label="评价状态 ", comment="评价状态 （1 未启动、2 进行中、3 已评价、4 已审核、5 已结束）"),
	}, orderBy="a.update_date DESC"
)
public class Plan extends DataEntity<Plan> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 计划名称
	private String planType;		// 考核计划分类
	private String createYear;		// 计划创建年
	private String createMonth;		// 创建月
	private String departmentId;		// 部门Id，考核计划归属的部门
	private String order;		// 序号
	private Integer majorType;		// 专业类型 （1 管理计划、2 管控体系、3 制度管理）
	private String userId;		// 负责人
	private String dutyId;		// 责任人
	private Date completeTime;		// 完成时间
	private String keyStep;		// 关键措施
	private String result;		// 工作成果
	private String urgent;		// 紧急程度
	private String isApproval;		// 审核是否通过 1 通过 0 不通过
	private String approvalMsg;		// 审批备注
	private String approvalBy;		// 审批人
	private Integer checkStatus;		// 评价状态 （1 未启动、2 进行中、3 已评价、4 已审核、5 已结束）
	
	public Plan() {
		this(null);
	}

	public Plan(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="计划名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="考核计划分类长度不能超过 255 个字符")
	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}
	
	@Length(min=0, max=255, message="计划创建年长度不能超过 255 个字符")
	public String getCreateYear() {
		return createYear;
	}

	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}
	
	@Length(min=0, max=255, message="创建月长度不能超过 255 个字符")
	public String getCreateMonth() {
		return createMonth;
	}

	public void setCreateMonth(String createMonth) {
		this.createMonth = createMonth;
	}
	
	@Length(min=0, max=50, message="部门Id，考核计划归属的部门长度不能超过 50 个字符")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Length(min=0, max=255, message="序号长度不能超过 255 个字符")
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public Integer getMajorType() {
		return majorType;
	}

	public void setMajorType(Integer majorType) {
		this.majorType = majorType;
	}
	
	@Length(min=0, max=100, message="负责人长度不能超过 100 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=100, message="责任人长度不能超过 100 个字符")
	public String getDutyId() {
		return dutyId;
	}

	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	
	@Length(min=0, max=255, message="关键措施长度不能超过 255 个字符")
	public String getKeyStep() {
		return keyStep;
	}

	public void setKeyStep(String keyStep) {
		this.keyStep = keyStep;
	}
	
	@Length(min=0, max=255, message="工作成果长度不能超过 255 个字符")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=255, message="紧急程度长度不能超过 255 个字符")
	public String getUrgent() {
		return urgent;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}
	
	@Length(min=0, max=255, message="审核是否通过 1 通过 0 不通过长度不能超过 255 个字符")
	public String getIsApproval() {
		return isApproval;
	}

	public void setIsApproval(String isApproval) {
		this.isApproval = isApproval;
	}
	
	@Length(min=0, max=255, message="审批备注长度不能超过 255 个字符")
	public String getApprovalMsg() {
		return approvalMsg;
	}

	public void setApprovalMsg(String approvalMsg) {
		this.approvalMsg = approvalMsg;
	}
	
	@Length(min=0, max=255, message="审批人长度不能超过 255 个字符")
	public String getApprovalBy() {
		return approvalBy;
	}

	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}
	
	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}