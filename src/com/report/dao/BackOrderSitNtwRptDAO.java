package com.report.dao;

import java.util.Date;
import java.util.List;

import com.report.model.BackOrderSitSubModel;

public interface BackOrderSitNtwRptDAO {
	public List<BackOrderSitSubModel> getNetBooking(Date reportDate);
	public List<BackOrderSitSubModel> getRetailSales(Date reportDate);
	public List<BackOrderSitSubModel> getRemainBackOrderCal(Date reportDate);
	public List<BackOrderSitSubModel> getRemainBackOrderSys(Date reportDate);
	public List<BackOrderSitSubModel> getGap(Date reportDate);
}
