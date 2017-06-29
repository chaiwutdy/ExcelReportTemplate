package com.report.dao;

import java.util.Date;
import java.util.List;

import com.report.model.BackOrderSitSubModel;

public interface BackOrderSitZoneRptDAO {
	public List<BackOrderSitSubModel> getNetBooking(Date reportDate, String zoneId);
	public List<BackOrderSitSubModel> getRetailSales(Date reportDate, String zoneId);
	public List<BackOrderSitSubModel> getRemainBackOrderCal(Date reportDate, String zoneId);
	public List<BackOrderSitSubModel> getRemainBackOrderSys(Date reportDate, String zoneId);
	public List<BackOrderSitSubModel> getGap(Date reportDate, String zoneId);
	public List<String> getZoneList();
}
