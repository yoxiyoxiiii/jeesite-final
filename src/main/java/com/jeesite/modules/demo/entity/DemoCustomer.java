/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * demo_customerEntity
 * @author sanye
 * @version 2019-04-23
 */
@Table(name="demo_customer", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="score", attrName="score", label="上报分数"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class DemoCustomer extends DataEntity<DemoCustomer> {
	
	private static final long serialVersionUID = 1L;
	private String score;		// 上报分数
	
	public DemoCustomer() {
		this(null);
	}

	public DemoCustomer(String id){
		super(id);
	}
	
	@NotBlank(message="上报分数不能为空")
	@Length(min=0, max=128, message="上报分数长度不能超过 128 个字符")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
}