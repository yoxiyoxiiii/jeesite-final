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
 * 民主测评记录Entity
 * @author sanye
 * @version 2019-06-01
 */
@Table(name="biz_evalu_data", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="evalu_lib_id", attrName="evaluLibId", label="所属测评项"),
		@Column(name="dept_id", attrName="deptId", label="参评单位"),
		@Column(name="score", attrName="score", label="得分", comment="得分(仅记录选项)"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="audit_by", attrName="auditBy", label="审批者"),
		@Column(name="audit_date", attrName="auditDate", label="审批时间"),
	}, orderBy="a.update_date DESC"
)
public class EvaluData extends DataEntity<EvaluData> {
	
	private static final long serialVersionUID = 1L;
	private String evaluLibId;		// 所属测评项
	private String deptId;		// 参评单位
	private String score;		// 得分(仅记录选项)
	private String auditBy;		// 审批者
	private Date auditDate;		// 审批时间
	
	public EvaluData() {
		this(null);
	}

	public EvaluData(String id){
		super(id);
	}
	
	@NotBlank(message="所属测评项不能为空")
	@Length(min=0, max=64, message="所属测评项长度不能超过 64 个字符")
	public String getEvaluLibId() {
		return evaluLibId;
	}

	public void setEvaluLibId(String evaluLibId) {
		this.evaluLibId = evaluLibId;
	}
	
	@NotBlank(message="参评单位不能为空")
	@Length(min=0, max=64, message="参评单位长度不能超过 64 个字符")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@NotBlank(message="得分不能为空")
	@Length(min=0, max=1000, message="得分长度不能超过 1000 个字符")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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