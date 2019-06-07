/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargetdatainfo.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.businesstarget2.entity.BusinessTarget2;
import com.jeesite.modules.businesstargetdataitem.entity.BusinessTargetDataItem;
import com.jeesite.modules.sys.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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
		@Column(name="data_info", attrName="dataInfo", label="具体的数据"),
		@Column(name="data_type", attrName="dataType", label="数据类型"),
	},
		joinTable = {
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTargetDataItem.class, alias = "businessTargetDataItem",
						on = "businessTargetDataItem.id = a.target_data_item_id", attrName = "businessTargetDataItem",
						columns = {@Column(includeEntity = BusinessTargetDataItem.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = BusinessTarget2.class, alias = "businessTarget",
						on = "businessTarget.id = a.target_data_item_id", attrName = "businessTarget",
						columns = {@Column(includeEntity = BusinessTarget2.class)}),
				@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "user",
						on = "user.user_code = a.user_id", attrName = "user",
						columns = {@Column(includeEntity = User.class)}),

		},
		orderBy="a.id DESC"
)
public class BusinessTargetDataInfo extends DataEntity<BusinessTargetDataInfo> {
	
	private static final long serialVersionUID = 1L;

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
	
	public BusinessTargetDataInfo() {
		this(null);
	}

	public BusinessTargetDataInfo(String id){
		super(id);
	}
	
//	@Length(min=0, max=50, message="数据项长度不能超过 50 个字符")
//	public String getTargetDataItemId() {
//		return targetDataItemId;
//	}
//
//	public void setTargetDataItemId(String targetDataItemId) {
//		this.targetDataItemId = targetDataItemId;
//	}
	
//	@Length(min=0, max=50, message="数据上报的人/部门长度不能超过 50 个字符")
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	
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