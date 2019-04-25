/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesschecktemplateinfo.entity;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.modules.businesschecktemplat.entity.BusinessCheckTemplate;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 考核指标Entity
 * @author BusinessCheckTemplateInfo
 * @version 2019-04-25
 */
@Table(name="business_check_template_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="businessTarget.id", label="目标"),
		@Column(name="temp_id", attrName="businessCheckTemplate.id", label="模板"),
		@Column(name="description", attrName="description", label="描述信息"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	},joinTable = {
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= BusinessTarget.class, alias="businessTarget",
				on="businessTarget.id = a.target_id", attrName = "businessTarget",
				columns={@Column(includeEntity=BusinessTarget.class)}),
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= BusinessCheckTemplate.class, alias="businessCheckTemplate",
				on="businessCheckTemplate.id = a.temp_id", attrName = "businessCheckTemplate",
				columns={@Column(includeEntity=BusinessCheckTemplate.class)}),
},
		orderBy="a.update_date DESC"
)
public class BusinessCheckTemplateInfo extends DataEntity<BusinessCheckTemplateInfo> {
	
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private BusinessTarget businessTarget;		// 目标
	@Getter
	@Setter
	private BusinessCheckTemplate businessCheckTemplate;		// 模板
	private String description;		// 描述信息
	@Getter
	@Setter
	private String businessTargetId;		// 描述信息


	public BusinessCheckTemplateInfo() {
		this(null);
	}

	public BusinessCheckTemplateInfo(String id){
		super(id);
	}

	
	@Length(min=0, max=255, message="描述信息长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}