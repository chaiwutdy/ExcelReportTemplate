package com.report.criteria;

import java.util.Date;

import com.report.util.DateUtils;

public class DefaultReportCriteria implements ReportCriteria{
	
	public Date getReportDate() {
		return DateUtils.getYesterday();
	}

}
