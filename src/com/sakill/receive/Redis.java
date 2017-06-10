package com.sakill.receive;
import com.sakill.receive.*;
import java.util.*;

import sun.misc.*;
import java.io.*;
import java.util.LinkedList;
import redis.clients.jedis.Jedis;
public class Redis{
	public static Jedis jedis;
	public static HashMap<String,String> parse_log=new HashMap<String,String>();
	public static void init() {
		 jedis = new Jedis(Receivelog.cmd.get("RedisHost"));
		jedis.auth("sakill");
			    
    }
	
	public static void out(){
		while(jedis.llen("whc")>0){
	    	  String tmp=Base64.decode(jedis.lpop("whc")); 
		  	    parse_log=Parselog.parse(tmp);
		  	    EsOut.out(parse_log);
	      }
	}

}
