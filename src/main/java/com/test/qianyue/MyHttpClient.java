package com.test.qianyue;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
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
	static {
		objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}

	public MyHttpClient() {
	}

	public MyHttpClient(String url){
		this.url = url;
	}

	/**
	 * 请求返回信息
	 * @param param
	 * @return
	 */
	public String excuteResult(String param){
		HttpPost post = new HttpPost(url);
		// 接收参数json列表
		StringEntity entity = new StringEntity(param,"utf-8");//解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
//		entity.setContentType("application/x-www-form-urlencoded");
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
	public static <T extends QianyueResponse> T sendPost(String targetUrl, Map<String,String> map,String className) throws IOException, ClassNotFoundException {
		String sn = "";
		MyHttpClient client = new MyHttpClient(targetUrl);
		String params = objectMapper.writeValueAsString(map);
		LOGGER.info("请求参数：:{}",params);

		String res = client.excuteResult(params);
//		System.out.println(res);
		T tres = (T) objectMapper.readValue(res,Class.forName(className));
		return tres;
	}


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Map paramMap = new HashMap();
		paramMap.put("appId", "7ff6d728928c4d9f8f7e5a8ccd5e0d81");
		paramMap.put("secret", "0b40b177b27143f59cc293c055bb69e7");
		paramMap.put("nonceStr","5tL554bOi4565G57");

		String date = String.valueOf(System.currentTimeMillis()/1000);
		paramMap.put("timestamp",date);
		String waitSign = coverMap2String(paramMap);
		LOGGER.info("wait to sign: {}",waitSign);
		String sign = Utils.sha256(waitSign.getBytes());
		paramMap.put("signature",sign);

		QianyueResponse response = sendPost("https://open.95516.com/open/access/1.0/backendToken",paramMap,"com.test.qianyue.QianyueResponse");
		System.out.println(response.getResp());
	}
}
