package com.report.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.report.dao.CommonDAO;

public class CommonDAOImpl extends JdbcDaoSupport implements CommonDAO {
	private static final Logger LOGGER = LogManager.getLogger(CommonDAOImpl.class.getName());
	@Override
	public void callProcedure(String sql) {
		try {
			getJdbcTemplate().update(sql);
			LOGGER.info(sql.toString());
		} catch (Exception ex) { 
			ex.printStackTrace();
			LOGGER.info(ex.getMessage());
		}
	}

}
