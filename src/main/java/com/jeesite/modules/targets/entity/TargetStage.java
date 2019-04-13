/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 阶段目标Entity
 * @author 阶段目标
 * @version 2019-04-10
 */
@Table(name="target_stage", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="targetId", label="关联目标对象"),
		@Column(name="cycle", attrName="cycle", label="分解周期", comment="分解周期（月/季度）"),
		@Column(name="target_value", attrName="targetValue", label="目标值"),
		@Column(name="weight", attrName="weight", label="权重 存储 整型，计算换算"),
		@Column(name="effort_value", attrName="effortValue", label="争创值"),
		@Column(name="create_by", attrName="createBy", label="创建者", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="修改时间", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class TargetStage extends DataEntity<TargetStage> {
	
	private static final long serialVersionUID = 1L;
	private String targetId;		// 关联目标对象
	private String cycle;		// 分解周期（月/季度）
	private String targetValue;		// 目标值
	private Long weight;		// 权重 存储 整型，计算换算
	private String effortValue;		// 争创值
	
	public TargetStage() {
		this(null);
	}

	public TargetStage(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="关联目标对象长度不能超过 255 个字符")
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	@Length(min=0, max=255, message="分解周期长度不能超过 255 个字符")
	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	
	@Length(min=0, max=255, message="目标值长度不能超过 255 个字符")
	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	
	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=255, message="争创值长度不能超过 255 个字符")
	public String getEffortValue() {
		return effortValue;
	}

	public void setEffortValue(String effortValue) {
		this.effortValue = effortValue;
	}
	
}