/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.test.entity.TestData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.demo.entity.Demo;
import com.jeesite.modules.demo.service.DemoService;


/* 模型 */
import java.io.IOException;
import com.jeesite.common.config.Global;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jeesite.modules.basic.printer.service.PrinterService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 样例数据Controller
 * @author sanye
 * @version 2019-04-30
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/demo")
public class DemoController extends BaseController {

	@Autowired
	private DemoService demoService;

	@Autowired
	private PrinterService printerService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Demo get(String id, boolean isNewRecord) {
		return demoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("demo:demo:view")
	@RequestMapping(value = {"list", ""})
	public String list(Demo demo, Model model) {
		model.addAttribute("demo", demo);
		return "modules/demo/demoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("demo:demo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Demo> listData(Demo demo, HttpServletRequest request, HttpServletResponse response) {
		demo.setPage(new Page<>(request, response));
		Page<Demo> page = demoService.findPage(demo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("demo:demo:view")
	@RequestMapping(value = "form")
	public String form(Demo demo, Model model) {
		model.addAttribute("demo", demo);
		return "modules/demo/demoForm";
	}

	/**
	 * 保存样例数据
	 */
	@RequiresPermissions("demo:demo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Demo demo) {
		demoService.save(demo);
		return renderResult(Global.TRUE, text("保存样例数据成功！"));
	}
	
	/**
	 * 停用样例数据
	 */
	@RequiresPermissions("demo:demo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Demo demo) {
		demo.setStatus(Demo.STATUS_DISABLE);
		demoService.updateStatus(demo);
		return renderResult(Global.TRUE, text("停用样例数据成功"));
	}
	
	/**
	 * 启用样例数据
	 */
	@RequiresPermissions("demo:demo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Demo demo) {
		demo.setStatus(Demo.STATUS_NORMAL);
		demoService.updateStatus(demo);
		return renderResult(Global.TRUE, text("启用样例数据成功"));
	}
	
	/**
	 * 删除样例数据
	 */
	@RequiresPermissions("demo:demo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Demo demo) {
		demoService.delete(demo);
		return renderResult(Global.TRUE, text("删除样例数据成功！"));
	}



	/**
	 * 报表数据打印
	 * @param offerC
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