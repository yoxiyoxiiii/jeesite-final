package com.jeesite.modules.basic.printer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.printer.dao.PrinterDao;
import com.jeesite.modules.basic.printer.entity.Printer;
import com.jeesite.modules.sys.entity.DictData;
import com.jeesite.modules.sys.service.DictDataService;

/**
 * 字典
 * @author longlou.d@foxmail.com
 * @version 2019-4-12
 */
@Service
public class Dictionaries extends CrudService<PrinterDao, Printer> {
	
	@Autowired
	private DictDataService dictDataService;
	
	private Map<String, String> priceItem = new HashMap<String, String>();		//价格条款
	private Map<String, String> transport = new HashMap<String, String>();		//运输方式
	private Map<String, String> payment = new HashMap<String, String>();		//付款方式
	private Map<String, String> packageUnit = new HashMap<String, String>();	//包装单位
	private Map<String, String> singlePackage = new HashMap<String, String>();	//单件包装方式

	public void init(){
		DictData d = new DictData("");
		List<DictData> allDictList = dictDataService.findList(d);
				
		for(DictData dictData : allDictList){
			if(dictData.getDictType().equals("trading_price_term"))
				priceItem.put(dictData.getDictValue(), dictData.getDictLabel());
			if(dictData.getDictType().equals("trading_transport_way"))
				transport.put(dictData.getDictValue(), dictData.getDictLabel());
			if(dictData.getDictType().equals("trading_paymentterrms"))
				payment.put(dictData.getDictValue(), dictData.getDictLabel());
			if(dictData.getDictType().equals("trading_package_unit"))
				packageUnit.put(dictData.getDictValue(), dictData.getDictLabel());
			if(dictData.getDictType().equals("trading_single_package"))
				singlePackage.put(dictData.getDictValue(), dictData.getDictLabel());
		}
	}
	
	public String getPriceItem(String key){
		return priceItem.get(key);
	}
	
	public String getTransportWay(String key){
		return transport.get(key);
	}
	
	public String getPayment(String key){
		return payment.get(key);
	}
	
	public String getPackageUnit(String key){
		return packageUnit.get(key);
	}
	
	public String getSinglePackage(String key){
		return singlePackage.get(key);
	}
	
}
