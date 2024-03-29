/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import com.jeesite.common.mybatis.annotation.JoinTable;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 阶段目标Entity
 * @author StageTarget
 * @version 2019-04-22
 */
@Table(name="business_stage_target", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="stage_number", attrName="stageNumber", label="期数"),
		@Column(name="stage_name", attrName="stageName", label="阶段目标名称"),
		@Column(name="target_id", attrName="businessTargets.id", label="上级目标"),
		@Column(name="stage_target_value", attrName="stageTargetValue", label="目标值"),
		@Column(name="stage_weight", attrName="stageWeight", label="权重%"),
		@Column(name="stage_try_hard_value", attrName="stageTryHardValue", label="争创值"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	},
		joinTable = {
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTarget2.class, alias = "businessTargets",
						on = "businessTargets.id = a.target_id", attrName = "businessTargets",
						columns = {@Column(includeEntity = BusinessTarget2.class)}),
		},
		orderBy="a.update_date DESC"
)
public class StageTarget extends DataEntity<StageTarget> {
	
	private static final long serialVersionUID = 1L;
	private Integer stageNumber;		// 期数
	private BusinessTarget2 businessTargets;		// 上级目标
	private String stageTargetValue;		// 目标值
	private Integer stageWeight;		// 权重%
	private String stageTryHardValue;		// 争创值
	private String stageName;		// 阶段目标名称

	public String getStageName() {
		return stageName;
	}

	public void  setStageName(String stageName) {
		this.stageName = stageName;
	}
	public StageTarget() {
		this(null);
	}

	public StageTarget(String id){
		super(id);
	}
	
	public Integer getStageNumber() {
		return stageNumber;
	}

	public void setStageNumber(Integer stageNumber) {
		this.stageNumber = stageNumber;
	}
	
	public BusinessTarget2 getBusinessTargets() {
		return businessTargets;
	}

	public void setBusinessTargets(BusinessTarget2 businessTargets) {
		this.businessTargets = businessTargets;
	}
	
	@Length(min=0, max=255, message="目标值长度不能超过 255 个字符")
	public String getStageTargetValue() {
		return stageTargetValue;
	}

	public void setStageTargetValue(String stageTargetValue) {
		this.stageTargetValue = stageTargetValue;
	}
	
	public Integer getStageWeight() {
		return stageWeight;
	}

	public void setStageWeight(Integer stageWeight) {
		this.stageWeight = stageWeight;
	}
	
	@Length(min=0, max=255, message="争创值长度不能超过 255 个字符")
	public String getStageTryHardValue() {
		return stageTryHardValue;
	}

	public void setStageTryHardValue(String stageTryHardValue) {
		this.stageTryHardValue = stageTryHardValue;
	}
	
}