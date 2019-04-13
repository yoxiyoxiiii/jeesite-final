/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.check.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 考核评分名单Entity
 * @author 考核评分名单
 * @version 2019-04-10
 */
@Table(name="check_score", alias="a", columns={
		@Column(name="id", attrName="id", label="评分人", isPK=true),
		@Column(name="plan_id", attrName="planId", label="plan_id"),
		@Column(name="user_id", attrName="userId", label="评价人"),
		@Column(name="score", attrName="score", label="评价得分"),
		@Column(name="description", attrName="description", label="文字评价"),
		@Column(name="msg", attrName="msg", label="其他说明"),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="修改时间", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class CheckScore extends DataEntity<CheckScore> {
	
	private static final long serialVersionUID = 1L;
	private String planId;		// plan_id
	private String userId;		// 评价人
	private String score;		// 评价得分
	private String description;		// 文字评价
	private String msg;		// 其他说明
	
	public CheckScore() {
		this(null);
	}

	public CheckScore(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="plan_id长度不能超过 50 个字符")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	@Length(min=0, max=50, message="评价人长度不能超过 50 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=255, message="评价得分长度不能超过 255 个字符")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=255, message="文字评价长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="其他说明长度不能超过 255 个字符")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}