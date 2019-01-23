package com.test.qianyue.respo;

/**
 * Created by yezhangyuan on 2018-12-24.
 *
 * @author yezhangyuan
 */
public class OpenIdResponse {

	private String resp;
	private String msg;
	private Params params;

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public static class Params{

		private String  accessToken;
		private String  openId;

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}
	}
}
