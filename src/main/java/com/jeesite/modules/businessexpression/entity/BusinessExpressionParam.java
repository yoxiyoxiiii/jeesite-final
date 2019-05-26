/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessexpression.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * business_ expressionEntity
 * @author BusinessExpression
 * @version 2019-05-25
 */
@Table(name="business_expression_param", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="param_name", attrName="paramName", label="参数名", queryType=QueryType.LIKE),
		@Column(name="param_value", attrName="paramValue", label="参数值"),
		@Column(name="expression_id", attrName="expressionId.id", label="关联表达式"),
	}, orderBy="a.id ASC"
)
public class BusinessExpressionParam extends DataEntity<BusinessExpressionParam> {
	
	private static final long serialVersionUID = 1L;
	private String paramName;		// 参数名
	private String paramValue;		// 参数值
	private BusinessExpression expressionId;		// 关联表达式 父类
	
	public BusinessExpressionParam() {
		this(null);
	}


	public BusinessExpressionParam(BusinessExpression expressionId){
		this.expressionId = expressionId;
	}
	
	@Length(min=0, max=255, message="参数名长度不能超过 255 个字符")
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	@Length(min=0, max=255, message="参数值长度不能超过 255 个字符")
	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	@Length(min=0, max=60, message="关联表达式长度不能超过 60 个字符")
	public BusinessExpression getExpressionId() {
		return expressionId;
	}

	public void setExpressionId(BusinessExpression expressionId) {
		this.expressionId = expressionId;
	}
	
}