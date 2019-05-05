/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.customers.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 客户管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@Table(name="customers_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="客户姓名", queryType=QueryType.LIKE),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(name="phone", attrName="phone", label="联系电话", queryType=QueryType.LIKE),
		@Column(name="email", attrName="email", label="电子邮箱", isQuery=false),
		@Column(name="company", attrName="company", label="所属公司", queryType=QueryType.LIKE),
		@Column(name="district", attrName="district", label="地区"),
		@Column(name="address", attrName="address", label="详细地址", queryType=QueryType.LIKE),
		@Column(name="total_buy_amount", attrName="totalBuyAmount", label="消费总额", queryType=QueryType.GTE),
		@Column(name="credit", attrName="credit", label="信用评分"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class CustomersC extends DataEntity<CustomersC> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 客户姓名
	private String sex;		// 性别
	private String phone;		// 联系电话
	private String email;		// 电子邮箱
	private String company;		// 所属公司
	private String district;		// 地区
	private String address;		// 详细地址
	private Double totalBuyAmount;		// 消费总额
	private String credit;		// 信用评分
	
	public CustomersC() {
		this(null);
	}

	public CustomersC(String id){
		super(id);
	}
	
	@NotBlank(message="客户姓名不能为空")
	@Length(min=0, max=100, message="客户姓名长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="性别不能为空")
	@Length(min=0, max=1, message="性别长度不能超过 1 个字符")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@NotBlank(message="联系电话不能为空")
	@Length(min=0, max=20, message="联系电话长度不能超过 20 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=64, message="电子邮箱长度不能超过 64 个字符")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=100, message="所属公司长度不能超过 100 个字符")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Length(min=0, max=64, message="地区长度不能超过 64 个字符")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	@Length(min=0, max=200, message="详细地址长度不能超过 200 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getTotalBuyAmount() {
		return totalBuyAmount;
	}

	public void setTotalBuyAmount(Double totalBuyAmount) {
		this.totalBuyAmount = totalBuyAmount;
	}
	
	@Length(min=0, max=1, message="信用评分长度不能超过 1 个字符")
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
}