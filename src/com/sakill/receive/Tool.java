package com.sakill.receive;
import com.sakill.receive.*;
import java.util.*;
public class Tool {
	private  HashMap<String,String> cmdparse=new HashMap<String,String>();
	private  String[] cmd;
	public Tool(String[] args){
		this.cmd=args;
	}
	
	public HashMap<String,String> cmdParse(){
		for(int i=0;i<this.cmd.length;i=i+2){
			String var=this.cmd[i].substring(2);
			this.cmdparse.put(var,this.cmd[i+1]);
		}
		return cmdparse;
	}
	
}
