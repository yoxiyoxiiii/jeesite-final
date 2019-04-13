/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 指标分类Entity
 * @author 指标分类
 * @version 2019-04-10
 */
@Table(name="target_type", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="level", attrName="level", label="一类、二类、三类", comment="一类、二类、三类（综合得分）"),
		@Column(name="police_type", attrName="policeType", label="警种"),
		@Column(name="frequency", attrName="frequency", label="频次"),
		@Column(name="prize", attrName="prize", label="奖扣 ", comment="奖扣 (扣分项、单项否决、加分项）"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class TargetType extends DataEntity<TargetType> {
	
	private static final long serialVersionUID = 1L;
	private Integer level;		// 一类、二类、三类（综合得分）
	private String policeType;		// 警种
	private String frequency;		// 频次
	private String prize;		// 奖扣 (扣分项、单项否决、加分项）
	
	public TargetType() {
		this(null);
	}

	public TargetType(String id){
		super(id);
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Length(min=0, max=255, message="警种长度不能超过 255 个字符")
	public String getPoliceType() {
		return policeType;
	}

	public void setPoliceType(String policeType) {
		this.policeType = policeType;
	}
	
	@Length(min=0, max=255, message="频次长度不能超过 255 个字符")
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	@Length(min=0, max=255, message="奖扣 长度不能超过 255 个字符")
	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}
	
}