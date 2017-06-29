package com.report.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.report.dao.DummyRptDAO;

public class DummyRptDAOImpl extends JdbcDaoSupport implements DummyRptDAO{

	public String getReportData() {
		String result = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT 'HELLO WORLD' FROM DUAL ");
			result = getJdbcTemplate().queryForObject(sql.toString(), String.class);
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		return result;
	}

}
