package com.report.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import com.report.constant.ReportType;
import com.report.criteria.ReportCriteria;
import com.report.util.Utils;

/**
 * This class to generate reports that implements ReportGenerator class.
 */
public class GenerateReport {
	private static final Logger LOGGER = LogManager.getLogger(GenerateReport.class.getName());

	/**
	 * This method to write report that implements ReportGenerator class.
	 * @param		pReportType	This is the parameter to specify type of report.
	 * @return	a path of report file
	 */
	public String writeReport(ReportType pReportType){
		String templatePath = null;
		String outputPath = null;
		Workbook book = null;
		try {
			ReportGenerator reportGenerator = getReport(pReportType);
			ReportCriteria reportCriteria = getReportCriteria(pReportType);
			
			if(reportGenerator!=null){
				outputPath = reportGenerator.getOutputPath();
				outputPath = Utils.getFilePathWithDateFormat(outputPath, reportCriteria.getReportDate());
				templatePath = reportGenerator.getTemplate();
				
				if(outputPath == null){
					LOGGER.info("Please specify filePath");
				}else if(templatePath == null ){
					LOGGER.info("Please specify template");
				}else{
					OutputStream os = new FileOutputStream(outputPath);
					File templateFile = new File(templatePath);
					InputStream is = new FileInputStream(templateFile);
					Context context = reportGenerator.mapData(reportCriteria);
					JxlsHelper.getInstance().setUseFastFormulaProcessor(false).processTemplate(is, os, context);
				}
				
			}else{
				LOGGER.info("ReportType Not Found");
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			LOGGER.info(e1.getMessage());
		} catch (IOException e2) {
			e2.printStackTrace();
			LOGGER.info(e2.getMessage());
			
		} finally{ 
			try {
				if(book!=null){
					book.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.info(e.getMessage());
			}
		}
		return outputPath;
	}
	
	
	/**
	 * This method to find ReportGenerator by ReportType.<br>
	 * @param		pReportType	This is the parameter to find ReportGenerator.
	 * @return	Object that implements ReportGenerator
	 */
	private ReportGenerator getReport(ReportType pReportType){
		for(ReportType reportType:ReportType.values()){
			if(reportType.getId() == pReportType.getId()){
				return reportType.getReportGenerator();
			}
		}
		return null;
	}
	
	private ReportCriteria getReportCriteria(ReportType pReportType){
		for(ReportType reportType:ReportType.values()){
			if(reportType.getId() == pReportType.getId()){
				return reportType.getReportCriteria();
			}
		}
		return null;
	}
	
}
