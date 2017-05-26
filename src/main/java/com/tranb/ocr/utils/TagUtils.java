package com.tranb.ocr.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ydj.smart.common.tools.CommonUtils;

public class TagUtils {
	
	public static String longToDateString(Long datetime,String format){
		SimpleDateFormat sf = new SimpleDateFormat();
		sf.applyPattern(format);
		return sf.format(new Date(datetime));
	}
	
	public static String longToDateString(Long datetime){
		return longToDateString(datetime,"yyyy-MM-dd HH:mm:ss");
	}
	
	public static String fomatDecimal(Double decimal){
	  	DecimalFormat df2  = new DecimalFormat("0.000"); 
	  	String back = df2.format(decimal);
	  	return back;
	}
	public static int getRandomNumber(Integer mix,Integer max){
	  	return  CommonUtils.getRandomNumber(mix, max);
	}
	public static String fomatDecimal(Integer divisor,Integer dividend){
	  	DecimalFormat df2  = new DecimalFormat("0.000"); 
		if(divisor==0||dividend==0){
			return 0+"";
		}else{
			return df2.format((divisor+0.0)/dividend);
		}
	}
	public static String fomatDecimal2(Integer divisor,Integer dividend){
	  	DecimalFormat df2  = new DecimalFormat("0.0000"); 
		if(divisor==0||dividend==0){
			return 0+"";
		}else{
			return df2.format((divisor+0.0)/dividend);
		}
	}
	
	public static void main(String[] args){
	  	DecimalFormat df2  = new DecimalFormat("0.000"); 
	  	String back = df2.format((945966+0.0)/888838);
	  	System.out.println(back);
	}
}
