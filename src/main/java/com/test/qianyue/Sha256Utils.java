package com.test.qianyue;

import java.security.MessageDigest;

/**
 * Created by yezhangyuan on 2018-12-24.
 *
 * @author yezhangyuan
 */
public class Sha256Utils {

	public static String sha256(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return bytesToHex(md.digest(data));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 将byte数组转换成16进制字符串
	 *
	 * @param bytes
	 * @return 16进制字符串
	 */
	public static String bytesToHex(byte[] bytes) {
		String hexArray = "0123456789abcdef";
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			int bi = b & 0xff;
			sb.append(hexArray.charAt(bi >> 4));
			sb.append(hexArray.charAt(bi & 0xf));
		}
		return sb.toString();
	}


	public static void main(String[] args){
		String str = "appId=ddaa1881e464459e8230122c578b7cf5&nonceStr=6uELgwFkDqIzaLGY&timestamp=1562039512&url=https://ebank.dgcb.com.cn:18086/index.html";
		System.out.println(sha256(str.getBytes()));
	}

}
