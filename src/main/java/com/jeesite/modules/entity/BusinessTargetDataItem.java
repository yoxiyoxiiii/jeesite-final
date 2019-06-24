/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import com.jeesite.common.mybatis.annotation.JoinTable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 数据采集项Entity
 * @author BusinessTargetDataItem
 * @version 2019-04-22
 */
@Table(name="business_target_data_item", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="businessTargets.id", label="目标"),
		@Column(name="stage_target_id", attrName="stageTargets.id", label="阶段目标"),
		@Column(name="item_name", attrName="itemName", label="采集数据项", queryType=QueryType.LIKE),
		@Column(name="item_weight", attrName="itemWeight", label="数据项权重"),
		@Column(name="item_score", attrName="itemScore", label="数据项分数"),
		@Column(name="item_final_score", attrName="itemFinalScore", label="数据项得分"),
		@Column(name="item_status", attrName="itemStatus", label="数据项状态"),
		@Column(name="update_by", attrName="updateBy", label="审核者"),
		@Column(name="item_description", attrName="itemDescription", label="说明"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	}, joinTable = {
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTarget2.class, alias = "businessTargets",
				on = "businessTargets.id = a.target_id", attrName = "businessTargets",
				columns = {@Column(includeEntity = BusinessTarget2.class)}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = StageTarget.class, alias = "stageTargets",
				on = "stageTargets.id = a.stage_target_id", attrName = "stageTargets",
				columns = {@Column(includeEntity = StageTarget.class)}),
},
		orderBy="a.update_date DESC"
)
public class BusinessTargetDataItem extends DataEntity<BusinessTargetDataItem> {
	
	private static final long serialVersionUID = 1L;
	private BusinessTarget2 businessTargets;		// 上级目标
	private StageTarget stageTargets;		// 阶段目标
	private String itemName;		// 采集数据项
	private Integer itemWeight;		// 数据项权重
	private String itemScore;		// 数据项分数
	@Getter
	@Setter
	private String itemFinalScore;		// 数据项得分
	@Getter
	@Setter
	private String itemStatus;		// 数据项状态
	@Getter
	@Setter
	private String updateBy;		// 审核人
	private String itemDescription;		// 说明
	@Getter
	@Setter
	private String stagetargetId;
	
	public BusinessTargetDataItem() {
		this(null);
	}

	public BusinessTargetDataItem(String id){
		super(id);
	}
	
	public BusinessTarget2 getBusinessTargets() {
		return businessTargets;
	}

	public void setTargetId(BusinessTarget2 businessTargets) {
		this.businessTargets = businessTargets;
	}
	
	public StageTarget getStageTargets() {
		return stageTargets;
	}

	public void setStageTargets(StageTarget stageTarget) {
		this.stageTargets = stageTarget;
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