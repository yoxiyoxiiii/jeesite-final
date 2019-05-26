/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessexpression.entity;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.businesstarget2.entity.BusinessTarget2;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * business_ expressionEntity
 * @author BusinessExpression
 * @version 2019-05-25
 */
@Table(name="business_expression", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="expression", attrName="expression", label="计算公式"),
		@Column(name="target_id", attrName="businessTarget2.id", label="关联考核细则"),
		@Column(name="status", attrName="status", label="status", isUpdate=false),
		@Column(name="expression_type", attrName="expressionType", label="if / else"),
	},
		joinTable = {
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTarget2.class, alias = "businessTarget2",
						on = "businessTarget2.id = a.target_id", attrName = "businessTarget2",
						columns = {@Column(includeEntity = BusinessTarget2.class)})
		},

		orderBy="a.id DESC"
)
public class BusinessExpression extends DataEntity<BusinessExpression> {
	
	private static final long serialVersionUID = 1L;
	private String expression;		// 计算公式
	@Getter
	@Setter
	private BusinessTarget2 businessTarget2;		// 关联考核细则
	private String expressionType;		// if / else
	private List<BusinessExpressionParam> businessExpressionParamList = ListUtils.newArrayList();		// 子表列表
	
	public BusinessExpression() {
		this(null);
	}

	public BusinessExpression(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="计算公式长度不能超过 255 个字符")
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	
	@Length(min=0, max=255, message="if / else长度不能超过 255 个字符")
	public String getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(String expressionType) {
		this.expressionType = expressionType;
	}
	
	public List<BusinessExpressionParam> getBusinessExpressionParamList() {
		return businessExpressionParamList;
	}

	public void setBusinessExpressionParamList(List<BusinessExpressionParam> businessExpressionParamList) {
		this.businessExpressionParamList = businessExpressionParamList;
	}
	
}