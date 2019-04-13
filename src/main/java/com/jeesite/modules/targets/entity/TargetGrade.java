/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 目标分档Entity
 * @author 目标分档
 * @version 2019-04-10
 */
@Table(name="target_grade", alias="a", columns={
		@Column(name="id", attrName="id", label="目标分档", isPK=true),
		@Column(name="target_grade", attrName="targetGrade", label="中文描述 ", comment="中文描述 (基本目标、提升目标、、目标类、综合类）"),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="修改时间", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class TargetGrade extends DataEntity<TargetGrade> {
	
	private static final long serialVersionUID = 1L;
	private String targetGrade;		// 中文描述 (基本目标、提升目标、、目标类、综合类）
	
	public TargetGrade() {
		this(null);
	}

	public TargetGrade(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="中文描述 长度不能超过 255 个字符")
	public String getTargetGrade() {
		return targetGrade;
	}

	public void setTargetGrade(String targetGrade) {
		this.targetGrade = targetGrade;
	}
	
}