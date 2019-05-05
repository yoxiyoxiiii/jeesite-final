package com.jeesite.modules.basic.home.web;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.shiro.session.SessionDAO;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.basic.notifyc.entity.NotifyC;
import com.jeesite.modules.basic.notifyc.service.NotifyCService;
import com.jeesite.modules.basic.statistics.entity.Data;
import com.jeesite.modules.basic.statistics.service.DataService;

/**
 * 首页Controller
 * @author longlou.d@foxmail.com
 * @version 2019-4-10
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/home")
public class HomeController extends BaseController {
	
	@Autowired
	private SessionDAO sessionDAO;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private NotifyCService notifyCService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(Model model){
		//在线用户数
		
		int activeUser = sessionDAO.getActiveSessions(true, true).size();
		model.addAttribute("activeUser", activeUser);
		
		try {
			Future<Data> data1 = dataService.statisticsPayByMonth(new Date());
			Future<Data> data2 = dataService.statisticsReByMonth(new Date());
			
			model.addAttribute("totalPay", data1.get().getData());
			model.addAttribute("totalRe", data2.get().getData());
			model.addAttribute("totalBenefits",data2.get().getData()-data1.get().getData());
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("totalPay", "计算出错");
			model.addAttribute("totalRe", "计算出错");
			model.addAttribute("totalBenefits","计算出错");
		}
		
		//通知
		
		NotifyC notify = new NotifyC();
		List<NotifyC> notifyList = notifyCService.findList(notify);
		int j=1;
		for(int i = 0;j<=4;i++) {
			model.addAttribute("notifyTitle"+j,notifyList.get(i).getTitle());
			model.addAttribute("notifyContent"+j, notifyList.get(i).getContent());
			j++;
		}
		
		
		return "/basic/home/index";
	}
	
	@RequestMapping(value="sellerRankingByMonth")
	@ResponseBody
	public Model sellerRankingByMonth(Model model){
		try {
			Future<List<Data>> sellerDataListFuture = dataService.statisticsSellerRankingByMonth(new Date());			
			List<Double> values = new ArrayList<>();
			List<String> names = new ArrayList<>();
			List<Data> sellerDataList = sellerDataListFuture.get();
			for(int i=0;i<sellerDataList.size();i++) {
				values.add(sellerDataList.get(i).getData());
				names.add(sellerDataList.get(i).getDatetime());
			}
			model.addAttribute("value", values);
			model.addAttribute("name",names);
			model.addAttribute("year", new SimpleDateFormat("yyyy年MM月").format(new Date()));
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "统计出错");
		}
		return model;
	}
	
}
