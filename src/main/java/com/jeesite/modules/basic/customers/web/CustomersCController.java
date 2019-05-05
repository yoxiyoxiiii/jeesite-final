/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.customers.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.basic.customers.entity.CustomersC;
import com.jeesite.modules.basic.customers.service.CustomersCService;

/**
 * 客户管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/customers/customersC")
public class CustomersCController extends BaseController {

	@Autowired
	private CustomersCService customersCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CustomersC get(String id, boolean isNewRecord) {
		return customersCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("customers:customersC:view")
	@RequestMapping(value = {"list", ""})
	public String list(CustomersC customersC, Model model) {
		model.addAttribute("customersC", customersC);
		return "basic/customers/customersCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("customers:customersC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CustomersC> listData(CustomersC customersC, HttpServletRequest request, HttpServletResponse response) {
		customersC.setPage(new Page<>(request, response));
		Page<CustomersC> page = customersCService.findPage(customersC);
		return page;
	}
	
	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("customers:customersC:view")
	@RequestMapping(value = "form")
	public String form(CustomersC customersC, Model model) {
		model.addAttribute("customersC", customersC);
		return "basic/customers/customersCForm";
	}

	/**
	 * 保存客户
	 */
	@RequiresPermissions("customers:customersC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CustomersC customersC) {
		customersCService.save(customersC);
		return renderResult(Global.TRUE, text("保存客户成功！"));
	}
	
	/**
	 * 停用客户
	 */
	@RequiresPermissions("customers:customersC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(CustomersC customersC) {
		customersC.setStatus(CustomersC.STATUS_DISABLE);
		customersCService.updateStatus(customersC);
		return renderResult(Global.TRUE, text("停用客户成功"));
	}
	
	/**
	 * 启用客户
	 */
	@RequiresPermissions("customers:customersC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(CustomersC customersC) {
		customersC.setStatus(CustomersC.STATUS_NORMAL);
		customersCService.updateStatus(customersC);
		return renderResult(Global.TRUE, text("启用客户成功"));
	}
	
	/**
	 * 删除客户
	 */
	@RequiresPermissions("customers:customersC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CustomersC customersC) {
		customersCService.delete(customersC);
		return renderResult(Global.TRUE, text("删除客户成功！"));
	}
	
}