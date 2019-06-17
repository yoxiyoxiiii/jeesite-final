/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.biz.entity.PrizeLib;
import com.jeesite.modules.biz.entity.PrizeType;
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
import com.jeesite.modules.biz.entity.Prize;
import com.jeesite.modules.biz.service.PrizeService;

import java.util.List;

/**
 * 奖扣记录Controller
 * @author sanye
 * @version 2019-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/prize")
public class PrizeController extends BaseController {

	@Autowired
	private PrizeService prizeService;
	@Autowired
	private OfficeService officeService;

	@RequiresPermissions("biz:prize:view")
	@RequestMapping(value = { "index", "" })
	public String index() {
		//CmsUtils.getSite(Site.MAIN_SITE_CODE); // 用于初始化站点数据
		return "modules/biz/prizeIndex";
	}

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Prize get(String id, boolean isNewRecord) {
		return prizeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("biz:prize:view")
	@RequestMapping(value = {"list", ""})
	public String list(Prize prize, Model model) {
		model.addAttribute("prize", prize);
		return "modules/biz/prizeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("biz:prize:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Prize> listData(Prize prize, HttpServletRequest request, HttpServletResponse response) {
//		String id = request.getParameter("prizeTypeId");
//		if( id.length() > 1 ) {
//			PrizeType pt = new PrizeType();
//			pt.setId(id);
//			PrizeLib pb = new PrizeLib();
//			pb.setPrizeType(pt);
//			prize.setPrizeLib(pb);
//		}
		prize.setPage(new Page<>(request, response));
		Page<Prize> page = prizeService.findPage(prize);
//		List<Prize> list = page.getList();
//		for(int i=0; i< list.size(); i++) {
//			Office office = list.get(i).getOfficeJoin();
//			String officeNames = "";
//			String[] codes = office.getOfficeCode().split(",");
//			for(String code:codes){
//				Office tOffice = new Office();
//				tOffice.setOfficeCode(code);
//				tOffice = officeService.get(tOffice);
//				if(tOffice != null) {
//					officeNames += tOffice.getOfficeName() + ",";
//				}
//			}
//			if(officeNames.length()> 2){
//				officeNames = officeNames.substring(0,officeNames.length()-1);
//			}
//			//组合名称显示
//			office.setOfficeCode(officeNames);
//			Prize temp = list.get(i);
//			temp.setOfficeJoin(office);
//			list.set(i, temp);
//		}
//		page.setList(list);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("biz:prize:view")
	@RequestMapping(value = "form")
	public String form(Prize prize, Model model) {
		model.addAttribute("prize", prize);
		return "modules/biz/prizeForm";
	}

	/**
	 * 保存奖扣记录
	 */
	@RequiresPermissions("biz:prize:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Prize prize) {
//	    Office office = new Office();
//	    office.setOfficeCode("SY0001");
//		prize.setOfficeJoin(office);
		//只要修改过,就重走流程
		prize.setStatus( prize.STATUS_DRAFT);
		prizeService.save(prize);
		prizeService.updateStatus(prize);
		return renderResult(Global.TRUE, text("保存奖扣记录成功！"));
	}
	
	/**
	 * 停用奖扣记录
	 */
	@RequiresPermissions("biz:prize:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Prize prize) {
		prize.setStatus(Prize.STATUS_DISABLE);
		prizeService.updateStatus(prize);
		return renderResult(Global.TRUE, text("停用奖扣记录成功"));
	}
	
	/**
	 * 启用奖扣记录
	 */
	@RequiresPermissions("biz:prize:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Prize prize) {
		prize.setStatus(Prize.STATUS_NORMAL);
		prizeService.updateStatus(prize);
		return renderResult(Global.TRUE, text("启用奖扣记录成功"));
	}
	
	/**
	 * 删除奖扣记录
	 */
	@RequiresPermissions("biz:prize:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Prize prize) {
		prizeService.delete(prize);
		return renderResult(Global.TRUE, text("删除奖扣记录成功！"));
	}

	/**
	 * 审批奖扣类型
	 */
	@RequiresPermissions("biz:prize:audit")
	@RequestMapping(value = "audit")
	@ResponseBody
	public String audit(Prize prize, String status) {
		prize.setStatus(status);
		prizeService.updateStatus(prize);
		return renderResult(Global.TRUE, text("审批操作成功"));
	}
    /**
     * 停用奖扣记录
     */
   /* @RequiresPermissions("biz:prize:edit")
    @RequestMapping(value = "officejoin")
    @ResponseBody
    public String showJoin(Prize prize) {
        Office officeCode = prize.getOfficeJoin();
        String codes = officeCode.getOfficeCode();
        String officeName = "";
        for (String retval: codes.split(",")){
            Office officeTemp =  officeService.get(retval);
            officeName += "<li>" + officeTemp.getOfficeCode() + "</li>";
        }
        return renderResult(Global.TRUE, text("停用奖扣记录成功"));
    }*/
}