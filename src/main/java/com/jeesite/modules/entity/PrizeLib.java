/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import javax.validation.constraints.NotBlank;

import com.jeesite.modules.sys.entity.Office;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 奖扣分类Entity
 *
 * @author sanye
 * @version 2019-05-02
 */
@Table(name = "biz_prize_lib", alias = "a", columns = {
        @Column(name = "id", attrName = "id", label = "编号", isPK = true),
        @Column(name = "rule_remark", attrName = "ruleRemark", label = "奖扣说明", queryType = QueryType.LIKE),
        @Column(name = "prize_type_id", attrName = "prizeType.id", label = "奖扣分类", queryType = QueryType.EQ),
        @Column(name = "dept_id", attrName = "office.officeCode", label = "部门"),
        @Column(name = "is_add", attrName = "isAdd", label = "类型"),
        @Column(name = "limit", attrName = "limit", label = "加分控制", queryType = QueryType.GT),
        @Column(name = "formula", attrName = "formula", label = "计分规则", isQuery = false),
        @Column(includeEntity = DataEntity.class),
        @Column(name = "audit_by", attrName = "auditBy", label = "审批者", isInsert = false, isUpdate = false, isQuery = false),
        @Column(name = "audit_date", attrName = "auditDate", label = "审批时间", isInsert = false, isUpdate = false, isQuery = false),
        @Column(name = "extend1_name", attrName = "extend1Name", label = "扩展字段1名称", isQuery = false),
        @Column(name = "extend1_option", attrName = "extend1Option", label = "扩展字段1选项", isQuery = false),
        @Column(name = "extend2_name", attrName = "extend2Name", label = "扩展字段2名称", isQuery = false),
        @Column(name = "extend2_option", attrName = "extend2Option", label = "扩展字段2选项", isQuery = false),
        @Column(name = "extend3_name", attrName = "extend3Name", label = "扩展字段3名称", isQuery = false),
        @Column(name = "extend3_option", attrName = "extend3Option", label = "扩展字段3选项", isQuery = false),
        @Column(name = "extend4_name", attrName = "extend4Name", label = "扩展字段4名称", isQuery = false),
        @Column(name = "extend4_option", attrName = "extend4Option", label = "扩展字段4选项", isQuery = false),
        @Column(name = "extend5_name", attrName = "extend5Name", label = "扩展字段5名称", isQuery = false),
        @Column(name = "extend5_option", attrName = "extend5Option", label = "扩展字段5选项", isQuery = false),
        @Column(name = "extend6_name", attrName = "extend6Name", label = "扩展字段6名称", isQuery = false),
        @Column(name = "extend6_option", attrName = "extend6Option", label = "扩展字段6选项", isQuery = false),
},
        joinTable = {
                @JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = PrizeType.class, alias = "prizeType",
                        on = "prizeType.id = a.prize_type_id", attrName = "prizeType",
                        columns = {@Column(includeEntity = PrizeType.class)}),
                @JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = Office.class, alias = "office",
                        on = "office.office_code = a.dept_id", attrName = "office",
                        columns = {@Column(includeEntity = Office.class)})},
        orderBy = "a.update_date DESC"
)
public class PrizeLib extends DataEntity<PrizeLib> {

    private static final long serialVersionUID = 1L;
    private String ruleRemark;        // 奖扣说明
    private String prizeTypeId;        // 奖扣分类
    @Setter
    @Getter
    private PrizeType prizeType;    // 奖扣分类对象
    @Setter
    @Getter
    private Office office;    // 奖扣分类对象
//    private String deptId;        // 部门
    private String isAdd;        // 类型
    private Double limit;        // 加分控制
    private String formula;        // 计分规则
    private String auditBy;        // 审批者
    private Date auditDate;        // 审批时间
    private String extend1Name;        // 扩展字段1名称
    private String extend1Option;        // 扩展字段1选项
    private String extend2Name;        // 扩展字段2名称
    private String extend2Option;        // 扩展字段2选项
    private String extend3Name;        // 扩展字段3名称
    private String extend3Option;        // 扩展字段3选项
    private String extend4Name;        // 扩展字段4名称
    private String extend4Option;        // 扩展字段4选项
    private String extend5Name;        // 扩展字段5名称
    private String extend5Option;        // 扩展字段5选项
    private String extend6Name;        // 扩展字段6名称
    private String extend6Option;        // 扩展字段6选项

    public PrizeLib() {
        this(null);
    }

    public PrizeLib(String id) {
        super(id);
    }

    @NotBlank(message = "奖扣说明不能为空")
    @Length(min = 0, max = 500, message = "奖扣说明长度不能超过 500 个字符")
    public String getRuleRemark() {
        return ruleRemark;
    }

    public void setRuleRemark(String ruleRemark) {
        this.ruleRemark = ruleRemark;
    }

    //
//	@NotBlank(message="奖扣分类不能为空")
//	@Length(min=0, max=64, message="奖扣分类长度不能超过 64 个字符")
//	public String getPrizeTypeId() {
//		return prizeTypeId;
//	}
//
//	public void setPrizeTypeId(String prizeTypeId) {
//		this.prizeTypeId = prizeTypeId;
//	}
//
//    @NotBlank(message = "部门不能为空")
//    public Office getDeptId() {
//        return office;
//    }
//
//    public void setDeptId(Office office) {
//        this.office = office;
//    }

    @NotBlank(message = "类型不能为空")
    @Length(min = 0, max = 3, message = "类型长度不能超过 3 个字符")
    public String getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(String isAdd) {
        this.isAdd = isAdd;
    }


    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    @Length(min = 0, max = 200, message = "计分规则长度不能超过 200 个字符")
    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Length(min = 0, max = 64, message = "审批者长度不能超过 64 个字符")
    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    @Length(min = 0, max = 64, message = "扩展字段1名称长度不能超过 64 个字符")
    public String getExtend1Name() {
        return extend1Name;
    }

    public void setExtend1Name(String extend1Name) {
        this.extend1Name = extend1Name;
    }

    @Length(min = 0, max = 100, message = "扩展字段1选项长度不能超过 100 个字符")
    public String getExtend1Option() {
        return extend1Option;
    }

    public void setExtend1Option(String extend1Option) {
        this.extend1Option = extend1Option;
    }

    @Length(min = 0, max = 64, message = "扩展字段2名称长度不能超过 64 个字符")
    public String getExtend2Name() {
        return extend2Name;
    }

    public void setExtend2Name(String extend2Name) {
        this.extend2Name = extend2Name;
    }

    @Length(min = 0, max = 100, message = "扩展字段2选项长度不能超过 100 个字符")
    public String getExtend2Option() {
        return extend2Option;
    }

    public void setExtend2Option(String extend2Option) {
        this.extend2Option = extend2Option;
    }

    @Length(min = 0, max = 64, message = "扩展字段3名称长度不能超过 64 个字符")
    public String getExtend3Name() {
        return extend3Name;
    }

    public void setExtend3Name(String extend3Name) {
        this.extend3Name = extend3Name;
    }

    @Length(min = 0, max = 100, message = "扩展字段3选项长度不能超过 100 个字符")
    public String getExtend3Option() {
        return extend3Option;
    }

    public void setExtend3Option(String extend3Option) {
        this.extend3Option = extend3Option;
    }

    @Length(min = 0, max = 64, message = "扩展字段4名称长度不能超过 64 个字符")
    public String getExtend4Name() {
        return extend4Name;
    }

    public void setExtend4Name(String extend4Name) {
        this.extend4Name = extend4Name;
    }

    @Length(min = 0, max = 100, message = "扩展字段4选项长度不能超过 100 个字符")
    public String getExtend4Option() {
        return extend4Option;
    }

    public void setExtend4Option(String extend4Option) {
        this.extend4Option = extend4Option;
    }

    @Length(min = 0, max = 64, message = "扩展字段5名称长度不能超过 64 个字符")
    public String getExtend5Name() {
        return extend5Name;
    }

    public void setExtend5Name(String extend5Name) {
        this.extend5Name = extend5Name;
    }

    @Length(min = 0, max = 100, message = "扩展字段5选项长度不能超过 100 个字符")
    public String getExtend5Option() {
        return extend5Option;
    }

    public void setExtend5Option(String extend5Option) {
        this.extend5Option = extend5Option;
    }

    @Length(min = 0, max = 64, message = "扩展字段6名称长度不能超过 64 个字符")
    public String getExtend6Name() {
        return extend6Name;
    }

    public void setExtend6Name(String extend6Name) {
        this.extend6Name = extend6Name;
    }

    @Length(min = 0, max = 100, message = "扩展字段6选项长度不能超过 100 个字符")
    public String getExtend6Option() {
        return extend6Option;
    }

    public void setExtend6Option(String extend6Option) {
        this.extend6Option = extend6Option;
    }

}