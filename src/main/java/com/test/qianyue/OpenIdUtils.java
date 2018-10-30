package com.test.qianyue;

import com.sun.deploy.net.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yezhangyuan on 2018-10-29.
 *
 * @author yezhangyuan
 */
public class OpenIdUtils {

	public static String qianyue(String code){
		String url = "https://open.95516.com/open/access/1.0/token";
		MyHttpClient httpClient = new MyHttpClient(url);

		Map<String,String> map = new HashMap<String,String>()
		//json方式
		map.put("appId", "d0e121723ec0462c95f1c9141a5599d3");
		map.put("backendToken", "k+JZJN4bQleehJsAGDY7gQ==");
		map.put("code", "W1oTpVuQSLa3s47MM0SAbA==");
		map.put("grantType","authorization_code");
//		System.out.println();



		HttpResponse resp = client.execute(httpPost);
		if(resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he,"UTF-8");
		}
		System.out.println(respContent);
	}

}
