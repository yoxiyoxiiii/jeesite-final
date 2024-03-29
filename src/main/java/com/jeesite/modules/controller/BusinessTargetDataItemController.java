/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.controller;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.entity.BusinessTarget2;
import com.jeesite.modules.service.BusinessTarget2Service;
import com.jeesite.modules.entity.BusinessTargetDataItem;
import com.jeesite.modules.service.BusinessTargetDataItemService;
import com.jeesite.modules.entity.StageTarget;
import com.jeesite.modules.service.StageTargetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 数据采集项Controller
 * @author BusinessTargetDataItem
 * @version 2019-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstargetdataitem/businessTargetDataItem")
public class BusinessTargetDataItemController extends BaseController {

	@Autowired
	private BusinessTargetDataItemService businessTargetDataItemService;

	@Autowired
	private BusinessTarget2Service businessTargetService;

	@Autowired
	private StageTargetService stageTargetService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessTargetDataItem get(String id, boolean isNewRecord) {
		return businessTargetDataItemService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstargetdataitem:businessTargetDataItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessTargetDataItem businessTargetDataItem, Model model) {
		model.addAttribute("businessTargetDataItem", businessTargetDataItem);
		return "modules/businesstargetdataitem/businessTargetDataItemList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstargetdataitem:businessTargetDataItem:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessTargetDataItem> listData(BusinessTargetDataItem businessTargetDataItem, HttpServletRequest request, HttpServletResponse response) {
		businessTargetDataItem.setPage(new Page<>(request, response));
		Page<BusinessTargetDataItem> page = businessTargetDataItemService.findPage(businessTargetDataItem);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargetdataitem:businessTargetDataItem:view")
	@RequestMapping(value = "form")
	public String form(BusinessTargetDataItem businessTargetDataItem, String stageId, BusinessTarget2 businessTarget, StageTarget stageTarget, Model model) {
		if (stageId != null) {
			stageTarget = stageTargetService.get(stageId);
			businessTargetDataItem.setStageTargets(stageTarget);
			model.addAttribute("businessTargetDataItem", businessTargetDataItem);
			return "modules/businesstargetdataitem/businessTargetDataItemFormHasStageTarget";
		}
		List<BusinessTarget2> businessTargetList = businessTargetService.findList(businessTarget);
		List<StageTarget> stageTargetList = stageTargetService.findList(stageTarget);
		if (businessTargetList.size() == 0) {
			businessTargetList = businessTargetService.findList();
		}
		if (stageTargetList.size() == 0) {
			stageTargetList = stageTargetService.findList();
		}
		model.addAttribute("businessTargetDataItem", businessTargetDataItem);
		model.addAttribute("businessTargetList", businessTargetList);
		model.addAttribute("stageTargetList", stageTargetList);
		return "modules/businesstargetdataitem/businessTargetDataItemForm";
	}

	/**
	 * 保存数据采集项
	 */
	@RequiresPermissions("businesstargetdataitem:businessTargetDataItem:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessTargetDataItem businessTargetDataItem) {
		businessTargetDataItemService.save(businessTargetDataItem);
		return renderResult(Global.TRUE, text("保存数据采集项成功！"));
	}

	/**
	 * 根据targetId 来获取下面的数据采集项
	 */
	@RequiresPermissions("businesstargetdataitem:businessTargetDataItem:view")
	@GetMapping(value = "findByTargetId")
	@ResponseBody
	public List<BusinessTargetDataItem> findByTargetId(String targetId) {
		List<BusinessTargetDataItem> list = businessTargetDataItemService.findByBusinessTargetId(targetId);
		return list;
	}

	/**
	 * 停用数据采集项
	 */
	@RequiresPermissions("businesstargetdataitem:businessTargetDataItem:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessTargetDataItem businessTargetDataItem) {
		businessTargetDataItem.setStatus(BusinessTargetDataItem.STATUS_DISABLE);
		businessTargetDataItemService.updateStatus(businessTargetDataItem);
		return renderResult(Global.TRUE, text("停用数据采集项成功"));
	}
	
	/**
	 * 启用数据采集项
	 */
	@RequiresPermissions("businesstargetdataitem:businessTargetDataItem:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessTargetDataItem businessTargetDataItem) {
		businessTargetDataItem.setStatus(BusinessTargetDataItem.STATUS_NORMAL);
		businessTargetDataItemService.updateStatus(businessTargetDataItem);
		return renderResult(Global.TRUE, text("启用数据采集项成功"));
	}
	
	/**
	 * 删除数据采集项
	 */
	@RequiresPermissions("businesstargetdataitem:businessTargetDataItem:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTargetDataItem businessTargetDataItem) {
		businessTargetDataItemService.delete(businessTargetDataItem);
		return renderResult(Global.TRUE, text("删除数据采集项成功！"));
	}

	@RequestMapping({"listSelect"})
	public String listSelect(BusinessTarget2 businessTarget, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("businessTarget", businessTarget);
		return "modules/businesstargetdataitem/listSelect";
}
	
}