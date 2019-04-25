/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesschecktemplat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
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
import com.jeesite.modules.businesschecktemplat.entity.BusinessCheckTemplate;
import com.jeesite.modules.businesschecktemplat.service.BusinessCheckTemplateService;

import java.util.Map;

/**
 * 考核模板Controller
 * @author 考核模板
 * @version 2019-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/businesschecktemplat/businessCheckTemplate")
public class BusinessCheckTemplateController extends BaseController {

	@Autowired
	private BusinessCheckTemplateService businessCheckTemplateService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessCheckTemplate get(String id, boolean isNewRecord) {
		return businessCheckTemplateService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesschecktemplat:businessCheckTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessCheckTemplate businessCheckTemplate, Model model) {
		model.addAttribute("businessCheckTemplate", businessCheckTemplate);
		return "modules/businesschecktemplat/businessCheckTemplateList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesschecktemplat:businessCheckTemplate:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessCheckTemplate> listData(BusinessCheckTemplate businessCheckTemplate, HttpServletRequest request, HttpServletResponse response) {
		businessCheckTemplate.setPage(new Page<>(request, response));
		Page<BusinessCheckTemplate> page = businessCheckTemplateService.findPage(businessCheckTemplate);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesschecktemplat:businessCheckTemplate:view")
	@RequestMapping(value = "form")
	public String form(BusinessCheckTemplate businessCheckTemplate, Model model) {
		model.addAttribute("businessCheckTemplate", businessCheckTemplate);
		return "modules/businesschecktemplat/businessCheckTemplateForm";
	}

	/**
	 * 保存考核模板
	 */
	@RequiresPermissions("businesschecktemplat:businessCheckTemplate:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessCheckTemplate businessCheckTemplate) {
		businessCheckTemplateService.save(businessCheckTemplate);
		return renderResult(Global.TRUE, text("保存考核模板成功！"));
	}
	
	/**
	 * 停用考核模板
	 */
	@RequiresPermissions("businesschecktemplat:businessCheckTemplate:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessCheckTemplate businessCheckTemplate) {
		businessCheckTemplate.setStatus(BusinessCheckTemplate.STATUS_DISABLE);
		businessCheckTemplateService.updateStatus(businessCheckTemplate);
		return renderResult(Global.TRUE, text("停用考核模板成功"));
	}
	
	/**
	 * 启用考核模板
	 */
	@RequiresPermissions("businesschecktemplat:businessCheckTemplate:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessCheckTemplate businessCheckTemplate) {
		businessCheckTemplate.setStatus(BusinessCheckTemplate.STATUS_NORMAL);
		businessCheckTemplateService.updateStatus(businessCheckTemplate);
		return renderResult(Global.TRUE, text("启用考核模板成功"));
	}
	
	/**
	 * 删除考核模板
	 */
	@RequiresPermissions("businesschecktemplat:businessCheckTemplate:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessCheckTemplate businessCheckTemplate) {
		businessCheckTemplateService.delete(businessCheckTemplate);
		return renderResult(Global.TRUE, text("删除考核模板成功！"));
	}

	@RequestMapping({"listSelect"})
	public String listSelect(BusinessCheckTemplate businessCheckTemplate, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("businessCheckTemplate", businessCheckTemplate);
		return "modules/businesschecktemplat/listSelect";
	}
	
}