/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.entity;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.dto.BusinessTargetDataInfoDto;
import com.jeesite.modules.sys.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 上报的数据Entity
 * @author 上报的数据
 * @version 2019-06-07
 */
@Table(name="business_target_data_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="target_data_item_id", attrName="businessTargetDataItem.id", label="数据项"),
		@Column(name="user_id", attrName="user.userCode", label="数据上报的人/部门"),
		@Column(name="target_id", attrName="businessTarget.id", label="考核细则"),
		@Column(name="stage_id", attrName="businessStageTarget2.id", label="期数"),
		@Column(name="data_info", attrName="dataInfo", label="具体的数据"),
		@Column(name="data_status", attrName="dataStatus", label="上报数据状态"),
		@Column(name="msg", attrName="msg", label="驳回信息"),
		@Column(name="update_by", attrName="updateBy", label="操作人"),
		@Column(name="data_type", attrName="dataType", label="数据类型"),
	},
		joinTable = {
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTargetDataItem.class, alias = "businessTargetDataItem",
						on = "businessTargetDataItem.id = a.target_data_item_id", attrName = "businessTargetDataItem",
						columns = {@Column(includeEntity = BusinessTargetDataItem.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTarget2.class, alias = "businessTarget",
						on = "businessTarget.id = a.target_id", attrName = "businessTarget",
						columns = {@Column(includeEntity = BusinessTarget2.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "user",
						on = "user.user_code = a.user_id", attrName = "user",
						columns = {@Column(includeEntity = User.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessStageTarget2.class, alias = "businessStageTarget2",
						on = "businessStageTarget2.id = a.stage_id", attrName = "businessStageTarget2",
						columns = {@Column(includeEntity = BusinessStageTarget2.class)}),

		},
		orderBy="a.id DESC"
)
public class BusinessTargetDataInfo extends DataEntity<BusinessTargetDataInfo> {
	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String targetDataItemId; // 数据项
	@Getter
	@Setter
	private BusinessTargetDataItem businessTargetDataItem; // 数据项
	@Getter
	@Setter
	private BusinessTarget2 businessTarget; //考核细则
	@Getter
	@Setter
	private User user;		// 数据上报的人/部门
	private String dataInfo;		// 具体的数据
	private Integer dataType;		// 数据类型

	@Getter
	@Setter
	private String  dataStatus;		// 上报数据状态
	@Getter
	@Setter
	private String  msg;		// 驳回信息
	@Getter
	@Setter
	private String  updateBy;		// 操作人

	@Getter
	@Setter
	private BusinessStageTarget2 businessStageTarget2;//期数

	private List<BusinessTargetDataInfoDto> dataInfoDtoList =  ListUtils.newArrayList();
	public List<BusinessTargetDataInfoDto> getDataInfoDtoList() {
		return dataInfoDtoList;
	}

	public void setDataInfoDtoList(List<BusinessTargetDataInfoDto> dataInfoDtoList) {
		this.dataInfoDtoList = dataInfoDtoList;
	}



	public BusinessTargetDataInfo() {
		this(null);
	}

	public BusinessTargetDataInfo(String id){
		super(id);
	}
	@Length(min=0, max=255, message="具体的数据长度不能超过 255 个字符")
	public String getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(String dataInfo) {
		this.dataInfo = dataInfo;
	}
	
	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
}