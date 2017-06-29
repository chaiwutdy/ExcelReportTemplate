package com.report.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.report.dao.MailDAO;

public class MailDAOImpl extends JdbcDaoSupport implements MailDAO {
	private static final Logger LOGGER = LogManager.getLogger(MailDAOImpl.class.getName());
	
	@Override
	public List<String> getToList() {
		List<String> mailList = new ArrayList<String>();
		return mailList;
	}

	@Override
	public List<String> getCCList() {
		List<String> ccList = new ArrayList<String>();
		return ccList;
	}
	

}
