package com.sakill.receive;
import com.sakill.receive.*;
import java.util.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.script.Script;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
public class EsOut {
	public static TransportClient client;
	public static IndexResponse response;
    public static BulkRequestBuilder sendbuffer;
    public static BulkProcessor bulkProcessor;
    public static int sendnum;
	public static void init() throws UnknownHostException{	
		Settings settings = Settings.builder()
		        .put("cluster.name", "elasticsearch").build();
			 client = new PreBuiltTransportClient(settings)
			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.241.135"), 9300));
			 sendbuffer=client.prepareBulk();
	         sendnum=1000;
	         bulkProcessor=BulkProcessor.builder(client,new BulkProcessor.Listener() {  //Á¬½Ó
	                public void beforeBulk(long l, BulkRequest bulkRequest) {

	                }
	                public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
	                    if(bulkResponse.hasFailures()){
	                        System.out.println("excute bulkprocessor failure");
	                        System.exit(-3);
	                    }
	                }
	                public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {

	                }
	            }).setBulkActions(sendnum).setConcurrentRequests(0).build();
	}
	
	public static void out(HashMap <String,String> vars){
//		         response=client
//    		    .prepareIndex("twitter", "tweet").setSource(vars)
//    		    .execute().actionGet();
		IndexRequest indexRequest=new IndexRequest("log","log").source(vars);
        bulkProcessor.add(indexRequest);
	}
	
	public static void close(){
		client.close();
	}
}
