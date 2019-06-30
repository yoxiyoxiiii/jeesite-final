/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 目标生成的任务Entity
 * @author yj
 * @version 2019-05-02
 */
@Table(name="business_plan_user_task", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_id", attrName="user.userCode", label="任务所属的user"),
		@Column(name="monitor_id", attrName="monitorId", label="监控ID"),
		@Column(name="business_check_plan_id", attrName="businessCheckPlanId", label="任务所属的user"),
		@Column(name="department_id", attrName="office.officeCode", label="任务所属的部门"),
		@Column(name="target_id", attrName="businessTarget.id", label="任务关联的目标"),
		@Column(name="stage_id", attrName="businessStageTarget2.id", label="任务关联的目标"),
		@Column(name="target_data_item_id", attrName="businessTargetDataItem.id", label="任务关联的数据项"),
		@Column(name="task_status", attrName="taskStatus", label="任务状态"),
		@Column(name="task_description", attrName="taskDescription", label="描述信息"),
		@Column(name="task_start_time", attrName="taskStartTime", label="任务开始时间"),
		@Column(name="task_end_time", attrName="taskEndTime", label="任务结束时间"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	},
		joinTable = {
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "user",
						on = "user.user_code = a.user_id", attrName = "user",
						columns = {@Column(includeEntity = User.class)}),

				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTarget2.class, alias = "businessTarget",
						on = "businessTarget.id = a.target_id", attrName = "businessTarget",
						columns = {@Column(includeEntity = BusinessTarget2.class)}),

				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTargetDataItem.class, alias = "businessTargetDataItem",
						on = "businessTargetDataItem.id = a.target_data_item_id", attrName = "businessTargetDataItem",
						columns = {@Column(includeEntity = BusinessTargetDataItem.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessStageTarget2.class, alias = "businessStageTarget2",
						on = "businessStageTarget2.id = a.stage_id", attrName = "businessStageTarget2",
						columns = {@Column(includeEntity = BusinessStageTarget2.class)}),

		},
		orderBy="a.update_date DESC"
)
public class BusinessPlanUserTask extends DataEntity<BusinessPlanUserTask> {
	
	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private User user;		// 任务所属的user
	@Setter
	@Getter
	private BusinessTarget2 businessTarget;		// 任务关联的目标
	@Setter
	@Getter
	private BusinessStageTarget2 businessStageTarget2;		// 期数
	@Setter
	@Getter
	private BusinessTargetDataItem businessTargetDataItem;		// 任务关联的数据项
	private Integer taskStatus;		// 任务状态
	private String taskDescription;		// 描述信息
	private Date taskStartTime;		// 任务开始时间
	private Date taskEndTime;		// 任务结束时间

	@Getter
	@Setter
	private String monitorId;//监控ID

	@Getter
	@Setter
	private Office office;

	/**
	 * 关联考核计划
	 */
	@Getter
	@Setter
	private String businessCheckPlanId;
	
	public BusinessPlanUserTask() {
		this(null);
	}

	public BusinessPlanUserTask(String id){
		super(id);
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	@Length(min=0, max=255, message="描述信息长度不能超过 255 个字符")
	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}
	
}