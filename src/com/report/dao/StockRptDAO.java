package com.report.dao;

import java.util.Date;
import java.util.List;

import com.report.model.StockSub1Model;
import com.report.model.StockSub2Model;
import com.report.model.StockSub3Model;

public interface StockRptDAO {
	public List<StockSub1Model> getSubReport1Data(Date reportDate);
	public List<StockSub2Model> getSubReport2Data(Date reportDate);
	public List<StockSub3Model> getSubReport3Data(Date reportDate);
}
