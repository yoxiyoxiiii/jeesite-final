/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.controller;

import com.jeesite.common.config.Global;
import com.jeesite.modules.service.PrinterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.entity.TestData;
import com.jeesite.modules.service.TestDataService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示实例Controller
 * @author ThinkGem
 * @version 2018-03-24
 */
@Controller
@RequestMapping(value = "${adminPath}/demo")
public class DemoController extends BaseController {

	@Autowired
	private TestDataService testDataService;
	@Autowired
	private PrinterService printerService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TestData get(String id, boolean isNewRecord) {
		return testDataService.get(id, isNewRecord);
	}
	
	/**
	 * DataGrid
	 */
	@RequiresPermissions("test:testData:view")
	@RequestMapping(value = "dataGrid/{viewName}")
	public String dataGrid(@PathVariable String viewName, TestData testData, Model model) {
		return "modules/demo/demoDataGrid" + StringUtils.cap(viewName);
	}
	
	/**
	 * Form
	 */
	@RequiresPermissions("test:testData:view")
	@RequestMapping(value = "form/{viewName}")
	public String form(@PathVariable String viewName, TestData testData, Model model) {
		return "modules/demo/demoForm" + StringUtils.cap(viewName);
	}


	/**
	 * 报表数据打印
	 * @param
	 * @param response
	 * @throws IOException
	 */
	@RequiresPermissions("test:testData:view")
	@RequestMapping(value = "print")
	@ResponseBody
	public String print(TestData testData, HttpServletResponse response){
		try{
			printerService.printDemo(testData, response);
			return renderResult(Global.TRUE, text("打印报价成功！"));
		}catch(IOException e){
			return renderResult(Global.FALSE, text("打印出错！"));
		}
	}
}