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
@Table(name="business_target_data_item", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="targetId.id", label="目标ID"),
		@Column(name="stage_target_id", attrName="stageTargetId", label="阶段目标ID"),
		@Column(name="item_name", attrName="itemName", label="采集数据项", queryType=QueryType.LIKE),
		@Column(name="item_weight", attrName="itemWeight", label="数据项权重"),
		@Column(name="item_score", attrName="itemScore", label="数据项得分"),
		@Column(name="item_description", attrName="itemDescription", label="说明"),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
	}, orderBy="a.create_date ASC"
)
public class BusinessTargetDataItem2 extends DataEntity<BusinessTargetDataItem2> {
	
	private static final long serialVersionUID = 1L;
	private BusinessTarget2 targetId;		// 目标ID 父类
	private String stageTargetId;		// 阶段目标ID
	private String itemName;		// 采集数据项
	private Integer itemWeight;		// 数据项权重
	private String itemScore;		// 数据项得分
	private String itemDescription;		// 说明
	
	public BusinessTargetDataItem2() {
		this(null);
	}


	public BusinessTargetDataItem2(BusinessTarget2 targetId){
		this.targetId = targetId;
	}
	
	@Length(min=0, max=50, message="目标ID长度不能超过 50 个字符")
	public BusinessTarget2 getTargetId() {
		return targetId;
	}

	public void setTargetId(BusinessTarget2 targetId) {
		this.targetId = targetId;
	}
	
	@Length(min=0, max=50, message="阶段目标ID长度不能超过 50 个字符")
	public String getStageTargetId() {
		return stageTargetId;
	}

	public void setStageTargetId(String stageTargetId) {
		this.stageTargetId = stageTargetId;
	}
	
	@Length(min=0, max=255, message="采集数据项长度不能超过 255 个字符")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Integer getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(Integer itemWeight) {
		this.itemWeight = itemWeight;
	}
	
	@Length(min=0, max=255, message="数据项得分长度不能超过 255 个字符")
	public String getItemScore() {
		return itemScore;
	}

	public void setItemScore(String itemScore) {
		this.itemScore = itemScore;
	}
	
	@Length(min=0, max=255, message="说明长度不能超过 255 个字符")
	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
}