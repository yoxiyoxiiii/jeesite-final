/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.sys.entity.Company;
import com.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * targetsEntity
 * @author target
 * @version 2019-04-10
 */
@Table(name="target", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="p_id", attrName="pid", label="上级指标"),
		@Column(name="target_name", attrName="targetName", label="目标名称", queryType= QueryType.LIKE),
		@Column(name="target_value", attrName="targetValue", label="目标值"),
		@Column(name="effort_value", attrName="effortValue", label="争创值"),
		@Column(name="target_attribute", attrName="targetAttribute", label="指标属性 ", comment="指标属性 （1 定量 2 定性）"),
		@Column(name="target_type_id", attrName="targetTypeId", label="分类ID"),
		@Column(name="target_order", attrName="targetOrder", label="指标顺序"),
		@Column(name="weigh", attrName="weigh", label="权重", comment="权重（保存整型，计算时换算）"),
		@Column(name="create_year", attrName="createYear", label="年份"),
		@Column(name="create_month", attrName="createMonth", label="创建月份"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="修改时间", isQuery=false),
		@Column(name="check_cycle", attrName="checkCycle", label="考核周期", comment="考核周期(月，季度，半年，年，前N月）"),
		@Column(name="acquisition_cycle", attrName="acquisitionCycle", label="采集周期 关联定时任务 表达式"),
		@Column(name="grade_id", attrName="gradeId", label="选择分档, 外键关联"),
		@Column(name="complete", attrName="complete", label="完成标准/文字描述/公式结果"),
		@Column(name="belong_id", attrName="belongs.officeCode", label="指标归属"),
		@Column(name="check_basis", attrName="checkBasis", label="考核依据"),
		@Column(name="execute_department", attrName="executeDepartment", label="执行部门"),
		@Column(name="joint_work_department", attrName="jointWorkDepartment", label="协同部门"),
		@Column(name="description", attrName="description", label="描述信息"),
		@Column(name="is_analyze", attrName="isAnalyze", label="是否被分解"),
		@Column(name="handle_id", attrName="handleId", label="handle_id"),
		@Column(name="is_approval", attrName="isApproval", label="审批是否通过 1 通过 0 不通过"),
		@Column(name="approval_by", attrName="approvalBy", label="审批人"),
		@Column(name="approval_msg", attrName="approvalMsg", label="审批备注"),
	}, joinTable={
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity=Office.class, alias="belongOffice",
				on="belongOffice.office_code = a.belong_id", attrName = "belongs",
				columns={@Column(includeEntity=Office.class)}),
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity=Office.class, alias="executeDepartmentOffice",
				on="executeDepartmentOffice.office_code = a.execute_department", attrName = "executeDepartments",
				columns={@Column(includeEntity=Office.class)}),
		@JoinTable(type= JoinTable.Type.LEFT_JOIN, entity=Office.class, alias="jointWorkDepartmentOffice",
				on="jointWorkDepartmentOffice.office_code = a.joint_work_department", attrName = "jointWorkDepartments",
				columns={@Column(includeEntity=Office.class)}),
                },
		orderBy="a.update_date DESC"
)
public class Targets extends DataEntity<Targets> {
	
	private static final long serialVersionUID = 1L;
	private String pid;		// 上级指标
	private String targetName;		// 目标名称
	private String targetValue;		// 目标值
	private String effortValue;		// 争创值
	private Integer targetAttribute;		// 指标属性 （1 定量 2 定性）
	private String targetTypeId;		// 分类ID
	private Integer targetOrder;		// 指标顺序
	private Integer weigh;		// 权重（保存整型，计算时换算）
	private String createYear;		// 年份
	private String createMonth;		// 创建月份
	private String checkCycle;		// 考核周期(月，季度，半年，年，前N月）
	private String acquisitionCycle;		// 采集周期 关联定时任务 表达式
	private String gradeId;		// 选择分档
	private String complete;		// 完成标准/文字描述/公式结果
	private Office belongs;		// 指标归属
	private String checkBasis;		// 考核依据
	private Office executeDepartments1;		// 执行部门
	private Office jointWorkDepartments1;		// 协同部门
	private String description;		// 描述信息
	private Integer isAnalyze;		// 是否被分解
	private String handleId;		// handle_id
	private Integer isApproval;		// 审批是否通过 1 通过 0 不通过
	private String approvalBy;		// 审批人
	private String approvalMsg;		// 审批备注

	public Targets() {
		this(null);
	}

	public Targets(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="上级指标长度不能超过 255 个字符")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Length(min=0, max=255, message="目标名称长度不能超过 255 个字符")
	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	@Length(min=0, max=255, message="目标值长度不能超过 255 个字符")
	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	
	@Length(min=0, max=255, message="争创值长度不能超过 255 个字符")
	public String getEffortValue() {
		return effortValue;
	}

	public void setEffortValue(String effortValue) {
		this.effortValue = effortValue;
	}
	
	public Integer getTargetAttribute() {
		return targetAttribute;
	}

	public void setTargetAttribute(Integer targetAttribute) {
		this.targetAttribute = targetAttribute;
	}
	
	@Length(min=0, max=50, message="分类ID长度不能超过 50 个字符")
	public String getTargetTypeId() {
		return targetTypeId;
	}

	public void setTargetTypeId(String targetTypeId) {
		this.targetTypeId = targetTypeId;
	}
	
	public Integer getTargetOrder() {
		return targetOrder;
	}

	public void setTargetOrder(Integer targetOrder) {
		this.targetOrder = targetOrder;
	}
	
	public Integer getWeigh() {
		return weigh;
	}

	public void setWeigh(Integer weigh) {
		this.weigh = weigh;
	}
	
	@Length(min=0, max=255, message="年份长度不能超过 255 个字符")
	public String getCreateYear() {
		return createYear;
	}

	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}
	
	@Length(min=0, max=255, message="创建月份长度不能超过 255 个字符")
	public String getCreateMonth() {
		return createMonth;
	}

	public void setCreateMonth(String createMonth) {
		this.createMonth = createMonth;
	}
	
	@Length(min=0, max=255, message="考核周期长度不能超过 255 个字符")
	public String getCheckCycle() {
		return checkCycle;
	}

	public void setCheckCycle(String checkCycle) {
		this.checkCycle = checkCycle;
	}
	
	@Length(min=0, max=255, message="采集周期 关联定时任务 表达式长度不能超过 255 个字符")
	public String getAcquisitionCycle() {
		return acquisitionCycle;
	}

	public void setAcquisitionCycle(String acquisitionCycle) {
		this.acquisitionCycle = acquisitionCycle;
	}
	
	@Length(min=0, max=255, message="选择分档, 外键关联长度不能超过 255 个字符")
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	@Length(min=0, max=255, message="完成标准/文字描述/公式结果长度不能超过 255 个字符")
	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}
	
//	@NotNull
	public Office getBelongs() {
		if (belongs == null) {
			belongs = new Office();
		}
		return belongs;
	}

	public void setBelongs(Office belongs) {
		this.belongs = belongs;
	}
	
	@Length(min=0, max=255, message="考核依据长度不能超过 255 个字符")
	public String getCheckBasis() {
		return checkBasis;
	}

	public void setCheckBasis(String checkBasis) {
		this.checkBasis = checkBasis;
	}
	
//	@Length(min=0, max=255, message="执行部门长度不能超过 255 个字符")
	public Office getExecuteDepartments() {
		return executeDepartments1;
	}

	public void setExecuteDepartments(Office executeDepartment) {
		this.executeDepartments1 = executeDepartment;
	}
	
//	@Length(min=0, max=255, message="协同部门长度不能超过 255 个字符")
	public Office getJointWorkDepartments() {
		return jointWorkDepartments1;
	}

	public void setJointWorkDepartments(Office jointWorkDepartment) {
		this.jointWorkDepartments1 = jointWorkDepartment;
	}
	
	@Length(min=0, max=255, message="描述信息长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getIsAnalyze() {
		return isAnalyze;
	}

	public void setIsAnalyze(Integer isAnalyze) {
		this.isAnalyze = isAnalyze;
	}
	
	@Length(min=0, max=50, message="handle_id长度不能超过 50 个字符")
	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}
	
	public Integer getIsApproval() {
		return isApproval;
	}

	public void setIsApproval(Integer isApproval) {
		this.isApproval = isApproval;
	}
	
	@Length(min=0, max=255, message="审批人长度不能超过 255 个字符")
	public String getApprovalBy() {
		return approvalBy;
	}

	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}
	
	@Length(min=0, max=255, message="审批备注长度不能超过 255 个字符")
	public String getApprovalMsg() {
		return approvalMsg;
	}

	public void setApprovalMsg(String approvalMsg) {
		this.approvalMsg = approvalMsg;
	}
}