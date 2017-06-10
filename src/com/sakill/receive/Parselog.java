package com.sakill.receive;
import com.sakill.receive.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parselog {
	public static HashMap <String,String> kk=new HashMap<String,String>();
	public static String temp;
	public static String reg_ip="\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
	public static String reg_date="";
	public static HashMap <String,String> parse(String vars){
	     log_ip(vars);
		 log_date(vars);
		 log_request(vars);
		 return kk;
	}
	
	public static void log_ip(String vars){
		String var_ip="";
		Pattern pet = Pattern.compile(reg_ip);
        Matcher match = pet.matcher(vars);
        while(match.find()){
        	var_ip=match.group();
        }
        kk.put("ip", var_ip);
	}
	
	public static void log_date(String vars){
		String var_date="";
		int left=vars.indexOf("[")+1;
		int right=vars.lastIndexOf("]");
		var_date=vars.substring(left, right);
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);
		try {
			time = sdf.parse(var_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		var_date = sdf2.format(time);
		kk.put("date", var_date);
	}
	
	public static void log_request(String vars){
		String var_request="";
		String [] b;
		String [] a=vars.split("\"");
		var_request=a[1];
		b=var_request.split(" ");
		//System.out.println(b[1]);
		kk.put("method",b[0]);
		kk.put("action", b[1]);
		var_request=a[2];
		b=var_request.split(" ");
		kk.put("status", b[1]);
		kk.put("device", a[5]);
	}
		
}