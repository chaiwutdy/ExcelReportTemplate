package com.report.dao;

import java.util.Date;
import java.util.List;

import com.report.model.BackOrderSub1Model;
import com.report.model.BackOrderSub2Model;
import com.report.model.BackOrderSub3Model;

public interface BackOrderRptDAO {
	public List<BackOrderSub1Model> getSubReport1Data(Date reportDate);
	public List<BackOrderSub2Model> getSubReport2Data(Date reportDate);
	public List<BackOrderSub3Model> getSubReport3Data(Date reportDate);
}
