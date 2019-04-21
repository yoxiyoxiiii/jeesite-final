/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstarget.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.idgen.IdGen;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;
import com.jeesite.modules.businesstargettype.service.BusinessTargetTypeService;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.service.OfficeService;
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
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstarget.service.BusinessTargetService;

import java.util.List;

/**
 * 指标Controller
 * @author BusinessTarget
 * @version 2019-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstarget/businessTarget")
public class BusinessTargetController extends BaseController {

	@Autowired
	private BusinessTargetService businessTargetService;

	//部门信息
	@Autowired
	private OfficeService officeService;

	//目标分类信息
	@Autowired
	private BusinessTargetTypeService targetTypeService;


	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessTarget get(String id, boolean isNewRecord) {
		return businessTargetService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstarget:businessTarget:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessTarget businessTarget, Model model) {
		model.addAttribute("businessTarget", businessTarget);
		return "modules/businesstarget/businessTargetList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstarget:businessTarget:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessTarget> listData(BusinessTarget businessTarget, HttpServletRequest request, HttpServletResponse response) {
		businessTarget.setPage(new Page<>(request, response));
		Page<BusinessTarget> page = businessTargetService.findPage(businessTarget);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstarget:businessTarget:view")
	@RequestMapping(value = "form")
	public String form(BusinessTarget businessTarget, Office office, BusinessTargetType businessTargetType, Model model) {
		model.addAttribute("businessTarget", businessTarget);
        List<BusinessTargetType> targetTypeList = targetTypeService.findList(businessTargetType);
        if (targetTypeList.size() == 0) {
            targetTypeList = targetTypeService.findList();
        }
        model.addAttribute("targetTypeList", targetTypeList);

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

		return "modules/businesstarget/businessTargetForm";
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
	 * 保存指标
	 */
	@RequiresPermissions("businesstarget:businessTarget:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessTarget businessTarget) {
		businessTargetService.save(businessTarget);
		return renderResult(Global.TRUE, text("保存指标成功！"));
	}
	
	/**
	 * 删除指标
	 */
	@RequiresPermissions("businesstarget:businessTarget:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTarget businessTarget) {
		businessTargetService.delete(businessTarget);
		return renderResult(Global.TRUE, text("删除指标成功！"));
	}
	
}