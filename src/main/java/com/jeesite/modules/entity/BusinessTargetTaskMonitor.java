/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.sys.entity.Office;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务监控Entity
 * @author 任务监控/任务列表
 * @version 2019-06-09
 */
@Table(name="business_target_task_monitor", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_id", attrName="businessTarget2.id", label="考核细则", isTreeName=true),
		@Column(name="department_id", attrName="office.officeCode", label="考核部门ID"),
		@Column(name="plan_id", attrName="businessCheckPlan.id", label="考核计划"),
		@Column(name="data_item_count", attrName="dataItemCount", label="数据项数量"),
		@Column(name="up_item_count", attrName="upItemCount", label="已上报数据项数量"),
		@Column(name="status", attrName="status", label="数据上报状态 已上报/未上报", isUpdate=false),
		@Column(includeEntity=TreeEntity.class),
	},  joinTable = {
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= Office.class, alias="office",
				on="office.office_code = a.department_id", attrName = "office",
				columns={@Column(includeEntity=Office.class)}),

		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= BusinessCheckPlan.class, alias="businessCheckPlan",
				on="businessCheckPlan.id = a.plan_id", attrName = "businessCheckPlan",
				columns={@Column(includeEntity=BusinessCheckPlan.class)}),

		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity= BusinessTarget2.class, alias="businessTarget2",
				on="businessTarget2.id = a.target_id", attrName = "businessTarget2",
				columns={@Column(includeEntity=BusinessTarget2.class)}),
      },

		orderBy="a.tree_sorts, a.id"
)
public class BusinessTargetTaskMonitor extends TreeEntity<BusinessTargetTaskMonitor> {
	
	private static final long serialVersionUID = 1L;
	private String targetId;		// 考核细则
	private String departmentId;		// 考核部门ID
	private String planId;		// 考核计划

	@Getter
	@Setter
	private Integer dataItemCount;

	/**
	 *
	 * 已上报数量
	 */
	@Getter
	@Setter
	private Integer upItemCount;

	@Getter
	@Setter
	private String targetContent;

	@Getter
	@Setter
	private BusinessTarget2 businessTarget2;

	@Getter
	@Setter
	private Office office;

	@Getter
	@Setter
	private BusinessCheckPlan businessCheckPlan;

	public synchronized void addUpItemCount() {
		this.upItemCount = upItemCount+1;
	}

	public BusinessTargetTaskMonitor() {
		this(null);
	}

	public BusinessTargetTaskMonitor(String id){
		super(id);
	}
	
	@Override
	public BusinessTargetTaskMonitor getParent() {
		return parent;
	}

	@Override
	public void setParent(BusinessTargetTaskMonitor parent) {
		this.parent = parent;
	}
	
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	
}