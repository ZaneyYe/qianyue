package com.test.qianyue;

/**
 * Created by yezhangyuan on 2018-10-29.
 *
 * @author yezhangyuan
 */
public class QianyueResponse {

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
		private String  backendToken;
		private String  contractId;

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

		public String getBackendToken() {
			return backendToken;
		}

		public void setBackendToken(String backendToken) {
			this.backendToken = backendToken;
		}

		public String getContractId() {
			return contractId;
		}

		public void setContractId(String contractId) {
			this.contractId = contractId;
		}

		public static String getFileByName(String fieldName){
//			Field field = this.get.getField(fieldName);
			return "";
		}
	}

}
