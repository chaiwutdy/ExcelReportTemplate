package com.report.run;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.report.constant.ReportType;
import com.report.generator.GenerateMail;
import com.report.generator.GenerateReport;

public class RunGenerateReport {

	private static final Logger LOGGER = LogManager.getLogger(RunGenerateReport.class.getName());
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		LOGGER.debug("Debug Message Logged !!!");
//    LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
//		genAllReport();
		/*String fname = genSingleReport(ReportType.NDO_DAILY_SALES_RPT);
		Mail_BK2 mail = new Mail_BK2();
		mail.send(fname);*/
		
		genSingleReport(ReportType.NDO_DAILY_SALES_RPT);
	}
	
	public static void  genSingleReport(ReportType reportType){
		GenerateReport report = new GenerateReport();
		GenerateMail mail = new GenerateMail();
		String fileDataSourceName = report.writeReport(reportType);
		mail.send(reportType, fileDataSourceName);
		LOGGER.info("Success! FilePath:"+fileDataSourceName);
	}
	
	public static void genAllReport(){
		String fileDataSourceName;
		GenerateReport generateReport = new GenerateReport();
		GenerateMail mail = new GenerateMail();
		for(ReportType reportType :ReportType.values()){
			fileDataSourceName = generateReport.writeReport(reportType);
			  mail.send(reportType, fileDataSourceName);
			  LOGGER.info("Success! FilePath:"+fileDataSourceName);
		}
	}
}
