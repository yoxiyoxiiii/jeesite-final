package com.jeesite.modules.basic.statistics.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.statistics.dao.DataDao;
import com.jeesite.modules.basic.statistics.entity.Data;
import com.jeesite.modules.basic.statistics.entity.OutProduct;

/**
 * 数据统计Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class DataService extends CrudService<DataDao, Data> {
	@Autowired
	private DataDao dataDao;
	
	/**
	 * 根据月份统计付款额
	 * @param date
	 */
	@Async
	public Future<Data> statisticsPayByMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return new AsyncResult<Data>(dataDao.statisticsPayByMonth(sdf.format(date)));
	}
	
	/**
	 * 根据月份统计收款额
	 * @param date
	 */
	@Async
	public Future<Data> statisticsReByMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return new AsyncResult<Data>(dataDao.statisticsReByMonth(sdf.format(date)));
	}
	
	/**
	 * 根据年份统计销售额
	 * @param year
	 */
	public Future<List<Data>> statisticsSalesByYear(String year){
		return new AsyncResult<List<Data>>(dataDao.statisticsSalesByYear(year));
	}
	
	/**
	 * 根据年份统计成本
	 * @param year
	 */
	public Future<List<Data>> statisticsCostByYear(String year){
		return new AsyncResult<List<Data>>(dataDao.statisticsCostByYear(year));
	}
	
	/**
	 * 根据年份统计收益
	 * @param year
	 */
	public Future<List<Data>> statisticsBenefitsByYear(String year){
		return new AsyncResult<List<Data>>(dataDao.statisticsBenefitsByYear(year));
	}
	
	/**
	 * 根据月份统计各销售人员销售额
	 * @return
	 */
	public Future<List<Data>> statisticsSellerRankingByMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return new AsyncResult<List<Data>>(dataDao.statisticsSellerRankingByMonth(sdf.format(date)));
	}
	/**
	 * 相关系数计算
	 */
	@Async("taskExecutor")
	public Future<Double> correlationCoefficient(List<Double> x,List<Double> y){
		double Ex=0,Ey=0,Exy=0,Cov=0,Ey2=0,Ex2=0,r=0;
		for(int i=0;i<x.size()&&i<y.size();i++){
			Exy+=x.get(i)*y.get(i);
			Ex2+=x.get(i)*x.get(i);
			Ey2+=y.get(i)*y.get(i);
		}
		
		Ex=average(x);
		Ey=average(y);
		Exy=Exy/x.size();
		Ex2=Ex2/x.size();
		Ey2=Ey2/y.size();
		Cov=Exy-Ex*Ey;
		
		r=Cov/(Math.sqrt(Ex2-Ex*Ex)*Math.sqrt(Ey2-Ey*Ey));

		return new AsyncResult<Double>(r);
	}
	
	/**
	 * 判定系数(拟合度)计算
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Async("taskExecutor")
	public Future<Double> determinationCoefficient(List<Double> x,List<Double> y,double a, double b){
		double SSE=0,SST=0;
		double averageY = average(y);
		
		//计算SSE、SST
		for(int i=0;i<x.size()&&i<y.size();i++){
			SSE+=Math.pow(y.get(i)-(a*x.get(i)+b),2);
			SST+=Math.pow(y.get(i)-averageY,2);
		}
		
		//避免除以零
		if(SST==0)SST=1;
		
		return new AsyncResult<Double>(1-SSE/SST);
	}
	
	/**
	 * 计算参数a
	 */
	@Async("taskExecutor")
	public Future<Double> calculateA(List<Double> x,List<Double> y){
		double upper=0,lower=0;
		double averageX=average(x);
		double averageY=average(y);
		
		for(int i=0;i<x.size()&&i<y.size();i++){
			upper+=(x.get(i)-averageX)*(y.get(i)-averageY);
			lower+=Math.pow(x.get(i)-averageX,2);
		}
		
		return new AsyncResult<Double>(upper/lower);
	}
	
	/**
	 * 计算参数b
	 */
	@Async("taskExecutor")
	public Future<Double> calculateB(List<Double> x,List<Double> y,double a){
		double b = average(y)-a*average(x);
		return new AsyncResult<Double>(b);
	}
	
	/**
	 * 计算平均数
	 */
	public double average(List<Double> x){
		double averageX=0;
		for(int i=0;i<x.size();i++){
			averageX+=x.get(i);
		}
		return averageX/x.size();
	}
	
	/**
	 * 计算回归方程坐标
	 */
	@Async("taskExecutor")
	public Future<List<List<Double>>> regressionPoint(List<Double> x, double a, double b){
		List<List<Double>> point = new ArrayList<List<Double>>();
		for(int i =0;i<x.size();i++){
			List<Double> temp = new ArrayList<Double>();
			temp.add(x.get(i));
			temp.add(a*x.get(i)+b);
			point.add(temp);
		}
		return new AsyncResult<List<List<Double>>>(point);
	}
	
	/**
	 * 按年份统计每月出货表
	 * @param year
	 * @return
	 */
	public Future<List<OutProduct>> statisticsProudctsByYear(String year){
		return new AsyncResult<List<OutProduct>>(dataDao.statisticsProudctsByYear(year));
	}
	
	/**
	 * 预测下一个月
	 * @param year
	 * @param month
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public Future<Data> predictedNextMonth(Date date) throws InterruptedException, ExecutionException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		if(date.getMonth()==0){
			//若是今年一月，则返回上年本月
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, -1);
			return new AsyncResult<Data>(dataDao.statisticsReByMonth(sdf.format(calendar)));
		}
		
		//统计上一年全年销售总额
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -1);
		double preYearTotal = totalAmountByYear(calendar.getTime());
		
		//统计上年一月
		calendar.set(Calendar.MONTH, 0);
		Future<Data> preYearPreMonthData = statisticsReByMonth(calendar.getTime());
		
		//统计上年本月
		calendar.set(Calendar.MONTH, date.getMonth());
		Future<Data> preYearThisMonthData = statisticsReByMonth(calendar.getTime());
		
		//统计今年一月
		calendar.add(Calendar.YEAR, 1);
		calendar.set(Calendar.MONTH, 0);
		Future<Data> thisYearPreMonthData = statisticsReByMonth(calendar.getTime());
		
		//上年本月与上年全年的比值
		double pYtMonth_D_pYTotal_rate = preYearThisMonthData.get().getData()/preYearTotal;
		
		//今年预测总额
		double preYearPreMonth_D_preYearTotal_rate =  preYearPreMonthData.get().getData()/preYearTotal;
		double thisYear_expectTotal = thisYearPreMonthData.get().getData();
		thisYear_expectTotal = thisYear_expectTotal/preYearPreMonth_D_preYearTotal_rate;
		
		//填充数据
		Data data = new Data();
		sdf = new SimpleDateFormat("MM");
		data.setData(pYtMonth_D_pYTotal_rate*thisYear_expectTotal);
		data.setDatetime("预测"+sdf.format(date));
		
		return new AsyncResult<Data>(data);
	}
	
	/**
	 * 全年销售额
	 */
	private double totalAmountByYear(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Data> dataList = dataDao.statisticsSalesByYear(sdf.format(date));
		double total=0;
		for(Data d : dataList){
			total+=d.getData();
		}
		return total;
	}
	
}
