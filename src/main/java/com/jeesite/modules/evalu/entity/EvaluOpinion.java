/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.entity;

import javax.validation.constraints.NotBlank;
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
 * 民主测评意见Entity
 * @author sanye
 * @version 2019-05-16
 */
@Table(name="biz_evalu_opinion", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="evalu_id", attrName="evaluId", label="所属测评表"),
		@Column(name="dept_id", attrName="deptId", label="参评单位"),
		@Column(name="opinion", attrName="opinion", label="测评意见"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="audit_by", attrName="auditBy", label="审批者", isQuery=false),
		@Column(name="audit_date", attrName="auditDate", label="审批时间", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class EvaluOpinion extends DataEntity<EvaluOpinion> {
	
	private static final long serialVersionUID = 1L;
	private String evaluId;		// 所属测评表
	private String deptId;		// 参评单位
	private String opinion;		// 测评意见
	private String auditBy;		// 审批者
	private Date auditDate;		// 审批时间
	
	public EvaluOpinion() {
		this(null);
	}

	public EvaluOpinion(String id){
		super(id);
	}
	
	@NotBlank(message="所属测评表不能为空")
	@Length(min=0, max=64, message="所属测评表长度不能超过 64 个字符")
	public String getEvaluId() {
		return evaluId;
	}

	public void setEvaluId(String evaluId) {
		this.evaluId = evaluId;
	}
	
	@NotBlank(message="参评单位不能为空")
	@Length(min=0, max=500, message="参评单位长度不能超过 500 个字符")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@NotBlank(message="测评意见不能为空")
	@Length(min=0, max=1000, message="测评意见长度不能超过 1000 个字符")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
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
	
}