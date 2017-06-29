package com.report.criteria;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class NDODailySalesCriteria implements ReportCriteria{

	public Date getReportDate() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -12);
		return cal.getTime();
	}
	
}
