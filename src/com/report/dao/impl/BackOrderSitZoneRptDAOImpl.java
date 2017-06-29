package com.report.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.report.dao.BackOrderSitZoneRptDAO;
import com.report.model.BackOrderSitSubModel;
import com.report.util.DateUtils;

public class BackOrderSitZoneRptDAOImpl extends JdbcDaoSupport implements BackOrderSitZoneRptDAO {
	private static final Logger LOGGER = LogManager.getLogger(BackOrderSitZoneRptDAOImpl.class.getName());
	private String dateFormat = "yyyyMMdd";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BackOrderSitSubModel> getNetBooking(Date reportDate, String zoneId) {
		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lstDayOfPreMothYYYYMMDD = DateUtils.getLastDayOfPreviousMonth(reportDate,dateFormat);
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<BackOrderSitSubModel> result = null;
		
		return result;
	}

	@Override
	public List<String> getZoneList() {
		List<String> result = null;
		return result;
	}

	@Override
	public List<BackOrderSitSubModel> getRetailSales(Date reportDate,
			String zoneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BackOrderSitSubModel> getRemainBackOrderCal(Date reportDate,
			String zoneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BackOrderSitSubModel> getRemainBackOrderSys(Date reportDate,
			String zoneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BackOrderSitSubModel> getGap(Date reportDate, String zoneId) {
		// TODO Auto-generated method stub
		return null;
	}

}
