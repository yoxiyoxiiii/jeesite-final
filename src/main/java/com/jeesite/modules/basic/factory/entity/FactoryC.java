/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.factory.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 厂家管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@Table(name="factory_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="full_name", attrName="fullName", label="全称", queryType=QueryType.LIKE),
		@Column(name="name", attrName="name", label="简称", queryType=QueryType.LIKE),
		@Column(name="contractor", attrName="contractor", label="联系人"),
		@Column(name="phone", attrName="phone", label="联系电话", queryType=QueryType.LIKE),
		@Column(name="fax", attrName="fax", label="传真"),
		@Column(name="district", attrName="district", label="地区"),
		@Column(name="address", attrName="address", label="详细地址", queryType=QueryType.LIKE),
		@Column(name="credit", attrName="credit", label="信用评分"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class FactoryC extends DataEntity<FactoryC> {
	
	private static final long serialVersionUID = 1L;
	private String fullName;		// 全称
	private String name;		// 简称
	private String contractor;		// 联系人
	private String phone;		// 联系电话
	private String fax;		// 传真
	private String district;		// 地区
	private String address;		// 详细地址
	private String credit;		// 信用评分
	
	public FactoryC() {
		this(null);
	}

	public FactoryC(String id){
		super(id);
	}
	
	@NotBlank(message="全称不能为空")
	@Length(min=0, max=100, message="全称长度不能超过 100 个字符")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Length(min=0, max=64, message="简称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="联系人不能为空")
	@Length(min=0, max=64, message="联系人长度不能超过 64 个字符")
	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
	
	@NotBlank(message="联系电话不能为空")
	@Length(min=0, max=20, message="联系电话长度不能超过 20 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=20, message="传真长度不能超过 20 个字符")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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
	
	@Length(min=0, max=1, message="信用评分长度不能超过 1 个字符")
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
}