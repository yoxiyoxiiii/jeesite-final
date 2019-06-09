/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettaskmonitor.web;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.businesstarget2.entity.BusinessTarget2;
import com.jeesite.modules.businesstarget2.service.BusinessTarget2Service;
import com.jeesite.modules.businesstargettaskmonitor.entity.BusinessTargetTaskMonitor;
import com.jeesite.modules.businesstargettaskmonitor.service.BusinessTargetTaskMonitorService;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 任务监控Controller
 * @author 任务监控/任务列表
 * @version 2019-06-09
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstargettaskmonitor/businessTargetTaskMonitor")
public class BusinessTargetTaskMonitorController extends BaseController {

	@Autowired
	private BusinessTargetTaskMonitorService businessTargetTaskMonitorService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessTargetTaskMonitor get(String id, boolean isNewRecord) {
		return businessTargetTaskMonitorService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:view")
	@RequestMapping(value = {"treeList"})
	public String treeList(BusinessTarget2 businessTarget2, Model model) {
		model.addAttribute("businessTarget2", businessTarget2);
		return "modules/businesstargettaskmonitor/businessTreeList";
	}



	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessTargetTaskMonitor businessTargetTaskMonitor, Model model) {
		model.addAttribute("businessTargetTaskMonitor", businessTargetTaskMonitor);
		return "modules/businesstargettaskmonitor/businessTargetTaskMonitorList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<BusinessTargetTaskMonitor> listData(BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		if (StringUtils.isBlank(businessTargetTaskMonitor.getParentCode())) {
			businessTargetTaskMonitor.setParentCode(BusinessTargetTaskMonitor.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(businessTargetTaskMonitor.getTargetId())){
			businessTargetTaskMonitor.setParentCode(null);
		}
		if (StringUtils.isNotBlank(businessTargetTaskMonitor.getDepartmentId())){
			businessTargetTaskMonitor.setParentCode(null);
		}
		if (StringUtils.isNotBlank(businessTargetTaskMonitor.getPlanId())){
			businessTargetTaskMonitor.setParentCode(null);
		}
		List<BusinessTargetTaskMonitor> list = businessTargetTaskMonitorService.findList(businessTargetTaskMonitor);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:view")
	@RequestMapping(value = "form")
	public String form(BusinessTargetTaskMonitor businessTargetTaskMonitor, Model model) {
		// 创建并初始化下一个节点信息
		businessTargetTaskMonitor = createNextNode(businessTargetTaskMonitor);
		model.addAttribute("businessTargetTaskMonitor", businessTargetTaskMonitor);
		return "modules/businesstargettaskmonitor/businessTargetTaskMonitorForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public BusinessTargetTaskMonitor createNextNode(BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		if (StringUtils.isNotBlank(businessTargetTaskMonitor.getParentCode())){
			businessTargetTaskMonitor.setParent(businessTargetTaskMonitorService.get(businessTargetTaskMonitor.getParentCode()));
		}
		if (businessTargetTaskMonitor.getIsNewRecord()) {
			BusinessTargetTaskMonitor where = new BusinessTargetTaskMonitor();
			where.setParentCode(businessTargetTaskMonitor.getParentCode());
			BusinessTargetTaskMonitor last = businessTargetTaskMonitorService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				businessTargetTaskMonitor.setTreeSort(last.getTreeSort() + 30);
			}
		}
		// 以下设置表单默认数据
		if (businessTargetTaskMonitor.getTreeSort() == null){
			businessTargetTaskMonitor.setTreeSort(BusinessTargetTaskMonitor.DEFAULT_TREE_SORT);
		}
		return businessTargetTaskMonitor;
	}

	/**
	 * 保存任务监控
	 */
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		businessTargetTaskMonitorService.save(businessTargetTaskMonitor);
		return renderResult(Global.TRUE, text("保存任务监控成功！"));
	}
	
	/**
	 * 删除任务监控
	 */
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		businessTargetTaskMonitorService.delete(businessTargetTaskMonitor);
		return renderResult(Global.TRUE, text("删除任务监控成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@Autowired
	private BusinessTarget2Service businessTarget2Service;
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<BusinessTargetTaskMonitor> list = businessTargetTaskMonitorService.findAll();
		for (int i=0; i<list.size(); i++){
				BusinessTargetTaskMonitor e = list.get(i);
			// 过滤非正常的数据
//			if (!BusinessTargetTaskMonitor.STATUS_NORMAL.equals(e.getStatus())){
//				continue;
//			}
			// 过滤被排除的编码（包括所有子级）
//			if (StringUtils.isNotBlank(excludeCode)){
//				if (e.getId().equals(excludeCode)){
//					continue;
//				}
//				if (e.getParentCodes().contains("," + excludeCode + ",")){
//					continue;
//				}
//			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getTargetId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getTargetId(), e.getTargetContent()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("businesstargettaskmonitor:businessTargetTaskMonitor:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(BusinessTargetTaskMonitor businessTargetTaskMonitor){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		businessTargetTaskMonitorService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}