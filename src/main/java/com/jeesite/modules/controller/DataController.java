package com.jeesite.modules.controller;

import com.jeesite.common.web.BaseController;
import com.jeesite.modules.service.PrinterService;
import com.jeesite.modules.entity.Data;
import com.jeesite.modules.entity.OutProduct;
import com.jeesite.modules.service.DataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 数据统计Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/Data/Data")
public class DataController extends BaseController{

	@Autowired
	private DataService dataService;
	@Autowired
	private PrinterService printerService;
	
	/**
	 * 成本收益页面
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"costAndBenefitsChart", ""})
	public String costAndBenefitsChart(){
		return "statistics/costAndBenefits/costAndBenefitsChart";
	}
	
	/**
	 * 出货统计页面
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"outProductsChart", ""})
	public String outProductsChart(){
		return "statistics/outProducts/outProductsChart";
	}
	
	/**
	 * 销售统计数据
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"salesAmountByYearData", ""})
	@ResponseBody
	public Model salesAmountByYearData(Model model, String date){
		//按年份统计
		String year = date.split("-")[0];
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), 1, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(calendar.getTime());
		Future<List<Data>> dataList =  dataService.statisticsSalesByYear(dateString);
		
		List<String> name = new ArrayList<String>();
		List<Double> value = new ArrayList<Double>();
		
		try{
			for(Data d : dataList.get()){
				name.add(d.getDatetime()+"月");
				value.add(d.getData());
			}
			
			//预测月
			Future<Data> predictedDate = null;
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int rest = 12-dataList.get().size();
			if(rest == 12) {
				calendar = new GregorianCalendar(Integer.parseInt(year), 1, 1);
				predictedDate = dataService.predictedNextMonth(calendar.getTime());
			}else {
				name.remove(name.size()-1);
				value.remove(value.size()-1);
				for(int i=0;i<rest;i++) {
					cal.add(Calendar.MONTH, i);
					predictedDate = dataService.predictedNextMonth(cal.getTime());
					name.add(predictedDate.get().getDatetime()+"月");
					value.add(Double.parseDouble(String.format("%.2f", predictedDate.get().getData())));
					cal.add(Calendar.MONTH, -i);
				}
			}
			
			model.addAttribute("year", year);
			model.addAttribute("name", name);
			model.addAttribute("value", value);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "数据出错");
		}
		
		return model;
	}
	
	/**
	 * 成本统计
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"costByYearData", ""})
	@ResponseBody
	public Model costByYearData(Model model, String date){
		//按年份统计
		String year = date.split("-")[0];
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), 1, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String dateString = formatter.format(calendar.getTime());
		List<String> name = new ArrayList<String>();
		List<Double> value = new ArrayList<Double>();
		
		try{
			//获取成本
			Future<List<Data>> costFuture =  dataService.statisticsCostByYear(dateString);
			for(Data d : costFuture.get()){
				name.add(d.getDatetime()+"月");
				value.add(d.getData());
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "数据出错");
			return model;
		}
		
		model.addAttribute("year", year);
		model.addAttribute("name", name);
		model.addAttribute("value", value);
		return model;
	}
	
	/**
	 * 销售额与成本统计数据
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"costAndBenefitsByYearData", ""})
	@ResponseBody
	public Model costAndBenefitsByYearData(Model model, String date){
		//按年份统计
		String year = date.split("-")[0];
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), 1, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String dateString = formatter.format(calendar.getTime());
		
		List<Data> costList;
		List<Data> benefitList;
		List<Double> costDataList;
		List<Double> benefitDataList;
		List<List<Double>> costAndBenefits;
		try{
			//获取成本与收益值
			Future<List<Data>> costFuture =  dataService.statisticsCostByYear(dateString);
			Future<List<Data>> beneFuture =  dataService.statisticsBenefitsByYear(dateString);
			costList = costFuture.get();
			benefitList = beneFuture.get();
			
			//获取实际值
			costDataList = new ArrayList<Double>();
			benefitDataList = new ArrayList<Double>();
			
			//封装数据
			costAndBenefits = new ArrayList<List<Double>>();
			for(int i = 0;i<costList.size()&&i<benefitList.size();i++){
				List<Double> temp = new ArrayList<Double>();
				temp.add(costList.get(i).getData());
				temp.add(benefitList.get(i).getData());
				temp.add(Double.parseDouble(costList.get(i).getDatetime()));
				costAndBenefits.add(temp);
				
				costDataList.add(costList.get(i).getData());
				benefitDataList.add(benefitList.get(i).getData());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "数据出错");
			return model;
		}

		//拟合度
		Future<Double> determinationCoefficient;
		//相关系数
		Future<Double> correlationCoefficient;
		//回归方程参数a
		Future<Double> regressionA;
		//回归方程参数b
		Future<Double> regressionB;
		//方程坐标
		Future<List<List<Double>>> points;
		try {
			regressionA=dataService.calculateA(costDataList, benefitDataList);
			regressionB=dataService.calculateB(costDataList, benefitDataList,regressionA.get());
			correlationCoefficient = dataService.correlationCoefficient(costDataList, benefitDataList);
			determinationCoefficient = dataService.determinationCoefficient(costDataList, benefitDataList,regressionA.get(),regressionB.get());
			points = dataService.regressionPoint(costDataList, regressionA.get(), regressionB.get());
			
			model.addAttribute("determinationCoefficient", String.format("%.3f",determinationCoefficient.get()));
			model.addAttribute("correlationCoefficient", String.format("%.3f",correlationCoefficient.get()));
			model.addAttribute("regressionA", String.format("%.3f",regressionA.get()));
			model.addAttribute("regressionB", String.format("%.3f",regressionB.get()));
			model.addAttribute("points", points.get());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "计算出错");
			return model;
		}
		
		model.addAttribute("year", year);
		model.addAttribute("costAndBenefits", costAndBenefits);
		return model;
	}
	
	/**
	 * 按年份统计每月出货表
	 * @param model
	 * @param date
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"statisticsProudctsByYear", ""})
	@ResponseBody
	public List<OutProduct> statisticsProudctsByYear(String date) throws InterruptedException, ExecutionException{
		//按年份统计
		String year = date.split("-")[0];
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), 1, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(calendar.getTime());
		Future<List<OutProduct>> dataList =  dataService.statisticsProudctsByYear(dateString);
		
		return dataList.get();
	}
//
//	/**
//	 * 导出出货表
//	 * @param recordDate
//	 * @return
//	 */
//	@RequiresPermissions("Data:Data:view")
//	@RequestMapping(value = "print")
//	@ResponseBody
//	public String printOutProducts(@RequestParam("recordDate")String recordDate,HttpServletResponse response) {
//		if(recordDate==null||recordDate.isEmpty())
//			return renderResult(Global.FALSE, text("请选择年份！"));
//		try {
//			printerService.printOutProducts(statisticsProudctsByYear(recordDate),recordDate.split("-")[0], response);
//			return renderResult(Global.TRUE, text("打印完成！"));
//		}catch (Exception e) {
//			e.printStackTrace();
//			return renderResult(Global.FALSE, text("打印出错！"));
//		}
//	}
}
