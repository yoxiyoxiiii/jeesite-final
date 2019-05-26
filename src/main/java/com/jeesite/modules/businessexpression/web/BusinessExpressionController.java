/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessexpression.web;

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
import com.jeesite.modules.businessexpression.entity.BusinessExpression;
import com.jeesite.modules.businessexpression.service.BusinessExpressionService;

/**
 * business_ expressionController
 * @author BusinessExpression
 * @version 2019-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/businessexpression/businessExpression")
public class BusinessExpressionController extends BaseController {

	@Autowired
	private BusinessExpressionService businessExpressionService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessExpression get(String id, boolean isNewRecord) {
		return businessExpressionService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businessexpression:businessExpression:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessExpression businessExpression, Model model) {
		model.addAttribute("businessExpression", businessExpression);
		return "modules/businessexpression/businessExpressionList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businessexpression:businessExpression:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessExpression> listData(BusinessExpression businessExpression, HttpServletRequest request, HttpServletResponse response) {
		businessExpression.setPage(new Page<>(request, response));
		Page<BusinessExpression> page = businessExpressionService.findPage(businessExpression);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businessexpression:businessExpression:view")
	@RequestMapping(value = "form")
	public String form(BusinessExpression businessExpression, Model model) {
		model.addAttribute("businessExpression", businessExpression);
		return "modules/businessexpression/businessExpressionForm";
	}

	/**
	 * 保存business_ expression
	 */
	@RequiresPermissions("businessexpression:businessExpression:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessExpression businessExpression) {
		businessExpressionService.save(businessExpression);
		return renderResult(Global.TRUE, text("保存business_ expression成功！"));
	}
	
	/**
	 * 停用business_ expression
	 */
	@RequiresPermissions("businessexpression:businessExpression:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessExpression businessExpression) {
		businessExpression.setStatus(BusinessExpression.STATUS_DISABLE);
		businessExpressionService.updateStatus(businessExpression);
		return renderResult(Global.TRUE, text("停用business_ expression成功"));
	}
	
	/**
	 * 启用business_ expression
	 */
	@RequiresPermissions("businessexpression:businessExpression:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessExpression businessExpression) {
		businessExpression.setStatus(BusinessExpression.STATUS_NORMAL);
		businessExpressionService.updateStatus(businessExpression);
		return renderResult(Global.TRUE, text("启用business_ expression成功"));
	}
	
	/**
	 * 删除business_ expression
	 */
	@RequiresPermissions("businessexpression:businessExpression:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessExpression businessExpression) {
		businessExpressionService.delete(businessExpression);
		return renderResult(Global.TRUE, text("删除business_ expression成功！"));
	}
	
}