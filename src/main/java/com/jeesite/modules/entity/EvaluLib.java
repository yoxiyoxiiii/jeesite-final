/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 民主测评明细树表Entity
 * @author sanye
 * @version 2019-05-17
 */
@Table(name="biz_evalu_lib", alias="a", columns={
		@Column(name="tree_code", attrName="treeCode", label="节点编码", isPK=true),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="tree_name", attrName="treeName", label="评测项", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="evalu_id", attrName="evaluId", label="所属测评表"),
		@Column(name="score", attrName="score", label="分数权重", isQuery=false),
		@Column(name="eval_select_type", attrName="evalSelectType", label="计分方式", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.tree_sorts, a.tree_code"
)
public class EvaluLib extends TreeEntity<EvaluLib> {
	
	private static final long serialVersionUID = 1L;
	private String treeCode;		// 节点编码
	private String treeName;		// 评测项
	private String evaluId;		// 所属测评表
	private Double score;		// 分数权重
	private String evalSelectType;		// 计分方式
	
	public EvaluLib() {
		this(null);
	}

	public EvaluLib(String id){
		super(id);
	}
	
	@Override
	public EvaluLib getParent() {
		return parent;
	}

	@Override
	public void setParent(EvaluLib parent) {
		this.parent = parent;
	}
	
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}
	
	@NotBlank(message="评测项不能为空")
	@Length(min=0, max=200, message="评测项长度不能超过 200 个字符")
	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	
	@NotBlank(message="所属测评表不能为空")
	@Length(min=0, max=64, message="所属测评表长度不能超过 64 个字符")
	public String getEvaluId() {
		return evaluId;
	}

	public void setEvaluId(String evaluId) {
		this.evaluId = evaluId;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	@NotBlank(message="计分方式不能为空")
	@Length(min=0, max=200, message="计分方式长度不能超过 200 个字符")
	public String getEvalSelectType() {
		return evalSelectType;
	}

	public void setEvalSelectType(String evalSelectType) {
		this.evalSelectType = evalSelectType;
	}
	
}