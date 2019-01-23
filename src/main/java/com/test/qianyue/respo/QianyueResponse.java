package com.test.qianyue.respo;

/**
 * Created by yezhangyuan on 2018-12-25.
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
		private String contractCode;
		private String contractId;
		private String planId;
		private String openId;
		private String operateTime;

		public String getContractCode() {
			return contractCode;
		}

		public void setContractCode(String contractCode) {
			this.contractCode = contractCode;
		}

		public String getContractId() {
			return contractId;
		}

		public void setContractId(String contractId) {
			this.contractId = contractId;
		}

		public String getPlanId() {
			return planId;
		}

		public void setPlanId(String planId) {
			this.planId = planId;
		}

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public String getOperateTime() {
			return operateTime;
		}

		public void setOperateTime(String operateTime) {
			this.operateTime = operateTime;
		}
	}
}
