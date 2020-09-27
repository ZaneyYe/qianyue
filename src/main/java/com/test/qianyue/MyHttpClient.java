package com.test.qianyue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yezhangyuan on 2018-09-27.
 *
 * @author yezhangyuan
 */
public class MyHttpClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyHttpClient.class);

	private String url;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public MyHttpClient() {
	}

	public MyHttpClient(String url){
		this.url = url;
	}

	/**
	 * 请求返回信息  http json
	 * @param param
	 * @return
	 */
	public String excuteResult(String param){
		HttpPost post = new HttpPost(url);
		// 接收参数json列表
		StringEntity entity = new StringEntity(param,"utf-8");//解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resData = "";
		try {
			CloseableHttpResponse result = httpclient.execute(post);
//			 请求结束，返回结果
			  resData = EntityUtils.toString(result.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resData;
	}


	/**
	 * 将Map中的数据转换成key1=value1&key2=value2的形式 不包含签名域signature
	 *
	 * @param data
	 *            待拼接的Map数据
	 * @return 拼接好后的字符串
	 */
	public static String coverMap2String(Map<String, String> data) {
		TreeMap<String, String> tree = new TreeMap<String, String>();
		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> en = it.next();
			if (SDKConstants.param_signature.equals(en.getKey().trim())) {
				continue;
			}
			tree.put(en.getKey(), en.getValue());
		}
		it = tree.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry<String, String> en = it.next();
			sf.append(en.getKey() + SDKConstants.EQUAL + en.getValue()
					+ SDKConstants.AMPERSAND);
		}
		return sf.substring(0, sf.length() - 1);
	}


	/**
	 * 发送請求
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static String sendPost(String targetUrl, Map<String,String> map) throws IOException {
		String sn = "";
		MyHttpClient client = new MyHttpClient(targetUrl);
		String params = objectMapper.writeValueAsString(map);
		System.out.println("请求参数：:" + params);
		String response = client.excuteResult(params);
		LOGGER.info("sentclient response: {}",response);
		return response;
	}


	/**
	 * get请求
	 * @param targetUrl
	 * @return
	 * @throws IOException
	 */
	public static String sendGet(String targetUrl){
		HttpGet get = new HttpGet(targetUrl);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String response = "";
		try {
			CloseableHttpResponse result = httpclient.execute(get);
//			 请求结束，返回结果
			response = EntityUtils.toString(result.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info("sentclient response: {}",response);
		return response;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(sendGet("https://www.baidu.com"));
	}


}
