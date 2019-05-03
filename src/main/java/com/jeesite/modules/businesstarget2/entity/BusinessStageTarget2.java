/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstarget2.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 指标Entity
 * @author BusinessTarget2
 * @version 2019-05-03
 */
@Table(name="business_stage_target", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="targetId.id", label="target_id"),
		@Column(name="stage_number", attrName="stageNumber", label="期数 默认为一期", comment="期数 默认为一期："),
		@Column(name="stage_target_value", attrName="stageTargetValue", label="目标值"),
		@Column(name="stage_weight", attrName="stageWeight", label="权重%"),
		@Column(name="stage_try_hard_value", attrName="stageTryHardValue", label="争创值"),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="stage_name", attrName="stageName", label="阶段目标名称", queryType=QueryType.LIKE),
	}, orderBy="a.create_date ASC"
)
public class BusinessStageTarget2 extends DataEntity<BusinessStageTarget2> {
	
	private static final long serialVersionUID = 1L;
	private BusinessTarget2 targetId;		// target_id 父类
	private Integer stageNumber;		// 期数 默认为一期：
	private String stageTargetValue;		// 目标值
	private Integer stageWeight;		// 权重%
	private String stageTryHardValue;		// 争创值
	private String stageName;		// 阶段目标名称
	
	public BusinessStageTarget2() {
		this(null);
	}


	public BusinessStageTarget2(BusinessTarget2 targetId){
		this.targetId = targetId;
	}
	
	@Length(min=0, max=50, message="target_id长度不能超过 50 个字符")
	public BusinessTarget2 getTargetId() {
		return targetId;
	}

	public void setTargetId(BusinessTarget2 targetId) {
		this.targetId = targetId;
	}
	
	public Integer getStageNumber() {
		return stageNumber;
	}

	public void setStageNumber(Integer stageNumber) {
		this.stageNumber = stageNumber;
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
	
	@Length(min=0, max=255, message="阶段目标名称长度不能超过 255 个字符")
	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	
}