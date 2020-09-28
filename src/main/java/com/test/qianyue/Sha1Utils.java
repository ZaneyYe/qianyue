package com.test.qianyue;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by yezhangyuan on 2020-09-28.
 *
 * @author yezhangyuan
 */
public class Sha1Utils {

	/**
	 * sha1签名
	 * @param data
	 * @return
	 */
	public static String sha1(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.reset();
			md.update(data);
			return bytesToHex(md.digest());
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

	public static void main(String[] args) throws UnsupportedEncodingException {
//		String str1 = "jsapi_ticket=LIKLckvwlJT9cWIhEQTwfD3C7_kr9qjuIb4D1ogxhf54aWQ3c9CmTLc4piWaboti9Y1MP2a8cOiIzooSGiuISQ&noncestr=DOLi4je6teeMz5hs&timestamp=1601255402&url=http://47.98.179.66:8088/qianyue/toWeixin.do";
//		System.out.println(sha1(str1.getBytes("UTF-8")));
	}

}
