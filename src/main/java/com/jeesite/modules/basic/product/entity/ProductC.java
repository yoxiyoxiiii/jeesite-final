/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.product.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 货物管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@Table(name="product_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="货物名称", queryType=QueryType.LIKE),
		@Column(name="product_code", attrName="productCode", label="货物编码"),
		@Column(name="price", attrName="price", label="单价", queryType=QueryType.GTE),
		@Column(name="number", attrName="number", label="数量", queryType=QueryType.GTE),
		@Column(name="level", attrName="level", label="货物等级"),
		@Column(name="package_unit", attrName="packageUnit", label="包装单位", isQuery=false),
		@Column(name="spec", attrName="spec", label="规格", queryType=QueryType.LIKE),
		@Column(name="weight", attrName="weight", label="单个重量", isQuery=false),
		@Column(name="position", attrName="position", label="存放位置"),
		@Column(name="total_sold_num", attrName="totalSoldNum", label="已销售量", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class ProductC extends DataEntity<ProductC> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 货物名称
	private String productCode;		// 货物编码
	private Double price;		// 单价
	private Long number;		// 数量
	private String level;		// 货物等级
	private String packageUnit;		// 包装单位
	private String spec;		// 规格
	private Double weight;		// 单个重量
	private String position;		// 存放位置
	private Long totalSoldNum;		// 已销售量
	
	public ProductC() {
		this(null);
	}

	public ProductC(String id){
		super(id);
	}
	
	@NotBlank(message="货物名称不能为空")
	@Length(min=0, max=64, message="货物名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="货物编码不能为空")
	@Length(min=0, max=64, message="货物编码长度不能超过 64 个字符")
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@NotNull(message="单价不能为空")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@NotBlank(message="包装单位不能为空")
	@Length(min=0, max=20, message="包装单位长度不能超过 20 个字符")
	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}
	
	@NotBlank(message="规格不能为空")
	@Length(min=0, max=200, message="规格长度不能超过 200 个字符")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@NotNull(message="单个重量不能为空")
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=64, message="存放位置长度不能超过 64 个字符")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public Long getTotalSoldNum() {
		return totalSoldNum;
	}

	public void setTotalSoldNum(Long totalSoldNum) {
		this.totalSoldNum = totalSoldNum;
	}
	
}