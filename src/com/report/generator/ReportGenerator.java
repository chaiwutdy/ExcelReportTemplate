package com.report.generator;

import org.jxls.common.Context;

import com.report.criteria.ReportCriteria;

/**
 * This Interface to define The structure of a ReportGenerator.<br>
 * All ReportGenerator need to implements this interface.<br>
 * *** When implements this interface need to add it in com.report.constant.ReportType.
 */
public interface ReportGenerator {
	
	/**{@inheritDoc}
	 * This method always return path of report file.<br>
	 * Path of report file should store on config.properties file<br>
	 * and get value by Utils.getProperties(variable in config.properties).
	 * @return	path of report file
	 */
	public String getOutputPath();
	
	public String getTemplate();
	
	public Context mapData(ReportCriteria reportCriteria);
}
