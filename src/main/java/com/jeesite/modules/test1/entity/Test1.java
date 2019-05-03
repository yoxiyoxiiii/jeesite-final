/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test1.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * test1Entity
 * @author Test1
 * @version 2019-05-03
 */
@Table(name="test1", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="name", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class Test1 extends DataEntity<Test1> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private List<Test3> test3List = ListUtils.newArrayList();		// 子表列表
	private List<Test2> test2List = ListUtils.newArrayList();		// 子表列表
	
	public Test1() {
		this(null);
	}

	public Test1(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="name长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Test3> getTest3List() {
		return test3List;
	}

	public void setTest3List(List<Test3> test3List) {
		this.test3List = test3List;
	}
	
	public List<Test2> getTest2List() {
		return test2List;
	}

	public void setTest2List(List<Test2> test2List) {
		this.test2List = test2List;
	}
	
}