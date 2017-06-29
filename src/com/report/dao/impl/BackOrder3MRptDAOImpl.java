package com.report.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.report.dao.BackOrderRptDAO;
import com.report.model.BackOrderSub1Model;
import com.report.model.BackOrderSub2Model;
import com.report.model.BackOrderSub3Model;
import com.report.util.DateUtils;

public class BackOrder3MRptDAOImpl extends JdbcDaoSupport implements BackOrderRptDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(BackOrder3MRptDAOImpl.class.getName());
	private String dateFormat = "yyyyMMdd";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BackOrderSub1Model> getSubReport1Data(Date reportDate) {

		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastMonthYYYYMM = DateUtils.getLastMonth(reportDate,"yyyyMM");
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<BackOrderSub1Model> result = null;
		
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BackOrderSub2Model> getSubReport2Data(Date reportDate) {
		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastMonthYYYYMM = DateUtils.getLastMonth(reportDate,"yyyyMM");
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<BackOrderSub2Model> result = null;
		
		return result;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BackOrderSub3Model> getSubReport3Data(Date reportDate) {
		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastMonthYYYYMM = DateUtils.getLastMonth(reportDate,"yyyyMM");
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<BackOrderSub3Model> result = null;
		
		return result;
	}

}
