/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstarget2.entity;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;
import com.jeesite.modules.sys.entity.Office;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 指标Entity
 * @author BusinessTarget2
 * @version 2019-05-03
 */
@Table(name="business_target", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_name", attrName="targetName", label="指标名称", queryType=QueryType.LIKE),
		@Column(name="target_type_id", attrName="targetTypes.id", label="关联分类ID"),
		@Column(name="target_check_cycle", attrName="targetCheckCycle", label="目标考核周期 周、半月、月、季度、半年、年 ，定时任务关联"),
		@Column(name="target_check_basic", attrName="targetCheckBasic", label="考核依据"),
		@Column(name="target_attribute", attrName="targetAttribute", label="指标属性 定性、定量"),
		@Column(name="target_execute_dep_id", attrName="executeDepartments.officeCode", label="目标执行部门"),
		@Column(name="target_join_dep_id", attrName="jointWorkDepartments.officeCode", label="协同部门"),
		@Column(name="target_result_expression", attrName="targetResultExpression", label="目标结果计算公式"),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="target_is_decompose", attrName="targetIsDecompose", label="是否分解"),
	}, joinTable = {
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= Office.class, alias="executeDepartmentOffice",
				on="executeDepartmentOffice.office_code = a.target_execute_dep_id", attrName = "executeDepartments",
				columns={@Column(includeEntity=Office.class)}),
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity=Office.class, alias="jointWorkDepartmentOffice",
				on="jointWorkDepartmentOffice.office_code = a.target_join_dep_id", attrName = "jointWorkDepartments",
				columns={@Column(includeEntity=Office.class)}),
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= BusinessTargetType.class, alias="businessTargetType",
				on="businessTargetType.id = a.target_type_id", attrName = "targetTypes",
				columns={@Column(includeEntity=BusinessTargetType.class)}),
},
		orderBy="a.update_date DESC"
)
public class BusinessTarget2 extends DataEntity<BusinessTarget2> {
	
	private static final long serialVersionUID = 1L;
	private String targetName;		// 指标名称
	@Getter
	@Setter
	private BusinessTargetType targetTypes;		// 关联分类ID
	private String targetCheckCycle;		// 目标考核周期 周、半月、月、季度、半年、年 ，定时任务关联
	private String targetCheckBasic;		// 考核依据
	private String targetAttribute;		// 指标属性 定性、定量

	@Getter
	@Setter
	private Office executeDepartments;		// 执行部门

	@Getter
	@Setter
	private Office jointWorkDepartments;		// 协同部门
	private String targetResultExpression;		// 目标结果计算公式
	private Integer targetIsDecompose;		// 是否分解
	private List<BusinessTargetDataItem2> businessTargetDataItem2List = ListUtils.newArrayList();		// 子表列表
	private List<BusinessStageTarget2> businessStageTarget2List = ListUtils.newArrayList();		// 子表列表
	
	public BusinessTarget2() {
		this(null);
	}

	public BusinessTarget2(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="指标名称长度不能超过 255 个字符")
	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	

	@Length(min=0, max=255, message="目标考核周期 周、半月、月、季度、半年、年 ，定时任务关联长度不能超过 255 个字符")
	public String getTargetCheckCycle() {
		return targetCheckCycle;
	}

	public void setTargetCheckCycle(String targetCheckCycle) {
		this.targetCheckCycle = targetCheckCycle;
	}
	
	@Length(min=0, max=255, message="考核依据长度不能超过 255 个字符")
	public String getTargetCheckBasic() {
		return targetCheckBasic;
	}

	public void setTargetCheckBasic(String targetCheckBasic) {
		this.targetCheckBasic = targetCheckBasic;
	}
	
	@Length(min=0, max=255, message="指标属性 定性、定量长度不能超过 255 个字符")
	public String getTargetAttribute() {
		return targetAttribute;
	}

	public void setTargetAttribute(String targetAttribute) {
		this.targetAttribute = targetAttribute;
	}

	@Length(min=0, max=255, message="目标结果计算公式长度不能超过 255 个字符")
	public String getTargetResultExpression() {
		return targetResultExpression;
	}

	public void setTargetResultExpression(String targetResultExpression) {
		this.targetResultExpression = targetResultExpression;
	}
	
	public Integer getTargetIsDecompose() {
		return targetIsDecompose;
	}

	public void setTargetIsDecompose(Integer targetIsDecompose) {
		this.targetIsDecompose = targetIsDecompose;
	}
	
	public List<BusinessTargetDataItem2> getBusinessTargetDataItem2List() {
		return businessTargetDataItem2List;
	}

	public void setBusinessTargetDataItem2List(List<BusinessTargetDataItem2> businessTargetDataItem2List) {
		this.businessTargetDataItem2List = businessTargetDataItem2List;
	}
	
	public List<BusinessStageTarget2> getBusinessStageTarget2List() {
		return businessStageTarget2List;
	}

	public void setBusinessStageTarget2List(List<BusinessStageTarget2> businessStageTarget2List) {
		this.businessStageTarget2List = businessStageTarget2List;
	}
	
}