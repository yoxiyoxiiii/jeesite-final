/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.service.OfficeService;
import com.jeesite.modules.targets.entity.Targets;
import com.jeesite.modules.targets.service.TargetsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * targetsController
 * @author target
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/targets/targets")
public class TargetsController extends BaseController {

	@Autowired
	private TargetsService targetsService;

	//机构
	@Autowired
	private OfficeService officeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Targets get(String id, boolean isNewRecord) {
		return targetsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("targets:targets:view")
	@RequestMapping(value = {"list", ""})
	public String list(Targets targets, Model model) {
		model.addAttribute("targets", targets);
		return "modules/targets/targetsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("targets:targets:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Targets> listData(Targets targets, HttpServletRequest request, HttpServletResponse response) {
		targets.setPage(new Page<>(request, response));
		Page<Targets> page = targetsService.findPage(targets);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("targets:targets:view")
	@RequestMapping(value = "form")
	public String form(Targets targets, Office office, Model model) {
		model.addAttribute("targets", targets);

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

		return "modules/targets/targetsForm";
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
	 * 保存targets
	 */
	@RequiresPermissions("targets:targets:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Targets targets) {
		targetsService.save(targets);
		return renderResult(Global.TRUE, text("保存targets成功！"));
	}
	
	/**
	 * 删除targets
	 */
	@RequiresPermissions("targets:targets:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Targets targets) {
		targetsService.delete(targets);
		return renderResult(Global.TRUE, text("删除targets成功！"));
	}


	/**
	 * 树形结构展示
	 * @param excludeCode
	 * @param isShowCode
	 * @return
	 */
	@RequiresPermissions("targets:targets:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		return new ArrayList<>();
	}
}