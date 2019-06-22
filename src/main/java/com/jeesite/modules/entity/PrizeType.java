/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import javax.validation.constraints.NotBlank;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 奖扣类型Entity
 * @author sanye
 * @version 2019-05-02
 */
@Table(name="biz_prize_type", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="name", attrName="name", label="名称", queryType=QueryType.LIKE),
		@Column(name="is_add", attrName="isAdd", label="类型"),
		@Column(name="limit", attrName="limit", label="加分控制"),
		@Column(name="rule_remark", attrName="ruleRemark", label="奖扣标准说明", queryType=QueryType.LIKE),
		@Column(name="led", attrName="ledOffice.officeCode", label="牵头部门"),
		@Column(includeEntity=DataEntity.class),
	},joinTable={
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= Office.class, attrName="ledOffice", alias="u11",
				on="u11.office_code = a.led", columns={
				@Column(name="office_code", label="机构编码", isPK=true),
				@Column(name="office_name", label="机构名称", isQuery=false),
		})
    },
		orderBy="a.update_date DESC"
)
public class PrizeType extends DataEntity<PrizeType> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String isAdd;		// 类型
	private Double limit;		// 加分控制
	private String ruleRemark;		// 奖扣标准说明
	private Office ledOffice;		// 牵头部门
	
	public PrizeType() {
		this(null);
	}

	public PrizeType(String id){
		super(id);
	}
	
	@NotBlank(message="名称不能为空")
	@Length(min=0, max=200, message="名称长度不能超过 200 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="类型不能为空")
	@Length(min=0, max=3, message="类型长度不能超过 3 个字符")
	public String getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}
	
	@NotNull(message="加分控制不能为空")
	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}
	
	@Length(min=0, max=500, message="奖扣标准说明长度不能超过 500 个字符")
	public String getRuleRemark() {
		return ruleRemark;
	}

	public void setRuleRemark(String ruleRemark) {
		this.ruleRemark = ruleRemark;
	}

	public Office getLedOffice() {
		return ledOffice;
	}

	public void setLedOffice(Office ledOffice) {
		this.ledOffice = ledOffice;
	}
	
}