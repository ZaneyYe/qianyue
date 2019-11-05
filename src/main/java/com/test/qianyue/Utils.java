package com.test.qianyue;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.qianyue.respo.BackendTokenResponse;
import com.test.qianyue.respo.FrontTokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by yezhangyuan on 2018-10-29.
 *
 * @author yezhangyuan
 */
public class Utils {

	private static final String accessToken_url = "https://open.95516.com/open/access/1.0/token";
//   private static final String accessToken_url = "https://openapi.unionpay.com/upapi/token";

	private static final String backendToken_url = "https://open.95516.com/open/access/1.0/backendToken";
//   private static final String backendToken_url = "https://openapi.unionpay.com/upapi/backendToken";

	private static final String frontToken_url  = "https://open.95516.com/open/access/1.0/frontToken";
//    private static final String frontToken_url  = "https://openapi.unionpay.com/upapi/upWallet/frontToken";

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}

	private static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static String getOpenId(String appId, String code, String backendToken) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		//json方式
		map.put("appId", appId);
		map.put("backendToken", backendToken);
		map.put("code", code);
		map.put("grantType", "authorization_code");
		return MyHttpClient.sendPost(accessToken_url, map);
	}

	/**
	 * @param appId
	 * @param secret
	 * @return
	 * @throws IOException
	 */
	public static String getBackendToken(String appId, String secret) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		//json方式
		map.put("appId", appId);
		map.put("secret", secret);
		map.put("nonceStr", createNonceStr());
		map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
		String waitToSign = MyHttpClient.coverMap2String(map);
		String sign = Sha256Utils.sha256(waitToSign.getBytes());
		map.put("signature", sign);
		String data = MyHttpClient.sendPost(backendToken_url, map);
		ObjectMapper mapper = new ObjectMapper();
		BackendTokenResponse backendTokenResponse = mapper.readValue(data, BackendTokenResponse.class);
		if ("00".equals(backendTokenResponse.getResp()) & StringUtils.isNotBlank(backendTokenResponse.getParams().getBackendToken())) {
			return backendTokenResponse.getParams().getBackendToken();
		}
		return "";
	}


	/**
	 * @param appId
	 * @param secret
	 * @return
	 * @throws IOException
	 */
	public static String getFrontToken(String appId, String secret) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		//json方式
		map.put("appId", appId);
		map.put("secret", secret);
		map.put("nonceStr", createNonceStr());
		map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
		String waitToSign = MyHttpClient.coverMap2String(map);
		String sign = Sha256Utils.sha256(waitToSign.getBytes());
		map.put("signature", sign);
		String data = MyHttpClient.sendPost(frontToken_url, map);
		ObjectMapper mapper = new ObjectMapper();
		FrontTokenResponse frontTokenResponse = mapper.readValue(data, FrontTokenResponse.class);
		if ("00".equals(frontTokenResponse.getResp()) & StringUtils.isNotBlank(frontTokenResponse.getParams().getFrontToken())) {
			return frontTokenResponse.getParams().getFrontToken();
		}
		return "";
	}


	/**
	 * @param appId
	 * @param secret
	 * @return
	 * @throws IOException
	 */
	public static String getFrontTokenNew(String appId, String secret) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		//json方式
		map.put("appId", appId);
		map.put("secret", secret);
		map.put("nonceStr", createNonceStr());
//		map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
		map.put("timestamp", String.valueOf(System.currentTimeMillis()));
		String waitToSign = MyHttpClient.coverMap2String(map);
		String sign = Sha256Utils.sha256(waitToSign.getBytes());
		map.put("signature", sign);
		String data = MyHttpClient.sendPost(frontToken_url, map);
//		JsonObject jsonObject =
				ObjectMapper mapper = new ObjectMapper();
		FrontTokenResponse frontTokenResponse = mapper.readValue(data, FrontTokenResponse.class);
		if ("0000".equals(frontTokenResponse.getResp())) {
			return frontTokenResponse.getParams().getFrontToken();
		}
		return "";
	}


	public static String createNonceStr() {
		StringBuilder sb = new StringBuilder();
		String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		int max = temp.length();
		Random rand = new Random();
		for (int i = 0; i < 16; i++) {
			int start = rand.nextInt(max);
			sb.append(temp.substring(start, start + 1));
		}
		return sb.toString();
	}


	public static void main(String[] args) throws IOException {
		String frontToken = getFrontToken("d43714e0246a435e87037f80495d2c6d", "b3b15e5dee9b479b9011b43ca47f753e");
		System.out.println(frontToken);
	}



}
