/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.sys.entity.Office;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 考核名单Entity
 * @author 考核名单
 * @version 2019-04-28
 */
@Table(name="business_check_plan_user", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="plan_user_name", attrName="planUserName", label="考核名单名称"),
		@Column(name="department_id", attrName="office.officeCode", label="被考核的人"),
		@Column(name="department_name", attrName="office.officeName", label="被考核的人"),
		@Column(name="plan_id", attrName="businessCheckPlan.id", label="考核计划"),
		@Column(name="plan_status", attrName="planStatus", label="部门上报数据状态"),
		@Column(name="plan_user_status", attrName="planUserStatus", label="考核名单状态"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	},
		joinTable = {
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessCheckPlan.class, alias = "businessCheckPlan",
				on = "businessCheckPlan.id = a.plan_id", attrName = "businessCheckPlan",
				columns = {@Column(includeEntity = BusinessCheckPlan.class)}),

		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = Office.class, alias = "office",
				on = "office.office_code = a.department_id", attrName = "office",
				columns = {@Column(includeEntity = Office.class)}),
		},
		orderBy="a.update_date DESC"
)

public class BusinessCheckPlanUser extends DataEntity<BusinessCheckPlanUser> {
	
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@NotNull(message = "考核部门必选!")
	private Office office;		// 被考核的部门
	@Getter
	@Setter
	private BusinessCheckPlan businessCheckPlan;		// 考核计划
	@Getter
	@Setter
	@NotBlank(message = "考核名单名称不能为空!")
	private String planUserName; //考核名单名称

	@Getter
	@Setter
	private String departmentId;

	@Getter
	@Setter
	private String planStatus;
	@Getter
	@Setter
	private String planUserStatus;

	/**
	 * 考核细则
	 */
	@Getter
	@Setter
	private BusinessTarget2 businessTarget2;

	public BusinessCheckPlanUser() {
		this(null);
	}

	public BusinessCheckPlanUser(String id){
		super(id);
	}

	
}