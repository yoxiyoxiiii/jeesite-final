/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 上报的具体数据Entity
 * @author 上报的具体数据
 * @version 2019-04-10
 */
@Table(name="task_data", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="task_id", attrName="taskId", label="数据对应的任务"),
		@Column(name="collection_data_id", attrName="collectionDataId", label="该数据关联的数据项"),
		@Column(name="description", attrName="description", label="具体的数据的文字信息"),
		@Column(name="file_id", attrName="fileId", label="文件数据的id"),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class TaskData extends DataEntity<TaskData> {
	
	private static final long serialVersionUID = 1L;
	private String taskId;		// 数据对应的任务
	private String collectionDataId;		// 该数据关联的数据项
	private String description;		// 具体的数据的文字信息
	private String fileId;		// 文件数据的id
	
	public TaskData() {
		this(null);
	}

	public TaskData(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="数据对应的任务长度不能超过 50 个字符")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=0, max=50, message="该数据关联的数据项长度不能超过 50 个字符")
	public String getCollectionDataId() {
		return collectionDataId;
	}

	public void setCollectionDataId(String collectionDataId) {
		this.collectionDataId = collectionDataId;
	}
	
	@Length(min=0, max=255, message="具体的数据的文字信息长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=100, message="文件数据的id长度不能超过 100 个字符")
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
}