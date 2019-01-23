package com.test.qianyue.respo;

/**
 * Created by yezhangyuan on 2018-10-29.
 *
 * @author yezhangyuan
 */
public class FrontTokenResponse {

	private String cmd;
	private String resp;
	private String msg;
	private Params params;
	private String v;

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

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public static class Params{
		private String  frontToken;
		private String  expiresIn;

		public String getFrontToken() {
			return frontToken;
		}

		public void setFrontToken(String frontToken) {
			this.frontToken = frontToken;
		}

		public String getExpiresIn() {
			return expiresIn;
		}

		public void setExpiresIn(String expiresIn) {
			this.expiresIn = expiresIn;
		}
	}

}
