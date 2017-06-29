package com.report.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.report.dao.StockRptDAO;
import com.report.model.StockSub1Model;
import com.report.model.StockSub2Model;
import com.report.model.StockSub3Model;
import com.report.util.DateUtils;

public class StockAdvanceRptDAOImpl extends JdbcDaoSupport implements StockRptDAO {
	private static final Logger LOGGER = LogManager.getLogger(StockAdvanceRptDAOImpl.class.getName());
	private String dateFormat = "yyyyMMdd";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<StockSub1Model> getSubReport1Data(Date reportDate) {

		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastDayOfLastMonth = DateUtils.getLastDayOfPreviousMonth(reportDate,dateFormat);
		String lastDayOf2MonthAgo = DateUtils.getLastDayOfXMonth(reportDate,dateFormat,-2);
		
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<StockSub1Model> result = null;
		
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<StockSub2Model> getSubReport2Data(Date reportDate) {
		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastDayOfLastMonth = DateUtils.getLastDayOfPreviousMonth(reportDate,dateFormat);
		String lastDayOf2MonthAgo = DateUtils.getLastDayOfXMonth(reportDate,dateFormat,-2);
		
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<StockSub2Model> result = null;
		
		return result;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<StockSub3Model> getSubReport3Data(Date reportDate) {
		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastDayOfLastMonth = DateUtils.getLastDayOfPreviousMonth(reportDate,dateFormat);
		String lastDayOf2MonthAgo = DateUtils.getLastDayOfXMonth(reportDate,dateFormat,-2);
		
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<StockSub3Model> result = null;
		return result;
	}

}
