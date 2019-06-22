package com.jeesite.modules.businesstargettype.web; /**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */

import java.util.List;
import java.util.Map;

import com.jeesite.modules.businesscheckplan.entity.BusinessCheckPlan;
import com.jeesite.modules.businesscheckplan.service.BusinessCheckPlanService;
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;
import com.jeesite.modules.businesstargettype.service.BusinessTargetTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jeesite.common.config.Global;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;

/**
 * 目标分类Controller
 * @author sanye
 * @version 2019-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstargettype/businessTargetType")
public class BusinessTargetTypeController extends BaseController {

	@Autowired
	private BusinessTargetTypeService businessTargetTypeService;
	@Autowired
	private BusinessCheckPlanService businessCheckPlanService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessTargetType get(String targetTypeCode, boolean isNewRecord) {
		return businessTargetTypeService.get(targetTypeCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:view")
	@RequestMapping(value = {"list/{checkPlanId}", ""})
	public String list(@PathVariable String checkPlanId, BusinessTargetType businessTargetType, Model model) {
		BusinessCheckPlan businessCheckPlan = businessCheckPlanService.get(checkPlanId);
		model.addAttribute("businessTargetType", businessTargetType);
		model.addAttribute("businessCheckPlan",businessCheckPlan);
		return "modules/businesstargettype/businessTargetTypeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<BusinessTargetType> listData(BusinessTargetType businessTargetType) {
		if (StringUtils.isBlank(businessTargetType.getParentCode())) {
			businessTargetType.setParentCode(BusinessTargetType.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(businessTargetType.getTargetTypeName())){
			businessTargetType.setParentCode(null);
		}
		if (StringUtils.isNotBlank(businessTargetType.getCheckPlanId())){
			businessTargetType.setParentCode(null);
		}
		/*
		if (StringUtils.isNotBlank(businessTargetType.getTargetTypeScore())){
			businessTargetType.setParentCode(null);
		}
		if (StringUtils.isNotBlank(businessTargetType.getTargetType())){
			businessTargetType.setParentCode(null);
		}
		if (StringUtils.isNotBlank(businessTargetType.getPoliceType())){
			businessTargetType.setParentCode(null);
		}
		if (StringUtils.isNotBlank(businessTargetType.getDeptLevel())){
			businessTargetType.setParentCode(null);
		}
		if (StringUtils.isNotBlank(businessTargetType.getCreateDate())){
			businessTargetType.setParentCode(null);
		}*/
		if (StringUtils.isNotBlank(businessTargetType.getRemarks())){
			businessTargetType.setParentCode(null);
		}
		List<BusinessTargetType> list = businessTargetTypeService.findList(businessTargetType);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:view")
	@RequestMapping(value = "form")
	public String form(BusinessTargetType businessTargetType, Model model) {
		// 创建并初始化下一个节点信息
		businessTargetType = createNextNode(businessTargetType);
		model.addAttribute("businessTargetType", businessTargetType);
		return "modules/businesstargettype/businessTargetTypeForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public BusinessTargetType createNextNode(BusinessTargetType businessTargetType) {
		if (StringUtils.isNotBlank(businessTargetType.getParentCode())){
			businessTargetType.setParent(businessTargetTypeService.get(businessTargetType.getParentCode()));
		}
		if (businessTargetType.getIsNewRecord()) {
			BusinessTargetType where = new BusinessTargetType();
			where.setParentCode(businessTargetType.getParentCode());
			BusinessTargetType last = businessTargetTypeService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				businessTargetType.setTreeSort(last.getTreeSort() + 30);
				businessTargetType.setTargetTypeCode(IdGen.nextCode(last.getTargetTypeCode()));
			}else if (businessTargetType.getParent() != null){
				businessTargetType.setTargetTypeCode(businessTargetType.getParent().getTargetTypeCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (businessTargetType.getTreeSort() == null){
			businessTargetType.setTreeSort(BusinessTargetType.DEFAULT_TREE_SORT);
		}
		return businessTargetType;
	}

	/**
	 * 保存目标分类
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessTargetType businessTargetType) {
		businessTargetTypeService.save(businessTargetType);
		return renderResult(Global.TRUE, text("保存目标分类成功！"));
	}
	
	/**
	 * 停用目标分类
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessTargetType businessTargetType) {
		BusinessTargetType where = new BusinessTargetType();
		where.setStatus(BusinessTargetType.STATUS_NORMAL);
		where.setParentCodes("," + businessTargetType.getId() + ",");
		long count = businessTargetTypeService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该目标分类包含未停用的子目标分类！"));
		}
		businessTargetType.setStatus(BusinessTargetType.STATUS_DISABLE);
		businessTargetTypeService.updateStatus(businessTargetType);
		return renderResult(Global.TRUE, text("停用目标分类成功"));
	}
	
	/**
	 * 启用目标分类
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessTargetType businessTargetType) {
		businessTargetType.setStatus(BusinessTargetType.STATUS_NORMAL);
		businessTargetTypeService.updateStatus(businessTargetType);
		return renderResult(Global.TRUE, text("启用目标分类成功"));
	}
	
	/**
	 * 删除目标分类
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTargetType businessTargetType) {
		businessTargetTypeService.delete(businessTargetType);
		return renderResult(Global.TRUE, text("删除目标分类成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:view")
	@RequestMapping(value = "treeData/{checkPlanId}")
	@ResponseBody
	public List<Map<String, Object>> treeData(@PathVariable String checkPlanId,String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		BusinessTargetType businessTargetType = new BusinessTargetType();
		businessTargetType.setCheckPlanId(checkPlanId);
		List<BusinessTargetType> list = businessTargetTypeService.findList(businessTargetType);
		for (int i=0; i<list.size(); i++){
			BusinessTargetType e = list.get(i);
			// 过滤非正常的数据
			if (!BusinessTargetType.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getTargetTypeCode(), e.getTargetTypeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(BusinessTargetType businessTargetType){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		businessTargetTypeService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}

	/**
	 * 评测项排序
	 * @param ids
	 * @param sorts
	 * @return
	 */
	@RequestMapping({"updateTreeSort"})
	@ResponseBody
	public String updateTreeSort(Double[] targetTypeScores,String[] ids, Integer[] sorts) {
		for (int i = 0; i < ids.length; i ++) {
			BusinessTargetType a = new BusinessTargetType(ids[i]);
			a.setTreeSort(sorts[i]);
			businessTargetTypeService.updateTreeSort(a);
		}
		for (int i = 0; i < ids.length; i ++) {
			BusinessTargetType a = new BusinessTargetType(ids[i]);
			a.setTargetTypeScore(targetTypeScores[i]);
			businessTargetTypeService.update(a);
		}
//		evaluLibService.fixTreeData();
		return renderResult(Global.TRUE, text("保存测评项排序成功！"));
	}

}