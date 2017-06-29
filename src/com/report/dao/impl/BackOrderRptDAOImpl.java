package com.report.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.report.dao.BackOrderRptDAO;
import com.report.model.BackOrderSub1Model;
import com.report.model.BackOrderSub2Model;
import com.report.model.BackOrderSub3Model;
import com.report.util.DateUtils;

public class BackOrderRptDAOImpl extends JdbcDaoSupport implements BackOrderRptDAO {
	private static final Logger LOGGER = LogManager.getLogger(BackOrderRptDAOImpl.class.getName());
	private String dateFormat = "yyyyMMdd";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BackOrderSub1Model> getSubReport1Data(Date reportDate) {

		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastMonthYYYYMM = DateUtils.getLastMonth(reportDate,"yyyyMM");
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<BackOrderSub1Model> result = null;
		
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BackOrderSub2Model> getSubReport2Data(Date reportDate) {
		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastMonthYYYYMM = DateUtils.getLastMonth(reportDate,"yyyyMM");
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<BackOrderSub2Model> result = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("WITH t_data AS ( ");
			sql.append("SELECT t_master.ZONE_ID, t_master.DO_ZONE_NAME, t_master.DO_ZONE_SUB_TOTAL1, t_detail.AS_OF_DATE,  ");
			sql.append("SUM(TOTAL_CAR) TOTAL_CAR ");
			sql.append("FROM CHANNEL t_master ");
			sql.append("INNER JOIN DATA_DMS_BACK_ORDER_WUTTEST t_detail ");
			sql.append("ON t_master.CHANNEL_CODE = t_detail.DEALER_CODE ");
			sql.append("WHERE t_master.STATUS = 'A' ");
			sql.append("AND t_master.DEALER_FLAG IN ('3','1') ");
			sql.append("AND TO_CHAR(t_detail.AS_OF_DATE,'yyyyMM') = '"+reportMonth+"' ");
			sql.append("AND TO_CHAR(t_detail.AS_OF_DATE,'"+dateFormat+"') <= '"+reportDateStr+"' ");
			sql.append("OR TO_CHAR(t_detail.AS_OF_DATE,'"+dateFormat+"') = '"+lastMonthYYYYMMDD+"' ");
			sql.append("GROUP BY t_master.ZONE_ID, t_master.DO_ZONE_NAME, t_master.DO_ZONE_SUB_TOTAL1, t_detail.AS_OF_DATE) ");
			
			sql.append(",t_data2 AS ( ");
			sql.append("SELECT t_master.ZONE_ID, t_master.DO_ZONE_NAME, t_master.DO_ZONE_SUB_TOTAL1, ");
			sql.append("NVL(SUM(DECODE(TO_CHAR(t_detail.BK_DATE,'yyyyMM'),'"+reportMonth+"',TOTAL_CAR)),0) AS ACCUMTHISMONTH, ");
			sql.append("NVL(SUM(DECODE(SIGN(TO_CHAR(t_detail.BK_DATE,'yyyyMM')- "+reportMonth+") ,-1, TOTAL_CAR)),0) AS ACCUMPREVIOUSMONTH ");
			sql.append("FROM CHANNEL t_master ");
			sql.append("INNER JOIN DATA_DMS_BACK_ORDER_WUTTEST t_detail ");
			sql.append("ON t_master.CHANNEL_CODE = t_detail.DEALER_CODE ");
			sql.append("WHERE t_master.STATUS = 'A' ");
			sql.append("AND t_master.DEALER_FLAG IN ('3','1') ");
			sql.append("AND TO_CHAR(t_detail.AS_OF_DATE,'"+dateFormat+"') = '"+reportDateStr+"' ");
			sql.append("GROUP BY t_master.ZONE_ID, t_master.DO_ZONE_NAME, t_master.DO_ZONE_SUB_TOTAL1) ");
			
			sql.append(",t_data3 AS ( ");
			sql.append("SELECT t_master.ZONE_ID, t_master.DO_ZONE_NAME, t_master.DO_ZONE_SUB_TOTAL1, ");
			sql.append("NVL(SUM(DECODE(TO_CHAR(t_detail.BK_DATE,'yyyyMM'),'"+lastMonthYYYYMM+"',TOTAL_CAR)),0) AS ACCUMLASTMONTHTHISMONTH, ");
			sql.append("NVL(SUM(DECODE(SIGN(TO_CHAR(t_detail.BK_DATE,'yyyyMM')- "+lastMonthYYYYMM+") ,-1, TOTAL_CAR)),0) AS ACCUMLASTMONTHPREVIOUSMONTH ");
			sql.append("FROM CHANNEL t_master ");
			sql.append("INNER JOIN DATA_DMS_BACK_ORDER_WUTTEST t_detail ");
			sql.append("ON t_master.CHANNEL_CODE = t_detail.DEALER_CODE ");
			sql.append("WHERE t_master.STATUS = 'A' ");
			sql.append("AND t_master.DEALER_FLAG IN ('3','1') ");
			sql.append("AND TO_CHAR(t_detail.AS_OF_DATE,'"+dateFormat+"') = '"+lastMonthYYYYMMDD+"' ");
			sql.append("GROUP BY t_master.ZONE_ID, t_master.DO_ZONE_NAME, t_master.DO_ZONE_SUB_TOTAL1) ");
			
			sql.append("SELECT td1.ZONE_ID, td1.DO_ZONE_NAME AS DOZONENAME, td1.DO_ZONE_SUB_TOTAL1 AS DOZONESUBTOTAL1");
			for(int day:days){
				sql.append(",NVL(SUM(DECODE(to_char(td1.AS_OF_DATE,'"+dateFormat+"'),'"+reportMonth+StringUtils.leftPad(String.valueOf(day), 2, "0")+"',td1.TOTAL_CAR)),0)  AS DAY"+day+" ");
			}
			sql.append(",NVL(SUM(DECODE(to_char(td1.AS_OF_DATE,'"+dateFormat+"'),'"+reportDateStr+"',td1.TOTAL_CAR)),0)  AS TOTAL ");
			sql.append(",NVL(SUM(DECODE(to_char(td1.AS_OF_DATE,'"+dateFormat+"'),'"+lastMonthYYYYMMDD+"',td1.TOTAL_CAR)),0)  AS LASTMONTH ");
			
			sql.append(",(SELECT td2.ACCUMTHISMONTH FROM t_data2 td2 WHERE td2.ZONE_ID = td1.ZONE_ID AND  td2.DO_ZONE_NAME = td1.DO_ZONE_NAME AND ROWNUM = 1 ) AS ACCUMTHISMONTH ");
			sql.append(",(SELECT td2.ACCUMPREVIOUSMONTH FROM t_data2 td2 WHERE td2.ZONE_ID = td1.ZONE_ID AND  td2.DO_ZONE_NAME = td1.DO_ZONE_NAME AND ROWNUM = 1 ) AS ACCUMPREVIOUSMONTH ");
			
			sql.append(",(SELECT td3.ACCUMLASTMONTHTHISMONTH FROM t_data3 td3 WHERE td3.ZONE_ID = td1.ZONE_ID AND  td3.DO_ZONE_NAME = td1.DO_ZONE_NAME AND ROWNUM = 1 ) AS ACCUMLASTMONTHTHISMONTH ");
			sql.append(",(SELECT td3.ACCUMLASTMONTHPREVIOUSMONTH FROM t_data3 td3 WHERE td3.ZONE_ID = td1.ZONE_ID AND  td3.DO_ZONE_NAME = td1.DO_ZONE_NAME AND ROWNUM = 1 ) AS ACCUMLASTMONTHPREVIOUSMONTH ");
			
			sql.append("FROM t_data td1 ");
			sql.append("GROUP BY td1.ZONE_ID, td1.DO_ZONE_NAME, td1.DO_ZONE_SUB_TOTAL1 ");
			sql.append("ORDER BY td1.ZONE_ID ");
			
			LOGGER.info(sql.toString());
			result = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(BackOrderSub2Model.class));
		} catch (Exception ex) { 
			ex.printStackTrace();
			LOGGER.info(ex.getMessage());
		}
		return result;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BackOrderSub3Model> getSubReport3Data(Date reportDate) {
		String reportDateStr = DateUtils.getDateByformat(reportDate,dateFormat,Locale.US);
		String reportMonth = DateUtils.getDateByformat(reportDate,"yyyyMM",Locale.US);
		String lastMonthYYYYMMDD = DateUtils.getLastMonth(reportDate,dateFormat);
		String lastMonthYYYYMM = DateUtils.getLastMonth(reportDate,"yyyyMM");
		int[] days = DateUtils.getNumberOfDays(reportDate);
		List<BackOrderSub3Model> result = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("WITH t_data AS ( ");
			sql.append("SELECT t_master.DISPLAY_SEQ, t_master.ZONE_ID, t_master.AREA_ID, t_master.CHANNEL_CODE, t_master.CH_SHORT_NAME, t_detail.AS_OF_DATE,  ");
			sql.append("SUM(TOTAL_CAR) TOTAL_CAR  ");
			sql.append("FROM CHANNEL t_master ");
			sql.append("INNER JOIN DATA_DMS_BACK_ORDER_WUTTEST t_detail ");
			sql.append("ON t_master.CHANNEL_CODE = t_detail.DEALER_CODE ");
			sql.append("WHERE t_master.STATUS = 'A' ");
			sql.append("AND t_master.DEALER_FLAG IN ('3','1') ");
			sql.append("AND TO_CHAR(t_detail.AS_OF_DATE,'yyyyMM') = '"+reportMonth+"' ");
			sql.append("AND TO_CHAR(t_detail.AS_OF_DATE,'"+dateFormat+"') <= '"+reportDateStr+"' ");
			sql.append("OR TO_CHAR(t_detail.AS_OF_DATE,'"+dateFormat+"') = '"+lastMonthYYYYMMDD+"' ");
			sql.append("GROUP BY t_master.DISPLAY_SEQ, t_master.ZONE_ID, t_master.AREA_ID, t_master.CHANNEL_CODE, t_master.CH_SHORT_NAME, t_detail.AS_OF_DATE) ");
			
			sql.append(",t_data2 AS ( ");
			sql.append("SELECT t_master.ZONE_ID, t_master.AREA_ID, t_master.CHANNEL_CODE, t_master.CH_SHORT_NAME, ");
			sql.append("NVL(SUM(DECODE(TO_CHAR(t_detail.BK_DATE,'yyyyMM'),'"+reportMonth+"',TOTAL_CAR)),0) AS ACCUMTHISMONTH, ");
			sql.append("NVL(SUM(DECODE(SIGN(TO_CHAR(t_detail.BK_DATE,'yyyyMM')- "+reportMonth+") ,-1, TOTAL_CAR)),0) AS ACCUMPREVIOUSMONTH ");
			sql.append("FROM CHANNEL t_master ");
			sql.append("INNER JOIN DATA_DMS_BACK_ORDER_WUTTEST t_detail ");
			sql.append("ON t_master.CHANNEL_CODE = t_detail.DEALER_CODE ");
			sql.append("WHERE t_master.STATUS = 'A' ");
			sql.append("AND t_master.DEALER_FLAG IN ('3','1') ");
			sql.append("AND TO_CHAR(t_detail.AS_OF_DATE,'"+dateFormat+"') = '"+reportDateStr+"' ");
			sql.append("GROUP BY t_master.ZONE_ID, t_master.AREA_ID, t_master.CHANNEL_CODE, t_master.CH_SHORT_NAME) ");
			
			sql.append(",t_data3 AS ( ");
			sql.append("SELECT t_master.ZONE_ID, t_master.AREA_ID, t_master.CHANNEL_CODE, t_master.CH_SHORT_NAME, ");
			sql.append("NVL(SUM(DECODE(TO_CHAR(t_detail.BK_DATE,'yyyyMM'),'"+lastMonthYYYYMM+"',TOTAL_CAR)),0) AS ACCUMLASTMONTHTHISMONTH, ");
			sql.append("NVL(SUM(DECODE(SIGN(TO_CHAR(t_detail.BK_DATE,'yyyyMM')- "+lastMonthYYYYMM+") ,-1, TOTAL_CAR)),0) AS ACCUMLASTMONTHPREVIOUSMONTH ");
			sql.append("FROM CHANNEL t_master ");
			sql.append("INNER JOIN DATA_DMS_BACK_ORDER_WUTTEST t_detail ");
			sql.append("ON t_master.CHANNEL_CODE = t_detail.DEALER_CODE ");
			sql.append("WHERE t_master.STATUS = 'A' ");
			sql.append("AND t_master.DEALER_FLAG IN ('3','1') ");
			sql.append("AND TO_CHAR(t_detail.AS_OF_DATE,'"+dateFormat+"') = '"+lastMonthYYYYMMDD+"' ");
			sql.append("GROUP BY t_master.ZONE_ID, t_master.AREA_ID, t_master.CHANNEL_CODE, t_master.CH_SHORT_NAME) ");
			
			sql.append("SELECT td1.DISPLAY_SEQ, td1.ZONE_ID AS ZONEID, td1.AREA_ID AS AREAID, td1.CHANNEL_CODE AS DEALERCODE, td1.CH_SHORT_NAME AS DEALERSHORTNAME ");
			for(int day:days){
				sql.append(",NVL(SUM(DECODE(to_char(td1.AS_OF_DATE,'"+dateFormat+"'),'"+reportMonth+StringUtils.leftPad(String.valueOf(day), 2, "0")+"',td1.TOTAL_CAR)),0)  AS DAY"+day+" ");
			}
			sql.append(",NVL(SUM(DECODE(to_char(td1.AS_OF_DATE,'"+dateFormat+"'),'"+reportDateStr+"',td1.TOTAL_CAR)),0)  AS TOTAL ");
			sql.append(",NVL(SUM(DECODE(to_char(td1.AS_OF_DATE,'"+dateFormat+"'),'"+lastMonthYYYYMMDD+"',td1.TOTAL_CAR)),0)  AS LASTMONTH ");
			
			sql.append(",(SELECT td2.ACCUMTHISMONTH FROM t_data2 td2 WHERE td2.ZONE_ID = td1.ZONE_ID AND  td2.AREA_ID = td1.AREA_ID AND  td2.CHANNEL_CODE = td1.CHANNEL_CODE AND ROWNUM = 1 ) AS ACCUMTHISMONTH ");
			sql.append(",(SELECT td2.ACCUMPREVIOUSMONTH FROM t_data2 td2 WHERE td2.ZONE_ID = td1.ZONE_ID AND  td2.AREA_ID = td1.AREA_ID AND  td2.CHANNEL_CODE = td1.CHANNEL_CODE AND ROWNUM = 1 ) AS ACCUMPREVIOUSMONTH ");
			
			sql.append(",(SELECT td3.ACCUMLASTMONTHTHISMONTH FROM t_data3 td3 WHERE td3.ZONE_ID = td1.ZONE_ID AND  td3.AREA_ID = td1.AREA_ID AND td3.CHANNEL_CODE = td1.CHANNEL_CODE AND ROWNUM = 1 ) AS ACCUMLASTMONTHTHISMONTH ");
			sql.append(",(SELECT td3.ACCUMLASTMONTHPREVIOUSMONTH FROM t_data3 td3 WHERE td3.ZONE_ID = td1.ZONE_ID AND  td3.AREA_ID = td1.AREA_ID AND td3.CHANNEL_CODE = td1.CHANNEL_CODE AND ROWNUM = 1 ) AS ACCUMLASTMONTHPREVIOUSMONTH ");
			
			sql.append("FROM t_data td1 ");
			sql.append("GROUP BY td1.DISPLAY_SEQ, td1.ZONE_ID, td1.AREA_ID, td1.CHANNEL_CODE, td1.CH_SHORT_NAME ");
			sql.append("ORDER BY td1.ZONE_ID, td1.AREA_ID, td1.DISPLAY_SEQ ");
			
			LOGGER.info(sql.toString());
			result = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(BackOrderSub3Model.class));
		} catch (Exception ex) { 
			ex.printStackTrace();
			LOGGER.info(ex.getMessage());
		}
		return result;
	}

}
