package com.jeesite.modules.basic.statistics.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 数据统计Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-10
 */

@Table(columns = {
		@Column(name = "data", attrName = "data"),
		@Column(name = "datetime", attrName = "datetime")
		})
public class Data extends DataEntity<Data>{
	private double data;
	private String datetime;
	
	public double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	
	
}
