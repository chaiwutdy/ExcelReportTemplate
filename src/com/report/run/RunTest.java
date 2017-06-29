package com.report.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.report.util.Utils;

public class RunTest {

	public static void main(String[] args) {
		//String path = "$yyyy$test $dd/MM/yyyy$_test_$yyyyMM$.xlsx";
		String path = "test ${dd/MM/yyyy}|${$yyyy}.xlsx";
		String result = Utils.getFilePathWithDateFormat2(path,new Date());
		System.out.println(result);
//		System.out.println(path.indexOf('$'+'{'));
	}
	
	public static void test1(List<String> mailList){
		mailList = new ArrayList<String>();
		mailList.add("5");
	}
	
}
