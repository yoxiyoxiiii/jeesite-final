/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesschecktemplat.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 考核模板Entity
 * @author 考核模板
 * @version 2019-04-25
 */
@Table(name="business_check_template", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="temp_name", attrName="tempName", label="模板名称", queryType=QueryType.LIKE),
		@Column(name="temp_target_type", attrName="tempTargetType", label="考核指标"),
		@Column(name="temp_calculation_type", attrName="tempCalculationType", label="计算类型"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class BusinessCheckTemplate extends DataEntity<BusinessCheckTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String tempName;		// 模板名称
	private Integer tempTargetType;		// 考核指标
	private Integer tempCalculationType;		// 计算类型
	
	public BusinessCheckTemplate() {
		this(null);
	}

	public BusinessCheckTemplate(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="模板名称长度不能超过 255 个字符")
	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	public Integer getTempTargetType() {
		return tempTargetType;
	}

	public void setTempTargetType(Integer tempTargetType) {
		this.tempTargetType = tempTargetType;
	}
	
	public Integer getTempCalculationType() {
		return tempCalculationType;
	}

	public void setTempCalculationType(Integer tempCalculationType) {
		this.tempCalculationType = tempCalculationType;
	}
	
}