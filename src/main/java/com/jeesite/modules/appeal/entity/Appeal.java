/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.appeal.entity;

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
 * 申诉Entity
 * @author sanye
 * @version 2019-06-04
 */
@Table(name="biz_appeal", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="paln_id", attrName="palnId", label="申诉项目"),
		@Column(name="phases_id", attrName="phasesId", label="审核阶段"),
		@Column(name="items", attrName="items", label="具体事项", queryType=QueryType.LIKE),
		@Column(name="reason", attrName="reason", label="申诉理由", isQuery=false),
		@Column(name="receiver_by", attrName="receiverBy", label="受理人", isQuery=false),
		@Column(name="receive_date", attrName="receiveDate", label="受理时间", isQuery=false),
		@Column(name="opinion", attrName="opinion", label="受理意见", isQuery=false),
		@Column(name="process", attrName="process", label="处理过程及意见", isQuery=false),
		@Column(name="result", attrName="result", label="裁决结果", isQuery=false),
		@Column(name="score", attrName="score", label="分数调整结果"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="audit_by", attrName="auditBy", label="审批者", isInsert=false, isQuery=false),
		@Column(name="audit_date", attrName="auditDate", label="审批时间", isInsert=false, isQuery=false),
		@Column(name="logs", attrName="logs", label="操作日志", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class Appeal extends DataEntity<Appeal> {
	
	private static final long serialVersionUID = 1L;
	private String palnId;		// 申诉项目
	private String phasesId;		// 审核阶段
	private String items;		// 具体事项
	private String reason;		// 申诉理由
	private String receiverBy;		// 受理人
	private Date receiveDate;		// 受理时间
	private String opinion;		// 受理意见
	private String process;		// 处理过程及意见
	private String result;		// 裁决结果
	private Double score;		// 分数调整结果
	private String auditBy;		// 审批者
	private Date auditDate;		// 审批时间
	private String logs;		// 操作日志
	
	public Appeal() {
		this(null);
	}

	public Appeal(String id){
		super(id);
	}
	

	@Length(min=0, max=64, message="申诉项目长度不能超过 64 个字符")
	public String getPalnId() {
		return palnId;
	}

	public void setPalnId(String palnId) {
		this.palnId = palnId;
	}
	

	@Length(min=0, max=64, message="审核阶段长度不能超过 64 个字符")
	public String getPhasesId() {
		return phasesId;
	}

	public void setPhasesId(String phasesId) {
		this.phasesId = phasesId;
	}


	@Length(min=0, max=2000, message="具体事项长度不能超过 2000 个字符")
	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	@Length(min=0, max=2000, message="申诉理由长度不能超过 2000 个字符")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=64, message="受理人长度不能超过 64 个字符")
	public String getReceiverBy() {
		return receiverBy;
	}

	public void setReceiverBy(String receiverBy) {
		this.receiverBy = receiverBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	@Length(min=0, max=2000, message="受理意见长度不能超过 2000 个字符")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	@Length(min=0, max=2000, message="处理过程及意见长度不能超过 2000 个字符")
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	@Length(min=0, max=2000, message="裁决结果长度不能超过 2000 个字符")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
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
	
	@Length(min=0, max=2000, message="操作日志长度不能超过 2000 个字符")
	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
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