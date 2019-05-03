/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test1.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * test1Entity
 * @author Test1
 * @version 2019-05-03
 */
@Table(name="test3", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="test1_id", attrName="test1Id.id", label="test1_id"),
		@Column(name="test3_name", attrName="test3Name", label="test3_name", queryType=QueryType.LIKE),
	}, orderBy="a.id ASC"
)
public class Test3 extends DataEntity<Test3> {
	
	private static final long serialVersionUID = 1L;
	private Test1 test1Id;		// test1_id 父类
	private String test3Name;		// test3_name
	
	public Test3() {
		this(null);
	}


	public Test3(Test1 test1Id){
		this.test1Id = test1Id;
	}
	
	@Length(min=0, max=11, message="test1_id长度不能超过 11 个字符")
	public Test1 getTest1Id() {
		return test1Id;
	}

	public void setTest1Id(Test1 test1Id) {
		this.test1Id = test1Id;
	}
	
	@Length(min=0, max=255, message="test3_name长度不能超过 255 个字符")
	public String getTest3Name() {
		return test3Name;
	}

	public void setTest3Name(String test3Name) {
		this.test3Name = test3Name;
	}
	
}