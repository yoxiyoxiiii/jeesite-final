package com.jeesite.modules.basic.printer.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 获取模板、初始化工作簿
 * @author longlou.d@foxmail.com
 * @version 2019-4-12
 */
public class BasicExcel {
	private String srcXlsPath = "";		//模板路径
	
	/**
	 * 初始化
	 * @param templateName 模板名称
	 */
	public Workbook initWork(String templateName) throws IOException{
		//模板路径
		this.srcXlsPath = "src/main/resources/static/template/"+templateName+".xlsx";
		//导入模板
		InputStream is = new FileInputStream(new File(srcXlsPath));
		Workbook wb = new XSSFWorkbook(is);
		is.close();
		return wb;
	}
}
