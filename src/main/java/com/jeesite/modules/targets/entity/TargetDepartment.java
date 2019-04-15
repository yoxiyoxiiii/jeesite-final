/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import org.hibernate.validator.constraints.Length;

/**
 * 单位/部门目标分解Entity
 * @author 单位/部门目标分解
 * @version 2019-04-10
 */
@Table(name="target_department", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="target", label="关联目标id"),
		@Column(name="department_id", attrName="departmentId", label="目标分解到的部门"),
		@Column(name="handle_id", attrName="handleId", label="操作人"),
		@Column(name="target_name", attrName="targetName", label="指标名称", queryType= QueryType.LIKE),
		@Column(name="target_value", attrName="targetValue", label="目标值"),
		@Column(name="department_type", attrName="departmentType", label="部门类型"),
		@Column(name="description", attrName="description", label="描述信息"),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class TargetDepartment extends DataEntity<TargetDepartment> {
	
	private static final long serialVersionUID = 1L;
	private Targets target;		// 关联目标id
	private String departmentId;		// 目标分解到的部门
	private String handleId;		// 操作人
	private String targetName;		// 指标名称
	private String targetValue;		// 目标值
	private Integer departmentType;		// 部门类型
	private String description;		// 描述信息
	
	public TargetDepartment() {
		this(null);
	}

	public TargetDepartment(String id){
		super(id);
	}
	
	public Targets getTarget() {
		return target;
	}

	public void setTarget(Targets target) {
		this.target = target;
	}
	
	@Length(min=0, max=50, message="目标分解到的部门长度不能超过 50 个字符")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Length(min=0, max=50, message="操作人长度不能超过 50 个字符")
	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}
	
	@Length(min=0, max=255, message="指标名称长度不能超过 255 个字符")
	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	@Length(min=0, max=255, message="目标值长度不能超过 255 个字符")
	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	
	public Integer getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(Integer departmentType) {
		this.departmentType = departmentType;
	}
	
	@Length(min=0, max=255, message="描述信息长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}