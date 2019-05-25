/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettypetree.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 目标分类Entity
 * @author BusinessTargetTypeTree
 * @version 2019-05-05
 */
@Table(name="business_target_type_tree", alias="a", columns={
		@Column(name="target_type_code", attrName="targetTypeCode", label="节点编码", isPK=true),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="target_type_name", attrName="targetTypeName", label="节点名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(includeEntity=DataEntity.class),
		@Column(name = "target_type_score", attrName = "targetTypeScore", label = "分值")
	}, orderBy="a.tree_sorts, a.target_type_code"
)
public class BusinessTargetTypeTree extends TreeEntity<BusinessTargetTypeTree> {
	
	private static final long serialVersionUID = 1L;
	private String targetTypeCode;		// 节点编码
	private String targetTypeName;		// 节点名称
	@Setter
	@Getter
	private Integer targetTypeScore;    //分值
	
	public BusinessTargetTypeTree() {
		this(null);
	}

	public BusinessTargetTypeTree(String id){
		super(id);
	}
	
	@Override
	public BusinessTargetTypeTree getParent() {
		return parent;
	}

	@Override
	public void setParent(BusinessTargetTypeTree parent) {
		this.parent = parent;
	}
	
	public String getTargetTypeCode() {
		return targetTypeCode;
	}

	public void setTargetTypeCode(String targetTypeCode) {
		this.targetTypeCode = targetTypeCode;
	}
	
	@NotBlank(message="节点名称不能为空")
	@Length(min=0, max=200, message="节点名称长度不能超过 200 个字符")
	public String getTargetTypeName() {
		return targetTypeName;
	}

	public void setTargetTypeName(String targetTypeName) {
		this.targetTypeName = targetTypeName;
	}
	
}