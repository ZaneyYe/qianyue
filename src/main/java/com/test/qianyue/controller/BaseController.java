package com.test.qianyue.controller;

import com.test.qianyue.Config;
import com.test.qianyue.MyHttpClient;
import com.test.qianyue.Sha256Utils;
import com.test.qianyue.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yezhangyuan on 2018-12-20.
 *
 * @author yezhangyuan
 */
@Controller
@RequestMapping(value = "/")
public class BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


	@RequestMapping(value = "/index.do")
	public String toIndex(){
		return "index";
	}


	@RequestMapping(value = "/toBiopsy.do",method = RequestMethod.GET)
	public String toBiopsy(HttpServletRequest request,Map<String,Object> dataMap){
		String appId = "d43714e0246a435e87037f80495d2c6d";
		String timestamp = String.valueOf(System.currentTimeMillis()/1000);
		String nonceStr = Utils.createNonceStr();
		String frontToken = "";
		try {
			frontToken = Utils.getFrontToken(Config.APPID,Config.SCRECT);
		} catch (IOException e) {
			LOGGER.info("get frontToken error");
			e.printStackTrace();
		}
		String url = "http://47.98.179.66:8088/qianyue/toBiopsy.do";
		HashMap<String,String> map = new HashMap();
		//json方式
		map.put("appId", appId);
		map.put("timestamp", timestamp);
		map.put("nonceStr", nonceStr);
		map.put("frontToken", frontToken);
		map.put("url", url);
		String waitToSign = MyHttpClient.coverMap2String(map);
		LOGGER.info("wait to sign : {}", waitToSign);
		String sign = Sha256Utils.sha256(waitToSign.getBytes());
		LOGGER.info("sign : {}", sign);
		dataMap.put("appId", appId);
		dataMap.put("timestamp", timestamp);
		dataMap.put("nonceStr", nonceStr);
		dataMap.put("signature", sign);
		return "/biopsy";
	}

}
