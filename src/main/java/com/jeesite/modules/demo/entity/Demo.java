/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 样例数据Entity
 * @author sanye
 * @version 2019-04-30
 */
@Table(name="demo_customer", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="score", attrName="score", label="上报分数"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Demo extends DataEntity<Demo> {
	
	private static final long serialVersionUID = 1L;
	private String score;		// 上报分数
	
	public Demo() {
		this(null);
	}

	public Demo(String id){
		super(id);
	}
	
	@Length(min=0, max=200, message="上报分数长度不能超过 200 个字符")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
}