/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.notifyc.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 通知管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-17
 */
@Table(name="notify_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="title", attrName="title", label="通知标题", queryType=QueryType.LIKE),
		@Column(name="content", attrName="content", label="通知内容", queryType=QueryType.LIKE),
		@Column(name="notify_date", attrName="notifyDate", label="通知时间"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class NotifyC extends DataEntity<NotifyC> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 通知标题
	private String content;		// 通知内容
	private Date notifyDate;		// 通知时间
	
	public NotifyC() {
		this(null);
	}

	public NotifyC(String id){
		super(id);
	}
	
	@NotBlank(message="通知标题不能为空")
	@Length(min=0, max=100, message="通知标题长度不能超过 100 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank(message="通知内容不能为空")
	@Length(min=0, max=500, message="通知内容长度不能超过 500 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	
}