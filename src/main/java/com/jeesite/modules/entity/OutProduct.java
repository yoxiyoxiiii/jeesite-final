package com.jeesite.modules.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 出货表统计Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-10
 */
@Table(columns = {
		@Column(name = "contractCode", attrName = "contract_code"),
		@Column(name = "productName", attrName = "name"),
		@Column(name = "producCode", attrName = "produc_code"),
		@Column(name = "quantity", attrName = "quantity"),
		@Column(name = "packageUnit", attrName = "package_unit"),
		@Column(name = "singlePackageType", attrName = "single_package_type"),
		@Column(name = "datetime", attrName = "datetime"),
		@Column(name = "signTime", attrName = "sign_time"),
		@Column(name = "endAddr", attrName = "end_addr"),
		@Column(name = "custName", attrName = "cust_name")
		})
public class OutProduct extends DataEntity<OutProduct> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String contractCode;
	private String productName;
	private String producCode;
	private Integer quantity;
	private String packageUnit;
	private String singlePackageType;
	private String datetime;
	private String signTime;
	private String endAddr;
	private String custName;
	
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProducCode() {
		return producCode;
	}
	public void setProducCode(String producCode) {
		this.producCode = producCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getPackageUnit() {
		return packageUnit;
	}
	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}
	public String getSinglePackageType() {
		return singlePackageType;
	}
	public void setSinglePackageType(String singlePackageType) {
		this.singlePackageType = singlePackageType;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public String getEndAddr() {
		return endAddr;
	}
	public void setEndAddr(String endAddr) {
		this.endAddr = endAddr;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
}
