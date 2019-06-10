/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstarget2.web;

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstarget2.entity.BusinessTarget2;
import com.jeesite.modules.businesstarget2.entity.BusinessTargetDataItem2;
import com.jeesite.modules.businesstarget2.service.BusinessTarget2Service;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 指标Controller
 * @author BusinessTarget2
 * @version 2019-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstarget2/businessTarget2")
public class BusinessTarget2Controller extends BaseController {

	@Autowired
	private BusinessTarget2Service businessTarget2Service;

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
	public BusinessTarget2 get(String id, boolean isNewRecord) {
		return businessTarget2Service.get(id, isNewRecord);
	}


	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstarget2:businessTarget2:view")
	@RequestMapping(value = {"treeList", ""})
	public String treeList(BusinessTarget2 businessTarget2, Model model) {
		model.addAttribute("businessTarget2", businessTarget2);
		return "modules/businesstarget2/businessTreeList";
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstarget2:businessTarget2:view")
	@RequestMapping(value = {"list"})
	public String list(BusinessTarget2 businessTarget2, Model model) {
		model.addAttribute("businessTarget2", businessTarget2);
		return "modules/businesstarget2/businessTarget2List";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstarget2:businessTarget2:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessTarget2> listData(BusinessTarget2 businessTarget2, HttpServletRequest request, HttpServletResponse response) {
		businessTarget2.setPage(new Page<>(request, response));
		Page<BusinessTarget2> page = businessTarget2Service.findPage(businessTarget2);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstarget2:businessTarget2:view")
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

		return "modules/businesstarget2/businessTarget2Form";
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
	@RequiresPermissions("businesstarget2:businessTarget2:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessTarget2 businessTarget2) {
		businessTarget2Service.save(businessTarget2);
		return renderResult(Global.TRUE, text("保存指标成功！"));
	}
	
	/**
	 * 删除指标
	 */
	@RequiresPermissions("businesstarget2:businessTarget2:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTarget2 businessTarget2) {
		businessTarget2Service.delete(businessTarget2);
		return renderResult(Global.TRUE, text("删除指标成功！"));
	}

	@RequestMapping({"listSelect"})
	public String listSelect(BusinessTarget businessTarget, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("businessTarget", businessTarget);
		return "modules/businesstarget2/listSelect";
	}

	/**
	 * 跳转到公式添加页
	 * @return
	 */
	@RequiresPermissions("businesstarget2:businessTarget2:edit")
	@RequestMapping({"add"})
	public String addList(BusinessTarget2 businessTarget2, Model model) {
		/**
		 * 符号列表
		 */
		StringBuffer sb = new StringBuffer();
		sb.append("加（+）").append(" ");
		sb.append("减（-）").append(" ");
		sb.append("乘（*）").append(" ");
		sb.append("除（/）").append(" \n");
		sb.append("左括号: (").append(" \n");
		sb.append("右括号:  )").append(" \n");
		sb.append("大于等于: >=").append(" \n");
		sb.append("小于等于: <=").append(" \n");
		sb.append("小于: <").append(" \n");
		sb.append("大于: >").append(" \n");
		sb.append("分隔符: @").append("\n ");
		sb.append("AND").append(" ");
		sb.append("OR").append(" \n");
		sb.append("if").append("&nbsp").append("else");

		BusinessTarget2 target2 = businessTarget2Service.get(businessTarget2);
		List<BusinessTargetDataItem2> businessTargetDataItem2List = target2.getBusinessTargetDataItem2List();
		StringBuffer dataItemList = new StringBuffer();
		for (int i=0;i<businessTargetDataItem2List.size(); i++) {
			BusinessTargetDataItem2 businessTargetDataItem2 = businessTargetDataItem2List.get(i);

			dataItemList.append(businessTargetDataItem2.getItemName()).append("&nbsp");
			if (i%5==0) {
				dataItemList.append("\n");
			}
		}


		businessTarget2.setSymbolList(sb.toString());
		businessTarget2.setDataItemList(dataItemList.toString());
		model.addAttribute("businessTarget2",businessTarget2);
		return "modules/businesstarget2/addList";
	}
	
}