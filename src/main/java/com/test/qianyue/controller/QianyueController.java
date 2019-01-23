package com.test.qianyue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.qianyue.Config;
import com.test.qianyue.MyHttpClient;
import com.test.qianyue.Utils;
import com.test.qianyue.entity.QianyueResult;
import com.test.qianyue.respo.OpenIdResponse;
import com.test.qianyue.respo.QianyueResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yezhangyuan on 2018-12-20.
 *
 * @author yezhangyuan
 */
@RequestMapping(value = "/")
@Controller
public class QianyueController {

	private static final Logger LOGGER = LoggerFactory.getLogger(QianyueController.class);

	private static final String STATE = "qianyue";

	private static final ObjectMapper MAPPER = new ObjectMapper();


	@RequestMapping(value = "/qianyue")
	public String toQianyue(HttpServletRequest request,Map<String,Object> dataMap){
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String errmsg = request.getParameter("errmsg");
		dataMap.put("code",code);
		dataMap.put("errmsg",errmsg);
		if(!STATE.equals(state)){
		    LOGGER.warn("伪造返回");
		    return "qianyue.do";
		}
		return "qianyue";
	}

	@RequestMapping(value = "/shenqingqianyue.do",method = RequestMethod.POST)
	@ResponseBody
	public static QianyueResult shenqingqianyue(String code) throws IOException {
		QianyueResult result = new QianyueResult();
		String backendToken = Utils.getBackendToken(Config.APPID, Config.SCRECT);
		String openIdRes = Utils.getOpenId(Config.APPID, code, backendToken);
		OpenIdResponse openIdResponse = MAPPER.readValue(openIdRes, OpenIdResponse.class);
		String openId = "";
		String accessToken = "";
		if(openIdResponse.getResp() == "00"){
			openId = openIdResponse.getParams().getOpenId();
			accessToken = openIdResponse.getParams().getAccessToken();
		}
		String planId = "";
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("appId",Config.APPID);
		paramMap.put("openId",openId);
		paramMap.put("accessToken",accessToken);
		paramMap.put("backendToken",backendToken);
		paramMap.put("planId","1d7a370d886f4133a2f366350116b2d0");
		paramMap.put("contractCode","123213");
		String shenqingUrl = "https://open.95516.com/open/access/1.0/contract.apply";
		String cIdRep = MyHttpClient.sendPost(shenqingUrl, paramMap);
		QianyueResponse qianyueResponse = MAPPER.readValue(cIdRep, QianyueResponse.class);
		result.setContractId(qianyueResponse.getParams().getContractId());
		return result;
	}









}
