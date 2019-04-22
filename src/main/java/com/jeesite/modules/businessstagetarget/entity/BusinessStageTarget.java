/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessstagetarget.entity;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 阶段目标Entity
 * @author 阶段目标
 * @version 2019-04-22
 */
@Table(name="business_stage_target", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="businessTarget.id", label="上级目标"),
		@Column(name="stage_number", attrName="stageNumber", label="期数"),
		@Column(name="stage_target_value", attrName="stageTargetValue", label="目标值"),
		@Column(name="stage_weight", attrName="stageWeight", label="权重%"),
		@Column(name="stage_try_hard_value", attrName="stageTryHardValue", label="争创值"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="跟新时间", isQuery=false),
	},
		joinTable = {
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTarget.class, alias = "businessTarget",
						on = "businessTarget.id = a.target_id", attrName = "businessTarget",
						columns = {@Column(includeEntity = BusinessTarget.class)}),
		},
		orderBy="a.update_date DESC"
)
public class BusinessStageTarget extends DataEntity<BusinessStageTarget> {
	
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private BusinessTarget businessTarget;		// 上级目标
	private Integer stageNumber;		// 期数
	private String stageTargetValue;		// 目标值
	private Integer stageWeight;		// 权重%
	private String stageTryHardValue;		// 争创值
	
	public BusinessStageTarget() {
		this(null);
	}

	public BusinessStageTarget(String id){
		super(id);
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
	
}