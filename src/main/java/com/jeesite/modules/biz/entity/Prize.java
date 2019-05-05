/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.entity;

import javax.validation.constraints.NotBlank;

import com.jeesite.modules.sys.entity.Office;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 奖扣记录Entity
 * @author sanye
 * @version 2019-05-03
 */
@Table(name="biz_prize", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="dept_id", attrName="office.officeCode", label="主体单位"),
		@Column(name="dept_join_id", attrName="officeJoin.officeCode", label="参与单位"),
		@Column(name="prize_lib_id", attrName="prizeLib.id", label="奖扣指标ID"),
		@Column(name="prize_date", attrName="prizeDate", label="获奖时间"),
		@Column(name="evidence", attrName="evidence", label="情况说明", queryType=QueryType.LIKE),
		@Column(name="score", attrName="score", label="奖扣结果"),
		@Column(includeEntity=DataEntity.class),
//		@Column(name="audit_by", attrName="auditBy", label="审批者", isInsert=false, isUpdate=false, isQuery=false),
//		@Column(name="audit_date", attrName="auditDate", label="审批时间", isInsert=false, isUpdate=false, isQuery=false),
		@Column(name="extend1_value", attrName="extend1Value", label="扩展字段1值"),
		@Column(name="extend2_value", attrName="extend2Value", label="扩展字段2值"),
		@Column(name="extend3_value", attrName="extend3Value", label="扩展字段3值"),
		@Column(name="extend4_value", attrName="extend4Value", label="扩展字段4值"),
		@Column(name="extend5_value", attrName="extend5Value", label="扩展字段5值"),
		@Column(name="extend6_value", attrName="extend6Value", label="扩展字段6值"),
	},
		joinTable = {
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = PrizeLib.class, alias = "prizeLib",
						on = "prizeLib.id = a.prize_lib_id", attrName = "prizeLib",
						columns = {@Column(includeEntity = PrizeLib.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = Office.class, alias = "office",
						on = "office.office_code = a.dept_id", attrName = "office",
						columns = {@Column(includeEntity = Office.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = Office.class, alias = "officeJoin",
						on = "officeJoin.office_code = a.dept_join_id", attrName = "officeJoin",
						columns = {@Column(includeEntity = Office.class)})},

		orderBy="a.update_date DESC"
)
public class Prize extends DataEntity<Prize> {
	
	private static final long serialVersionUID = 1L;
//	private String deptId;		// 主体单位
//	private String deptJoinId;		// 参与单位
//	private String prizeLibId;		// 奖扣指标ID
	private Date prizeDate;		// 获奖时间
	private String evidence;		// 情况说明
	private Double score;		// 奖扣结果
//	private String auditBy;		// 审批者
//	private Date auditDate;		// 审批时间
	private String extend1Value;		// 扩展字段1值
	private String extend2Value;		// 扩展字段2值
	private String extend3Value;		// 扩展字段3值
	private String extend4Value;		// 扩展字段4值
	private String extend5Value;		// 扩展字段5值
	private String extend6Value;		// 扩展字段6值

	@Setter
	@Getter
	private PrizeLib prizeLib;    // 奖扣分类对象

	@Setter
	@Getter
	private Office office;    // 奖扣分类对象

	@Setter
	@Getter
	private Office officeJoin;    // 奖扣分类对象
	public Prize() {
		this(null);
	}

	public Prize(String id){
		super(id);
	}
//
//	@NotBlank(message="主体单位不能为空")
//	@Length(min=0, max=64, message="主体单位长度不能超过 64 个字符")
//	public String getDeptId() {
//		return deptId;
//	}
//
//	public void setDeptId(String deptId) {
//		this.deptId = deptId;
//	}
	
//	@Length(min=0, max=64, message="参与单位长度不能超过 64 个字符")
//	public String getDeptJoinId() {
//		return deptJoinId;
//	}
//
//	public void setDeptJoinId(String deptJoinId) {
//		this.deptJoinId = deptJoinId;
//	}
	
//	@NotBlank(message="奖扣指标ID不能为空")
//	@Length(min=0, max=64, message="奖扣指标ID长度不能超过 64 个字符")
//	public String getPrizeLibId() {
//		return prizeLibId;
//	}
//
//	public void setPrizeLibId(String prizeLibId) {
//		this.prizeLibId = prizeLibId;
//	}
//
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="获奖时间不能为空")
	public Date getPrizeDate() {
		return prizeDate;
	}

	public void setPrizeDate(Date prizeDate) {
		this.prizeDate = prizeDate;
	}
	
	@NotBlank(message="情况说明不能为空")
	@Length(min=0, max=500, message="情况说明长度不能超过 500 个字符")
	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
//	@Length(min=0, max=64, message="审批者长度不能超过 64 个字符")
//	public String getAuditBy() {
//		return auditBy;
//	}
//
//	public void setAuditBy(String auditBy) {
//		this.auditBy = auditBy;
//	}
//
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	public Date getAuditDate() {
//		return auditDate;
//	}
//
//	public void setAuditDate(Date auditDate) {
//		this.auditDate = auditDate;
//	}
	
	@Length(min=0, max=64, message="扩展字段1值长度不能超过 64 个字符")
	public String getExtend1Value() {
		return extend1Value;
	}

	public void setExtend1Value(String extend1Value) {
		this.extend1Value = extend1Value;
	}
	
	@Length(min=0, max=64, message="扩展字段2值长度不能超过 64 个字符")
	public String getExtend2Value() {
		return extend2Value;
	}

	public void setExtend2Value(String extend2Value) {
		this.extend2Value = extend2Value;
	}
	
	@Length(min=0, max=64, message="扩展字段3值长度不能超过 64 个字符")
	public String getExtend3Value() {
		return extend3Value;
	}

	public void setExtend3Value(String extend3Value) {
		this.extend3Value = extend3Value;
	}
	
	@Length(min=0, max=64, message="扩展字段4值长度不能超过 64 个字符")
	public String getExtend4Value() {
		return extend4Value;
	}

	public void setExtend4Value(String extend4Value) {
		this.extend4Value = extend4Value;
	}
	
	@Length(min=0, max=64, message="扩展字段5值长度不能超过 64 个字符")
	public String getExtend5Value() {
		return extend5Value;
	}

	public void setExtend5Value(String extend5Value) {
		this.extend5Value = extend5Value;
	}
	
	@Length(min=0, max=64, message="扩展字段6值长度不能超过 64 个字符")
	public String getExtend6Value() {
		return extend6Value;
	}

	public void setExtend6Value(String extend6Value) {
		this.extend6Value = extend6Value;
	}
	
}