/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettype.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 指标分类Entity
 * @author 指标分类
 * @version 2019-04-21
 */
@Table(name="business_target_type", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="type_name", attrName="typeName", label="分类名称", queryType=QueryType.LIKE),
		@Column(name="type", attrName="type", label="考核分类"),
		@Column(name="police_type", attrName="policeType", label="条线/警种"),
		@Column(name="dept_level", attrName="deptLevel", label="部门层级"),
		@Column(name="create_date", attrName="createDate", label="创新时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class BusinessTargetType extends DataEntity<BusinessTargetType> {
	
	private static final long serialVersionUID = 1L;
	private String typeName;		// 分类名称
	private Integer type;		// 考核分类
	private String policeType;		// 条线/警种
	private Integer deptLevel;		// 部门层级
	
	public BusinessTargetType() {
		this(null);
	}

	public BusinessTargetType(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="分类名称长度不能超过 255 个字符")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="条线/警种长度不能超过 255 个字符")
	public String getPoliceType() {
		return policeType;
	}

	public void setPoliceType(String policeType) {
		this.policeType = policeType;
	}
	
	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}
	
}