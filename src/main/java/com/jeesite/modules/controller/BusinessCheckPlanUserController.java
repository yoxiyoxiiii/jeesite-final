/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.controller;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.entity.BusinessCheckPlan;
import com.jeesite.modules.entity.BusinessCheckPlanUser;
import com.jeesite.modules.service.BusinessCheckPlanUserService;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.service.OfficeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 考核名单Controller
 * @author 考核名单
 * @version 2019-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/businesscheckplanuser/businessCheckPlanUser")
public class BusinessCheckPlanUserController extends BaseController {

	@Autowired
	private BusinessCheckPlanUserService businessCheckPlanUserService;


	//部门信息
	@Autowired
	private OfficeService officeService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessCheckPlanUser get(String id, boolean isNewRecord) {
		return businessCheckPlanUserService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessCheckPlanUser businessCheckPlanUser, Model model) {
		model.addAttribute("businessCheckPlanUser", businessCheckPlanUser);
		return "modules/businesscheckplanuser/businessCheckPlanUserList";
	}
	/**
	 * 监控列表
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:view")
	@RequestMapping(value = {"listMonitor"})
	public String listMonitor(BusinessCheckPlanUser businessCheckPlanUser, Model model) {
		model.addAttribute("businessCheckPlanUser", businessCheckPlanUser);
		return "modules/businesscheckplanuser/businessCheckPlanUserListMonitor";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessCheckPlanUser> listData(BusinessCheckPlanUser businessCheckPlanUser, HttpServletRequest request, HttpServletResponse response) {
		businessCheckPlanUser.setPage(new Page<>(request, response));
		Page<BusinessCheckPlanUser> page = businessCheckPlanUserService.findPage(businessCheckPlanUser);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:view")
	@RequestMapping(value = "form")
	public String form(BusinessCheckPlanUser businessCheckPlanUser,Office office,Model model) {
	    //新建名单时名单默认名为计划名加+"考核名单"
        if(businessCheckPlanUser.getIsNewRecord() && businessCheckPlanUser.getBusinessCheckPlan() != null){
            businessCheckPlanUser.setPlanUserName( businessCheckPlanUser.getBusinessCheckPlan().getPlanName() + "名单");
        }
		model.addAttribute("businessCheckPlanUser", businessCheckPlanUser);
		// 创建并初始化下一个节点信息
		office = createNextNode(office);
		if (StringUtils.isNotBlank(office.getParentCode())) {
			office.setParent(officeService.get(office.getParentCode()));
		}
		if (office.getIsNewRecord()) {
			office.setTreeSort(30);
			Office where = new Office();
			where.setParentCode(office.getParentCode());
			Office last = officeService.getLastByParentCode(where);
			if (last != null) {
				office.setTreeSort(last.getTreeSort() + 30);
				office.setViewCode(IdGen.nextCode(last.getViewCode()));
			} else if (office.getParent() != null) {
				office.setViewCode(office.getParent().getViewCode() + "001");
			}
		}
		model.addAttribute("office", office);
		return "modules/businesscheckplanuser/businessCheckPlanUserForm";
	}

	public Office createNextNode(Office office) {
		if (StringUtils.isNotBlank(office.getParentCode())) {
			office.setParent(officeService.get(office.getParentCode()));
		}
		if (office.getIsNewRecord()) {
			Office where = new Office();
			where.setParentCode(office.getParentCode());
			Office last = officeService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				office.setTreeSort(last.getTreeSort() + 30);
				office.setViewCode(IdGen.nextCode(last.getViewCode()));
			}else if(office.getParent() != null){
				office.setViewCode(office.getParent().getViewCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (office.getTreeSort() == null){
			office.setTreeSort(Office.DEFAULT_TREE_SORT);
		}
		return office;
	}

	/**
	 * 保存考核名单
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessCheckPlanUser businessCheckPlanUser) {
		if (org.springframework.util.StringUtils.isEmpty(businessCheckPlanUser.getBusinessCheckPlan().getId())){return renderResult(Global.FALSE, text("考核计划不能为空!！")); }
		boolean isNewRecord = businessCheckPlanUser.getIsNewRecord();
		if (isNewRecord) {businessCheckPlanUser.setPlanUserStatus("1");}//初始状态设置未可用
		if ("1".equals(businessCheckPlanUser.getPlanUserStatus())){
			String officeCode = businessCheckPlanUser.getOffice().getOfficeCode();
			BusinessCheckPlan businessCheckPlan = businessCheckPlanUser.getBusinessCheckPlan();
			if (org.springframework.util.StringUtils.isEmpty(officeCode)) {return renderResult(Global.FALSE, text("考核部门不能为空!"));}
			if (org.springframework.util.StringUtils.isEmpty(businessCheckPlan.getId())) {return renderResult(Global.FALSE, text("考核计划不能为空!"));}
			businessCheckPlanUserService.save(businessCheckPlanUser);
			return renderResult(Global.TRUE, text("保存考核名单成功！"));
		} else {
			return renderResult(Global.FALSE, text("不可编辑!！"));
		}

	}

	/**
	 * 保存考核名单
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:edit")
	@GetMapping(value = "view")
	public String view(String id, Model model) {
		BusinessCheckPlanUser planUser = businessCheckPlanUserService.get(id);
		String departmentId = planUser.getOffice().getOfficeCode();
		String[] split = departmentId.split(",");
		List<Office> offices = new ArrayList<>();
		for (String item: split) {
			Office office = officeService.get(item);
			Integer treeLevel = office.getTreeLevel();
			if (treeLevel != 0) {
				offices.add(office);
			}
		}
		model.addAttribute("offices",offices);
		model.addAttribute("businessCheckPlanUser",planUser);
		return "modules/businesscheckplanuser/view";
	}

	/**
	 * 删除考核名单
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessCheckPlanUser businessCheckPlanUser) {
		businessCheckPlanUserService.delete(businessCheckPlanUser);
		return renderResult(Global.TRUE, text("删除考核名单成功！"));
	}

	/**
	 * 删除考核名单
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:edit")
	@RequestMapping(value = "del")
	public String dele(String officeCode, String id, Model model) {
		BusinessCheckPlanUser businessCheckPlanUser = businessCheckPlanUserService.get(id);
		String departmentId = businessCheckPlanUser.getOffice().getOfficeCode();
		String[] split = departmentId.split(",");
		List<Office> offices = new ArrayList<>();
		for (String item: split) {
			Office office = officeService.get(item);
			Integer treeLevel = office.getTreeLevel();
			if (treeLevel != 0) {
				offices.add(office);
			}
		}
		List<String> codes = new ArrayList<>();
		for (String s : split) {
			codes.add(s);
		}
		 codes.remove(officeCode);
		StringBuffer sb = new StringBuffer();
		for (String item : codes) {
			sb.append(item).append(",");
		}
		businessCheckPlanUser.getOffice().setOfficeCode(sb.toString());
		businessCheckPlanUserService.update(businessCheckPlanUser);
		model.addAttribute("offices",offices);
		model.addAttribute("businessCheckPlanUser",businessCheckPlanUser);
		return "modules/businesscheckplanuser/view";
	}

	@RequestMapping({"listSelect"})
	public String listSelect(BusinessCheckPlanUser businessCheckPlanUser, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("businessCheckPlanUser", businessCheckPlanUser);
		return "modules/businesscheckplanuser/listSelect";
	}
}