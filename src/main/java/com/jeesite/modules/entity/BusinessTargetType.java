package com.jeesite.modules.entity; /**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 目标分类Entity
 * @author sanye
 * @version 2019-06-20
 */
@Table(name="business_target_type", alias="a", columns={
		@Column(name="target_type_code", attrName="targetTypeCode", label="节点编码", isPK=true),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="target_type_name", attrName="targetTypeName", label="节点名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="check_plan_id", attrName="checkPlanId", label="考核计划ID"),
		@Column(name="target_type_score", attrName="targetTypeScore", label="指标项分值"),
		@Column(name="target_type", attrName="targetType", label="基础分类"),
		@Column(name="police_type", attrName="policeType", label="条线/警种"),
		@Column(name="dept_level", attrName="deptLevel", label="部门层级"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="audit_by", attrName="auditBy", label="审批者", isQuery=false),
		@Column(name="audit_date", attrName="auditDate", label="审批时间", isQuery=false),
	}, orderBy="a.tree_sorts, a.target_type_code"
)
public class BusinessTargetType extends TreeEntity<BusinessTargetType> {
	
	private static final long serialVersionUID = 1L;
	private String targetTypeCode;		// 节点编码
	private String targetTypeName;		// 节点名称
	private String checkPlanId;		// 考核计划ID
	private Double targetTypeScore;		// 指标项分值
	private Integer targetType;		// 基础分类
	private String policeType;		// 条线/警种
	private Integer deptLevel;		// 部门层级
	private String auditBy;		// 审批者
	private Date auditDate;		// 审批时间
	
	public BusinessTargetType() {
		this(null);
	}

	public BusinessTargetType(String id){
		super(id);
	}
	
	@Override
	public BusinessTargetType getParent() {
		return parent;
	}

	@Override
	public void setParent(BusinessTargetType parent) {
		this.parent = parent;
	}
	
	public String getTargetTypeCode() {
		return targetTypeCode;
	}

	public void setTargetTypeCode(String targetTypeCode) {
		this.targetTypeCode = targetTypeCode;
	}
	
	@NotBlank(message="节点名称不能为空")
	@Length(min=0, max=200, message="节点名称长度不能超过 200 个字符")
	public String getTargetTypeName() {
		return targetTypeName;
	}

	public void setTargetTypeName(String targetTypeName) {
		this.targetTypeName = targetTypeName;
	}
	
	@NotBlank(message="考核计划ID不能为空")
	@Length(min=0, max=64, message="考核计划ID长度不能超过 64 个字符")
	public String getCheckPlanId() {
		return checkPlanId;
	}

	public void setCheckPlanId(String checkPlanId) {
		this.checkPlanId = checkPlanId;
	}
	
	public Double getTargetTypeScore() {
		return targetTypeScore;
	}

	public void setTargetTypeScore(Double targetTypeScore) {
		this.targetTypeScore = targetTypeScore;
	}
	
	public Integer getTargetType() {
		return targetType;
	}

	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}
	
	@NotBlank(message="条线/警种不能为空")
	@Length(min=0, max=255, message="条线/警种长度不能超过 255 个字符")
	public String getPoliceType() {
		return policeType;
	}

	public void setPoliceType(String policeType) {
		this.policeType = policeType;
	}
	
	@NotNull(message="部门层级不能为空")
	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
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
	
	public Date getCreateDate_gte() {
		return sqlMap.getWhere().getValue("create_date", QueryType.GTE);
	}

	public void setCreateDate_gte(Date createDate) {
		sqlMap.getWhere().and("create_date", QueryType.GTE, createDate);
	}
	
	public Date getCreateDate_lte() {
		return sqlMap.getWhere().getValue("create_date", QueryType.LTE);
	}

	public void setCreateDate_lte(Date createDate) {
		sqlMap.getWhere().and("create_date", QueryType.LTE, createDate);
	}
	
}