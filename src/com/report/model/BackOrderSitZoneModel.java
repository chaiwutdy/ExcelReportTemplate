package com.report.model;

import java.util.List;

public class BackOrderSitZoneModel {

	private String zoneId;
	private List<BackOrderSitSubModel> backOrderSitSub1Models;
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public List<BackOrderSitSubModel> getBackOrderSitSub1Models() {
		return backOrderSitSub1Models;
	}
	public void setBackOrderSitSub1Models(
			List<BackOrderSitSubModel> backOrderSitSub1Models) {
		this.backOrderSitSub1Models = backOrderSitSub1Models;
	}
	
}
