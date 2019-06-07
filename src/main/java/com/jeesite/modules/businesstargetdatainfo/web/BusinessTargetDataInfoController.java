/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargetdatainfo.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.businesstarget2.entity.BusinessTarget2;
import com.jeesite.modules.businesstarget2.service.BusinessTarget2Service;
import com.jeesite.modules.businesstargetdatainfo.entity.BusinessTargetDataInfo;
import com.jeesite.modules.businesstargetdatainfo.service.BusinessTargetDataInfoService;
import com.jeesite.modules.businesstargetdataitem.entity.BusinessTargetDataItem;
import com.jeesite.modules.businesstargetdataitem.service.BusinessTargetDataItemService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上报的数据Controller
 * @author 上报的数据
 * @version 2019-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstargetdatainfo/businessTargetDataInfo")
public class BusinessTargetDataInfoController extends BaseController {

	@Autowired
	private BusinessTargetDataInfoService businessTargetDataInfoService;

	@Autowired
	private BusinessTarget2Service businessTargetService;

	@Autowired
	private BusinessTargetDataItemService dataItemService;

	@Autowired
	private UserService userService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessTargetDataInfo get(String id, boolean isNewRecord) {
		return businessTargetDataInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessTargetDataInfo businessTargetDataInfo, String userCode,Model model) {
		model.addAttribute("userCode", userCode);
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		return "modules/businesstargetdatainfo/businessTargetDataInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessTargetDataInfo> listData(BusinessTargetDataInfo businessTargetDataInfo, String userCode,HttpServletRequest request, HttpServletResponse response) {

		businessTargetDataInfo.setPage(new Page<>(request, response));
		if (StringUtils.isEmpty(userCode)) {
			Page<BusinessTargetDataInfo> page = businessTargetDataInfoService.findPage(businessTargetDataInfo);
			return page;
		}
		//更具用户过滤
		User user = new User();
		user.setUserCode(userCode);
		businessTargetDataInfo.setUser(user);
		return businessTargetDataInfoService.findPage(businessTargetDataInfo);
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "form")
	public String form(BusinessTargetDataInfo businessTargetDataInfo, Model model) {
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		return "modules/businesstargetdatainfo/businessTargetDataInfoForm";
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "viewAndUpdate")
	public String viewAndUpdate(BusinessTargetDataInfo businessTargetDataInfo, Model model) {
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		return "modules/businesstargetdatainfo/businessTargetDataInfoFormAndUpdate";
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:view")
	@RequestMapping(value = "formNew")
	public String formNew(BusinessTargetDataInfo businessTargetDataInfo,
						  String businessTargetId, String dataItemId ,
						  String userCode,
						  String userTaskId,
						  Model model) {

		BusinessTarget2 businessTarget2 = new BusinessTarget2();
		businessTarget2.setId(businessTargetId);
		BusinessTarget2 target2 = businessTargetService.get(businessTarget2);
		BusinessTargetDataItem dataItem = new BusinessTargetDataItem();
		dataItem.setId(dataItemId);
		businessTargetDataInfo.setBusinessTarget(target2);
		BusinessTargetDataItem targetDataItem = dataItemService.get(dataItem);
		businessTargetDataInfo.setBusinessTargetDataItem(targetDataItem);
		User user = new User();
		user.setUserCode(userCode);
		User userModel = userService.get(user);
		businessTargetDataInfo.setUser(userModel);
		model.addAttribute("businessTargetDataInfo", businessTargetDataInfo);
		model.addAttribute("userTaskId", userTaskId);
		return "modules/businesstargetdatainfo/businessTargetDataInfoFormNew";
	}

	/**
	 * 保存上报的数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessTargetDataInfo businessTargetDataInfo, String userTaskId) {
		businessTargetDataInfo.setIsNewRecord(true);
		businessTargetDataInfoService.save(businessTargetDataInfo,userTaskId);
		return renderResult(Global.TRUE, text("保存上报的数据成功！"));
	}
	
	/**
	 * 停用上报的数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessTargetDataInfo businessTargetDataInfo) {
		businessTargetDataInfo.setStatus(BusinessTargetDataInfo.STATUS_DISABLE);
		businessTargetDataInfoService.updateStatus(businessTargetDataInfo);
		return renderResult(Global.TRUE, text("停用上报的数据成功"));
	}
	
	/**
	 * 启用上报的数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessTargetDataInfo businessTargetDataInfo) {
		businessTargetDataInfo.setStatus(BusinessTargetDataInfo.STATUS_NORMAL);
		businessTargetDataInfoService.updateStatus(businessTargetDataInfo);
		return renderResult(Global.TRUE, text("启用上报的数据成功"));
	}
	
	/**
	 * 删除上报的数据
	 */
	@RequiresPermissions("businesstargetdatainfo:businessTargetDataInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTargetDataInfo businessTargetDataInfo) {
		businessTargetDataInfoService.delete(businessTargetDataInfo);
		return renderResult(Global.TRUE, text("删除上报的数据成功！"));
	}
	
}