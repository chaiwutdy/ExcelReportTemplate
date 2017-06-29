package com.report.util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {

	private static final Locale LOCALE_TH = new Locale("th", "TH");
//    private static final Locale localeEN = Locale.US;
    
	public static String getCurrentDate(String format) {
    Date currentDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(format, LOCALE_TH);
    return sdf.format(currentDate);
  }
	
	public static String getDateByformat(Date date,String format,Locale locale) {
    SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
    return sdf.format(date);
  }
	
	public static String getDateByformat(Date date,String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
    return sdf.format(date);
  }
	
	public static String[] getShortMonths(){
		String [] months =  new DateFormatSymbols(Locale.US).getShortMonths();
		return Arrays.copyOf(months, months.length-1);
	}
	
	public static int[] getNumberOfDays(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int[]days = new int[maxDay];
		for(int i=0;i<maxDay;i++){
			days[i] = (i+1);
		}
		
		return days;
	}
	
	public static String getLastMonth(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		Calendar cal = new GregorianCalendar();//Calendar.getInstance();
		cal.setTime(date);
		if(cal.getActualMaximum(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)){
			cal.add(Calendar.MONTH, -1);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}else{
			cal.add(Calendar.MONTH, -1);
		}
		
		return sdf.format(cal.getTime());
	}
	
	public static String getLastDayOfPreviousMonth(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		Calendar cal = new GregorianCalendar();//Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(cal.getTime());
	}
	
	public static String getLastDayOfXMonth(Date date, String format, int month){
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		Calendar cal = new GregorianCalendar();//Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(cal.getTime());
	}
	
	public static Date getYesterday(){
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	/*public static Date getManualDate(){
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
	String dateInString = "10/05/2017";
	Date date = null;
	try {
		date = sdf.parse(dateInString);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return date;
	}*/
	
}
