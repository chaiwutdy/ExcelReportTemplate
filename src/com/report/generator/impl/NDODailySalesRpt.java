package com.report.generator.impl;

import java.util.ArrayList;
import java.util.List;

import org.jxls.common.Context;

import com.report.criteria.ReportCriteria;
import com.report.dao.BackOrderRptDAO;
import com.report.dao.BackOrderSitNtwRptDAO;
import com.report.dao.BackOrderSitZoneRptDAO;
import com.report.dao.CommonDAO;
import com.report.dao.StockRptDAO;
import com.report.generator.ReportGenerator;
import com.report.model.BackOrderSitSubModel;
import com.report.model.BackOrderSitZoneModel;
import com.report.model.BackOrderSub1Model;
import com.report.model.BackOrderSub2Model;
import com.report.model.BackOrderSub3Model;
import com.report.model.StockSub1Model;
import com.report.model.StockSub2Model;
import com.report.model.StockSub3Model;
import com.report.util.DateUtils;
import com.report.util.Utils;


public class NDODailySalesRpt implements ReportGenerator{

	@Override
	public String getOutputPath() {
		return Utils.getProperties("app.path.ndoDailySalesRpt");
	}
	
	@Override
	public String getTemplate() {
		return Utils.getProperties("app.template.ndoDailySalesRpt");
	}
	
	@Override
	public Context mapData(ReportCriteria reportCriteria){
//		callProcedureForGetNewData();
		Context context = new Context();
		context.putVar("reportDate", DateUtils.getDateByformat(reportCriteria.getReportDate(), "dd/MM/yyyy"));
		mappingBackOrderData(reportCriteria, context);
		mappingStockData(reportCriteria, context);
		mappingOrderSitNtwData(reportCriteria, context);
		mappingOrderSitZoneData(reportCriteria, context);
		return context;
	}
	
	public void mappingBackOrderData(ReportCriteria reportCriteria, Context context){
		BackOrderRptDAO backOrderRptDAO = (BackOrderRptDAO)Utils.APP_CONTEXT.getBean("backOrderRptDAO");
		List<BackOrderSub1Model>  backOrderSub1Models = backOrderRptDAO.getSubReport1Data(reportCriteria.getReportDate());
		List<BackOrderSub2Model>  backOrderSub2Models = backOrderRptDAO.getSubReport2Data(reportCriteria.getReportDate());
		List<BackOrderSub3Model>  backOrderSub3Models = backOrderRptDAO.getSubReport3Data(reportCriteria.getReportDate());
	
		BackOrderRptDAO backOrder3MRptDAO = (BackOrderRptDAO)Utils.APP_CONTEXT.getBean("backOrder3MRptDAO");
		List<BackOrderSub1Model>  backOrder3MSub1Models = backOrder3MRptDAO.getSubReport1Data(reportCriteria.getReportDate());
		List<BackOrderSub2Model>  backOrder3MSub2Models = backOrder3MRptDAO.getSubReport2Data(reportCriteria.getReportDate());
		List<BackOrderSub3Model>  backOrder3MSub3Models = backOrder3MRptDAO.getSubReport3Data(reportCriteria.getReportDate());
		
		BackOrderRptDAO backOrderMore3MRptDAO = (BackOrderRptDAO)Utils.APP_CONTEXT.getBean("backOrderMore3MRptDAO");
		List<BackOrderSub1Model>  backOrderMore3MSub1Models = backOrderMore3MRptDAO.getSubReport1Data(reportCriteria.getReportDate());
		List<BackOrderSub2Model>  backOrderMore3MSub2Models = backOrderMore3MRptDAO.getSubReport2Data(reportCriteria.getReportDate());
		List<BackOrderSub3Model>  backOrderMore3MSub3Models = backOrderMore3MRptDAO.getSubReport3Data(reportCriteria.getReportDate());
		
		context.putVar("backOrderSub1Models", backOrderSub1Models);
		context.putVar("backOrderSub2Models", backOrderSub2Models);
		context.putVar("backOrderSub3Models", backOrderSub3Models);
		
		context.putVar("backOrder3MSub1Models", backOrder3MSub1Models);
		context.putVar("backOrder3MSub2Models", backOrder3MSub2Models);
		context.putVar("backOrder3MSub3Models", backOrder3MSub3Models);
		
		context.putVar("backOrderMore3MSub1Models", backOrderMore3MSub1Models);
		context.putVar("backOrderMore3MSub2Models", backOrderMore3MSub2Models);
		context.putVar("backOrderMore3MSub3Models", backOrderMore3MSub3Models);
	}
	
	
	public void mappingStockData(ReportCriteria reportCriteria, Context context){
		StockRptDAO stockRptDAO = (StockRptDAO)Utils.APP_CONTEXT.getBean("stockRptDAO");
		List<StockSub1Model>  stockSub1Models = stockRptDAO.getSubReport1Data(reportCriteria.getReportDate());
		List<StockSub2Model>  stockSub2Models = stockRptDAO.getSubReport2Data(reportCriteria.getReportDate());
		List<StockSub3Model>  stockSub3Models = stockRptDAO.getSubReport3Data(reportCriteria.getReportDate());
	
		StockRptDAO stockAdvanceRptDAO = (StockRptDAO)Utils.APP_CONTEXT.getBean("stockAdvanceRptDAO");
		List<StockSub1Model>  stockAdvanceSub1Models = stockAdvanceRptDAO.getSubReport1Data(reportCriteria.getReportDate());
		List<StockSub2Model>  stockAdvanceSub2Models = stockAdvanceRptDAO.getSubReport2Data(reportCriteria.getReportDate());
		List<StockSub3Model>  stockAdvanceSub3Models = stockAdvanceRptDAO.getSubReport3Data(reportCriteria.getReportDate());
		
		StockRptDAO stockNormalRptDAO = (StockRptDAO)Utils.APP_CONTEXT.getBean("stockNormalRptDAO");
		List<StockSub1Model>  stockNormalSub1Models = stockNormalRptDAO.getSubReport1Data(reportCriteria.getReportDate());
		List<StockSub2Model>  stockNormalSub2Models = stockNormalRptDAO.getSubReport2Data(reportCriteria.getReportDate());
		List<StockSub3Model>  stockNormalSub3Models = stockNormalRptDAO.getSubReport3Data(reportCriteria.getReportDate());
		
		context.putVar("stockSub1Models", stockSub1Models);
		context.putVar("stockSub2Models", stockSub2Models);
		context.putVar("stockSub3Models", stockSub3Models);
		
		context.putVar("stockAdvanceSub1Models", stockAdvanceSub1Models);
		context.putVar("stockAdvanceSub2Models", stockAdvanceSub2Models);
		context.putVar("stockAdvanceSub3Models", stockAdvanceSub3Models);
		
		context.putVar("stockNormalSub1Models", stockNormalSub1Models);
		context.putVar("stockNormalSub2Models", stockNormalSub2Models);
		context.putVar("stockNormalSub3Models", stockNormalSub3Models);
	}
	
	private void callProcedureForGetNewData(){
		CommonDAO commonDAO = (CommonDAO)Utils.APP_CONTEXT.getBean("commonDAO");
		commonDAO.callProcedure("CALL STOCK_DAILY_REPORT_WUTTEST2()");
		commonDAO.callProcedure("CALL BACK_ORDER_RPT_WUTTEST()");
		commonDAO.callProcedure("CALL BACK_ORDER_SIT_RPT_WUTTEST()");
	}

	private void mappingOrderSitNtwData(ReportCriteria reportCriteria, Context context){
		BackOrderSitNtwRptDAO backOrderSitNtwRptDAO = (BackOrderSitNtwRptDAO)Utils.APP_CONTEXT.getBean("backOrderSitNtwRptDAO");
		List<BackOrderSitSubModel> backOrderSitNtwSub1Models = backOrderSitNtwRptDAO.getNetBooking(reportCriteria.getReportDate());
		List<BackOrderSitSubModel> backOrderSitNtwSub2Models = backOrderSitNtwRptDAO.getRetailSales(reportCriteria.getReportDate());
		List<BackOrderSitSubModel> backOrderSitNtwSub3Models = backOrderSitNtwRptDAO.getRemainBackOrderCal(reportCriteria.getReportDate());
		List<BackOrderSitSubModel> backOrderSitNtwSub4Models = backOrderSitNtwRptDAO.getRemainBackOrderSys(reportCriteria.getReportDate());
		List<BackOrderSitSubModel> backOrderSitNtwSub5Models = backOrderSitNtwRptDAO.getGap(reportCriteria.getReportDate());
		
		context.putVar("backOrderSitNtwSub1Models", backOrderSitNtwSub1Models);
		context.putVar("backOrderSitNtwSub2Models", backOrderSitNtwSub2Models);
		context.putVar("backOrderSitNtwSub3Models", backOrderSitNtwSub3Models);
		context.putVar("backOrderSitNtwSub4Models", backOrderSitNtwSub4Models);
		context.putVar("backOrderSitNtwSub5Models", backOrderSitNtwSub5Models);
	}
	
	private void mappingOrderSitZoneData(ReportCriteria reportCriteria, Context context){
		List<BackOrderSitZoneModel> backOrderSitZoneModels = new ArrayList<BackOrderSitZoneModel>();
		BackOrderSitZoneModel backOrderSitZoneModel = null;
		
		BackOrderSitZoneRptDAO backOrderSitZoneRptDAO = (BackOrderSitZoneRptDAO)Utils.APP_CONTEXT.getBean("backOrderSitZoneRptDAO");
		for(String zoneId :backOrderSitZoneRptDAO.getZoneList()){
			backOrderSitZoneModel = new BackOrderSitZoneModel();
			backOrderSitZoneModel.setZoneId(zoneId);
			backOrderSitZoneModel.setBackOrderSitSub1Models(backOrderSitZoneRptDAO.getNetBooking(reportCriteria.getReportDate(), zoneId));
			backOrderSitZoneModels.add(backOrderSitZoneModel);
		}
		context.putVar("backOrderSitZoneModels", backOrderSitZoneModels);
	}
	

}
