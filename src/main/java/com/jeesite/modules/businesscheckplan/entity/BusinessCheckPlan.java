/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;
import com.jeesite.modules.sys.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 考核计划Entity
 * @author BusinessCheckPlan
 * @version 2019-04-25
 */
@Table(name="business_check_plan", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="temp_id", attrName="businessTargetType.id", label="模板"),
		@Column(name="plan_major_type", attrName="planMajorType", label="专业类型"),
		@Column(name="plan_name", attrName="planName", label="计划名称", queryType=QueryType.LIKE),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="plan_start_time", attrName="planStartTime", label="开始时间"),
		@Column(name="plan_end_time", attrName="planEndTime", label="结束时间"),
		@Column(name="plan_scoring_start_time", attrName="planScoringStartTime", label="评分开始时间"),
		@Column(name="plan_scoring_end_time", attrName="planScoringEndTime", label="评分结束时间"),
		@Column(name="plan_check_user_id", attrName="planCheckUser.userCode", label="负责人"),
		@Column(name="plan_duty_user_id", attrName="planDutyUser.userCode", label="责任人"),
		@Column(name="plan_work_results", attrName="planWorkResults", label="工作成果"),
		@Column(name="plan_key", attrName="planKey", label="关键措施分析"),
		@Column(name="plan_status", attrName="planStatus", label="状态"),
		@Column(name="plan_weight", attrName="planWeight", label="权重%"),
		@Column(name="is_update", attrName="isUpdate", label="isUpdate"),
	},
		joinTable = {
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTargetType.class, alias = "businessTargetType",
						on = "businessTargetType.target_type_code = a.temp_id", attrName = "businessTargetType",
						columns = {@Column(includeEntity = BusinessTargetType.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "planCheckUser",
						on = "planCheckUser.user_code = a.plan_check_user_id", attrName = "planCheckUser",
						columns = {@Column(includeEntity = User.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "planDutyUser",
						on = "planDutyUser.user_code = a.plan_duty_user_id", attrName = "planDutyUser",
						columns = {@Column(includeEntity = User.class)}),
		},
		orderBy="a.update_date DESC"
)
public class BusinessCheckPlan extends DataEntity<BusinessCheckPlan> {
	
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@NotNull(message = "专业类型必填")
	private BusinessTargetType businessTargetType;		// 模板

	private Integer planMajorType;		// 专业类型
	@NotNull(message = "计划名称必填")
	private String planName;		// 计划名称
	@NotNull(message = "开始时间必填")
	private Date planStartTime;		// 开始时间
	@NotNull(message = "结束时间必填")
	private Date planEndTime;		// 结束时间
	@NotNull(message = "评分开始时间非空")
	private Date planScoringStartTime;		// 评分开始时间
	@NotNull(message = "评分结束时间非空")
	private Date planScoringEndTime;		// 评分结束时间
	private User planCheckUser;		// 负责人
	private User planDutyUser;		// 责任人
	@Getter
	@Setter
	private String planDutyUserCode;		// 责任人Code
	private String planWorkResults;		// 工作成果
	private String planKey;		// 关键措施分析
	private Integer planStatus;		// 状态
	private Integer planWeight;		// 权重%
	@Getter
	@Setter
	private Integer isUpdate;		// isUpdate

	public BusinessCheckPlan() {
		this(null);
	}

	public BusinessCheckPlan(String id){
		super(id);
	}
	

	
	public Integer getPlanMajorType() {
		return planMajorType;
	}

	public void setPlanMajorType(Integer planMajorType) {
		this.planMajorType = planMajorType;
	}
	
	@Length(min=0, max=255, message="计划名称长度不能超过 255 个字符")
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanScoringStartTime() {
		return planScoringStartTime;
	}

	public void setPlanScoringStartTime(Date planScoringStartTime) {
		this.planScoringStartTime = planScoringStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanScoringEndTime() {
		return planScoringEndTime;
	}

	public void setPlanScoringEndTime(Date planScoringEndTime) {
		this.planScoringEndTime = planScoringEndTime;
	}
	
	public User getPlanCheckUser() {
		return planCheckUser;
	}

	public void setPlanCheckUser(User planCheckUser) {
		this.planCheckUser = planCheckUser;
	}
	
	public User getPlanDutyUser() {
		return planDutyUser;
	}

	public void setPlanDutyUser(User planDutyUser) {
		this.planDutyUser = planDutyUser;
	}
	
	@Length(min=0, max=255, message="工作成果长度不能超过 255 个字符")
	public String getPlanWorkResults() {
		return planWorkResults;
	}

	public void setPlanWorkResults(String planWorkResults) {
		this.planWorkResults = planWorkResults;
	}
	
	@Length(min=0, max=255, message="关键措施分析长度不能超过 255 个字符")
	public String getPlanKey() {
		return planKey;
	}

	public void setPlanKey(String planKey) {
		this.planKey = planKey;
	}
	
	public Integer getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}
	
	public Integer getPlanWeight() {
		return planWeight;
	}

	public void setPlanWeight(Integer planWeight) {
		this.planWeight = planWeight;
	}
	
}