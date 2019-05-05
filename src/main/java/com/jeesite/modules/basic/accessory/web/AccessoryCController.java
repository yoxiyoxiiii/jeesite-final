/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.accessory.web;

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
import com.jeesite.modules.basic.accessory.entity.AccessoryC;
import com.jeesite.modules.basic.accessory.service.AccessoryCService;

/**
 * 附件管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/accessory/accessoryC")
public class AccessoryCController extends BaseController {

	@Autowired
	private AccessoryCService accessoryCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public AccessoryC get(String id, boolean isNewRecord) {
		return accessoryCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("accessory:accessoryC:view")
	@RequestMapping(value = {"list", ""})
	public String list(AccessoryC accessoryC, Model model) {
		model.addAttribute("accessoryC", accessoryC);
		return "basic/accessory/accessoryCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("accessory:accessoryC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<AccessoryC> listData(AccessoryC accessoryC, HttpServletRequest request, HttpServletResponse response) {
		accessoryC.setPage(new Page<>(request, response));
		Page<AccessoryC> page = accessoryCService.findPage(accessoryC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("accessory:accessoryC:view")
	@RequestMapping(value = "form")
	public String form(AccessoryC accessoryC, Model model) {
		model.addAttribute("accessoryC", accessoryC);
		return "basic/accessory/accessoryCForm";
	}

	/**
	 * 保存附件
	 */
	@RequiresPermissions("accessory:accessoryC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated AccessoryC accessoryC) {
		accessoryCService.save(accessoryC);
		return renderResult(Global.TRUE, text("保存附件成功！"));
	}
	
	/**
	 * 停用附件
	 */
	@RequiresPermissions("accessory:accessoryC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(AccessoryC accessoryC) {
		accessoryC.setStatus(AccessoryC.STATUS_DISABLE);
		accessoryCService.updateStatus(accessoryC);
		return renderResult(Global.TRUE, text("停用附件成功"));
	}
	
	/**
	 * 启用附件
	 */
	@RequiresPermissions("accessory:accessoryC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(AccessoryC accessoryC) {
		accessoryC.setStatus(AccessoryC.STATUS_NORMAL);
		accessoryCService.updateStatus(accessoryC);
		return renderResult(Global.TRUE, text("启用附件成功"));
	}
	
	/**
	 * 删除附件
	 */
	@RequiresPermissions("accessory:accessoryC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(AccessoryC accessoryC) {
		accessoryCService.delete(accessoryC);
		return renderResult(Global.TRUE, text("删除附件成功！"));
	}
	
}