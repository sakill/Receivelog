package com.sakill.receive;
import com.sakill.receive.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.*;
import java.lang.Thread;
import redis.clients.jedis.Jedis;
public class Receivelog {
	public static HashMap<String,String> cmd;
	public static HashMap<String,String> temp;
	public static void main(String[] args) throws IOException{
//		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		Tool kk=new Tool(args);	
			cmd=kk.cmdParse();
			System.out.println(cmd.get("RedisHost"));
			EsOut.init();
			Redis.init();
			while(true){
				Redis.out();
			}
	}
}
