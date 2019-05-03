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
@Table(name="test2", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="test1_id", attrName="test1Id.id", label="test1_id"),
		@Column(name="test1_id", attrName="test1Id.id", label="test1_id"),
		@Column(name="test_name2", attrName="testName2", label="test_name2", queryType=QueryType.LIKE),
		@Column(name="test_name2", attrName="testName2", label="test_name2", queryType=QueryType.LIKE),
	}, orderBy="a.id ASC, a.id ASC"
)
public class Test2 extends DataEntity<Test2> {
	
	private static final long serialVersionUID = 1L;
	private Test1 test1Id;		// test1_id 父类
	private String testName2;		// test_name2

	public Test2() {
		this(null);
	}



	public Test2(Test1 test1Id){
		this.test1Id = test1Id;
	}
	

	
	public Test1 getTest1Id() {
		return test1Id;
	}

	public void setTest1Id(Test1 test1Id) {
		this.test1Id = test1Id;
	}
	


	@Length(min=0, max=255, message="test_name2长度不能超过 255 个字符")
	public String getTestName2() {
		return testName2;
	}

	public void setTestName2(String testName2) {
		this.testName2 = testName2;
	}
	
}