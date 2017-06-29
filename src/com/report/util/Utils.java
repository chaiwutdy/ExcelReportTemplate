package com.report.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ReflectionUtils;

public class Utils {
	private static final Logger LOGGER = LogManager.getLogger(Utils.class.getName());
	private static final String SPRING_MODULE_PATH = "Spring-Module.xml";
	private static final String CONFIG_PATH = "config.properties";
	public static final ApplicationContext APP_CONTEXT = new ClassPathXmlApplicationContext(SPRING_MODULE_PATH);
	
	/*public static ApplicationContext getApplicationContext(){
		return new ClassPathXmlApplicationContext(SPRING_MODULE_PATH);
	}*/
	
	public static String getProperties(String key){
		InputStream inputStream = null;
		String result = "";
		try {
			Properties prop = new Properties();
			inputStream = Utils.class.getClassLoader().getResourceAsStream(CONFIG_PATH);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + CONFIG_PATH + "' not found in the classpath");
			}
 
			return prop.getProperty(key);
		} catch (Exception e) {
			LOGGER.info("Exception:"+e.getMessage());
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static Object getValueByMethodName(Object obj,String methodName){
		Field field;
		Object result = null;
		try {
				field = obj.getClass().getDeclaredField(methodName);
				field.setAccessible(true);
				result = ReflectionUtils.getField(field, obj);
		} catch (NoSuchFieldException e) {
			LOGGER.info("Exception:"+e.getMessage());
			e.printStackTrace();
			result = null;
		} catch (SecurityException e) {
			e.printStackTrace();
			LOGGER.info("Exception:"+e.getMessage());
			result = null;
		}
		return result;
	}
	
	public static int convertString2Int(String strNumber){
		try{
			return Integer.parseInt(strNumber);
		}catch(Exception e){
			return 0;
		}
	}
	
	//$dateformat$ --> $dd/MM/yyyy$ , $yyyyMM$
	public static String getFilePathWithDateFormat(String filePath,Date date){
		char symbolOfPattern = '$';
		String result = null;
		String dateStr = null;
		StringBuffer dateFormatTarget = new StringBuffer();
		StringBuffer dateFormatReplaceMent = new StringBuffer();
		int count = 0;
		
		try{
			if(filePath != null){
				for(char c:filePath.toCharArray()){
					if( c == symbolOfPattern ){
						dateFormatTarget.append(c);
						count++;
					}
					
					if( count == 2 ){break;}
					
					if( count == 1 && c != symbolOfPattern ){
						dateFormatTarget.append(c);
						dateFormatReplaceMent.append(c);
					}
				}
				
				dateStr = DateUtils.getDateByformat(date, dateFormatReplaceMent.toString());
				result = filePath.replace(dateFormatTarget, dateStr);
				
				if(result.indexOf(symbolOfPattern) > 0){
					result = getFilePathWithDateFormat(result, date);
				}
				
			}
		}catch(Exception e){
			result = filePath;
			LOGGER.info("Exception:"+e.getMessage());
		}
		return result;
	}
	
	//${dateformat} --> ${dd/MM/yyyy} , ${yyyyMM}
	public static String getFilePathWithDateFormat2(String filePath,Date date){
		String startPattern = "${";
		char startBody = '{';
		char endBody = '}';
		String result = null;
		String dateStr = null;
		StringBuffer dateFormatTarget = new StringBuffer();
		StringBuffer dateFormatReplaceMent = new StringBuffer();
		int count = 0;
		
		try{
			if(filePath != null){
				if( filePath.indexOf(startPattern) > 0 &&
						filePath.indexOf(endBody) > 0 ){
					
					for(char c:filePath.toCharArray()){
						if( c == startBody ){
							dateFormatTarget.append(startPattern);
							count++;
						}
						
						if( count == 1 && c != startBody && c != endBody){
							dateFormatTarget.append(c);
							dateFormatReplaceMent.append(c);
						}
						
						if( c == endBody ){
							dateFormatTarget.append(endBody);
							break;
						}
					}
					
					
					dateStr = DateUtils.getDateByformat(date, dateFormatReplaceMent.toString());
					result = filePath.replace(dateFormatTarget, dateStr);
				}else{
					LOGGER.info("filePath:"+filePath+" is wrong format");
				}
				
				if(	result.indexOf(startPattern) > 0 && 
						result.indexOf(endBody) > 0 ){
						result = getFilePathWithDateFormat2(result, date);
				}
				
			}
		}catch(Exception e){
			result = filePath;
			LOGGER.info("Exception:"+e.getMessage());
		}
		return result;
	}
}
