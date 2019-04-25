/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesschecktemplateinfo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.businesschecktemplat.entity.BusinessCheckTemplate;
import com.jeesite.modules.businesschecktemplat.service.BusinessCheckTemplateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.businesschecktemplateinfo.entity.BusinessCheckTemplateInfo;
import com.jeesite.modules.businesschecktemplateinfo.service.BusinessCheckTemplateInfoService;
import sun.swing.StringUIClientPropertyKey;

/**
 * 考核指标Controller
 * @author BusinessCheckTemplateInfo
 * @version 2019-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/businesschecktemplateinfo/businessCheckTemplateInfo")
public class BusinessCheckTemplateInfoController extends BaseController {

	@Autowired
	private BusinessCheckTemplateInfoService businessCheckTemplateInfoService;

	@Autowired
	private BusinessCheckTemplateService businessCheckTemplateService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessCheckTemplateInfo get(String id, boolean isNewRecord) {
		return businessCheckTemplateInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesschecktemplateinfo:businessCheckTemplateInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessCheckTemplateInfo businessCheckTemplateInfo, Model model) {
		model.addAttribute("businessCheckTemplateInfo", businessCheckTemplateInfo);
		return "modules/businesschecktemplateinfo/businessCheckTemplateInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesschecktemplateinfo:businessCheckTemplateInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessCheckTemplateInfo> listData(BusinessCheckTemplateInfo businessCheckTemplateInfo, HttpServletRequest request, HttpServletResponse response) {
		businessCheckTemplateInfo.setPage(new Page<>(request, response));
		Page<BusinessCheckTemplateInfo> page = businessCheckTemplateInfoService.findPage(businessCheckTemplateInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesschecktemplateinfo:businessCheckTemplateInfo:view")
	@RequestMapping(value = "form")
	public String form(BusinessCheckTemplateInfo businessCheckTemplateInfo, String templateId, Model model) {
		if (!StringUtils.isEmpty(templateId)) {
			BusinessCheckTemplate businessCheckTemplate = businessCheckTemplateService.get(templateId);
			businessCheckTemplateInfo.setBusinessCheckTemplate(businessCheckTemplate);
			model.addAttribute("businessCheckTemplateInfo", businessCheckTemplateInfo);
			return "modules/businesschecktemplateinfo/businessCheckTemplateInfoFormHasTarget";
		}
		model.addAttribute("businessCheckTemplateInfo", businessCheckTemplateInfo);
		return "modules/businesschecktemplateinfo/businessCheckTemplateInfoForm";

	}

	/**
	 * 保存考核指标
	 */
	@RequiresPermissions("businesschecktemplateinfo:businessCheckTemplateInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		businessCheckTemplateInfoService.save(businessCheckTemplateInfo);
		return renderResult(Global.TRUE, text("保存考核指标成功！"));
	}
	
	/**
	 * 停用考核指标
	 */
	@RequiresPermissions("businesschecktemplateinfo:businessCheckTemplateInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		businessCheckTemplateInfo.setStatus(BusinessCheckTemplateInfo.STATUS_DISABLE);
		businessCheckTemplateInfoService.updateStatus(businessCheckTemplateInfo);
		return renderResult(Global.TRUE, text("停用考核指标成功"));
	}
	
	/**
	 * 启用考核指标
	 */
	@RequiresPermissions("businesschecktemplateinfo:businessCheckTemplateInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		businessCheckTemplateInfo.setStatus(BusinessCheckTemplateInfo.STATUS_NORMAL);
		businessCheckTemplateInfoService.updateStatus(businessCheckTemplateInfo);
		return renderResult(Global.TRUE, text("启用考核指标成功"));
	}
	
	/**
	 * 删除考核指标
	 */
	@RequiresPermissions("businesschecktemplateinfo:businessCheckTemplateInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		businessCheckTemplateInfoService.delete(businessCheckTemplateInfo);
		return renderResult(Global.TRUE, text("删除考核指标成功！"));
	}
	
}