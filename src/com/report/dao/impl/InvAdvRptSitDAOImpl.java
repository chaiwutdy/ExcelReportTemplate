package com.report.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.report.dao.InvAdvRptSitDAO;
import com.report.model.ChannelModel;
import com.report.model.MastCarSeriesModel;
import com.report.rowmapper.MastCarSeriesRowMapper;

public class InvAdvRptSitDAOImpl extends JdbcDaoSupport implements InvAdvRptSitDAO{

	public List<MastCarSeriesModel> getSubReport1Data() {
		List<MastCarSeriesModel> result = null;
		
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ChannelModel> getSubReport2Data() {
		List<ChannelModel> result = null;
	
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ChannelModel> getSubReport3Data() {
		List<ChannelModel> result = null;
		
		return result;
	}
	
}
