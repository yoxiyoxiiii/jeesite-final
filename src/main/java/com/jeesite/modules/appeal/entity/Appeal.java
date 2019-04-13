/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.appeal.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 评分结果申诉Entity
 * @author 评分结果申诉
 * @version 2019-04-10
 */
@Table(name="appeal", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_id", attrName="userId", label="申诉人/部门"),
		@Column(name="reason", attrName="reason", label="申诉理由"),
		@Column(name="file_id", attrName="fileId", label="申诉文件"),
		@Column(name="plan_id", attrName="planId", label="该申诉关联的 考核计划"),
		@Column(name="result", attrName="result", label="申诉结果"),
		@Column(name="msg", attrName="msg", label="申诉结果描述信息"),
		@Column(name="handle_id", attrName="handleId", label="审查处理人"),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class Appeal extends DataEntity<Appeal> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 申诉人/部门
	private String reason;		// 申诉理由
	private String fileId;		// 申诉文件
	private String planId;		// 该申诉关联的 考核计划
	private String result;		// 申诉结果
	private String msg;		// 申诉结果描述信息
	private String handleId;		// 审查处理人
	
	public Appeal() {
		this(null);
	}

	public Appeal(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="申诉人/部门长度不能超过 50 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=255, message="申诉理由长度不能超过 255 个字符")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=50, message="申诉文件长度不能超过 50 个字符")
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	@Length(min=0, max=50, message="该申诉关联的 考核计划长度不能超过 50 个字符")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	@Length(min=0, max=255, message="申诉结果长度不能超过 255 个字符")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=255, message="申诉结果描述信息长度不能超过 255 个字符")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Length(min=0, max=50, message="审查处理人长度不能超过 50 个字符")
	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}
	
}