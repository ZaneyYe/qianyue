package com.test.qianyue.controller;

import com.test.qianyue.*;
import com.test.qianyue.wxUtil.AesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
		String appId = Config.APPID;
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


	@RequestMapping(value = "/toWeixin.do",method = RequestMethod.GET)
	public String toWeiXin(HttpServletRequest request,Map<String,Object> dataMap) throws UnsupportedEncodingException {
		String appId = Config.WX_APPID;
		String nonceStr = Utils.createNonceStr();
		String ticket = "";
		try {
			ticket = Utils.getWxTicket(Config.WX_APPID,Config.WX_SCRECT);
		} catch (Exception e) {
			LOGGER.info("get ticket error");
			e.printStackTrace();
		}
		String url = "http://itgogo.top/qianyue/toWeixin.do";
		HashMap<String,String> map = new HashMap();
		String timestamp = String.valueOf(System.currentTimeMillis()/1000);
		//json方式
		map.put("timestamp", timestamp);
		map.put("noncestr", nonceStr);
		map.put("jsapi_ticket", ticket);
		map.put("url", url);
		String waitToSign = MyHttpClient.coverMap2String(map);
		LOGGER.info("wait to sign : {}", waitToSign);
		String sign = Sha1Utils.sha1(waitToSign.getBytes("UTF-8"));
		LOGGER.info("sign : {}", sign);

		dataMap.put("appId", appId);
		dataMap.put("timestamp", timestamp);
		dataMap.put("nonceStr", nonceStr);
		dataMap.put("signature", sign);
		return "/weixin";
	}

	/**
	 * 微信验证
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/verifyWx.do",method = RequestMethod.GET)
	@ResponseBody
	public String verifyWx(HttpServletRequest request) throws UnsupportedEncodingException, AesException {
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");
		String echostr  = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		LOGGER.info("req : timestamp={},nonce={},echostr={},signature={}", timestamp,nonce,echostr,signature);
		String waitToSign = "nonce="+nonce+"&timestamp="+timestamp+"&token="+Config.WX_TOKEN_CHECK;
		String sign = Sha1Utils.sha1(waitToSign.getBytes("UTF-8"));
		if(signature.equals(sign)){
			LOGGER.info("sign : {}", "驗簽失敗");
		}
		return echostr;
	}


}
