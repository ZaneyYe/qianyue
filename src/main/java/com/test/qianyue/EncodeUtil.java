package com.test.qianyue;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class EncodeUtil {

	public static String rsaEncryptWithPk(String value, String publicKey) throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		byte[] pubilcKeyBytes = Hex.decodeHex(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicX509 = new X509EncodedKeySpec(pubilcKeyBytes);
		PublicKey pubKey = keyFactory.generatePublic(publicX509);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = Hex.encodeHexString(resultBytes);
		return result;
	}

	public static String rsaDecryptWithPk(String value, String publicKey) throws Exception {
		byte[] valueBytes = Hex.decodeHex(value);
		byte[] pubilcKeyBytes = Hex.decodeHex(publicKey);
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicX509 = new X509EncodedKeySpec(pubilcKeyBytes);
		PublicKey pubKey = keyf.generatePublic(publicX509);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = new String(resultBytes, "UTF-8");
		return result;
	}

	public static String rsaEncryptWithSk(String value, String privateKey) throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		byte[] privateKeyBytes = Hex.decodeHex(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, priKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = Hex.encodeHexString(resultBytes);
		return result;
	}

	public static String rsaDecryptWithSk(String value, String privateKey) throws Exception {
		byte[] valueBytes = Hex.decodeHex(value);
		byte[] privateKeyBytes = Hex.decodeHex(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = new String(resultBytes, "UTF-8");
		return result;
	}

	public static String sign(String value, String privateKey) throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		byte[] privateKeyBytes = Hex.decodeHex(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
		Signature signature = Signature.getInstance("SHA1WithRSA");
		signature.initSign(priKey);
		signature.update(valueBytes);
		byte[] resultBytes = signature.sign();
		String result = Hex.encodeHexString(resultBytes);
		return result;
	}

	public static boolean verify(String value, String signStr, String publicKey) throws Exception {
		byte[] valueBytes = Hex.decodeHex(value);
		byte[] publicKeyBytes = Hex.decodeHex(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicX509 = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey pubKey = keyFactory.generatePublic(publicX509);
		Signature signature = Signature.getInstance("SHA1WithRSA");
		signature.initVerify(pubKey);
		signature.update(valueBytes);
		byte[] resultBytes = Hex.decodeHex(signStr);
		boolean result = signature.verify(resultBytes);
		return result;
	}

	public static void generateRsaKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		byte[] publicKeyBytes = publicKey.getEncoded();
		byte[] privateKeyBytes = privateKey.getEncoded();
		System.out.println("私钥(Base64):" + Base64.encodeBase64String(privateKeyBytes));
		System.out.println("-----------------------------------------");
		System.out.println("公钥(Base64):" + Base64.encodeBase64String(publicKeyBytes));
		System.out.println("-----------------------------------------");
		System.out.println("私钥(十六进制):" + Hex.encodeHexString(privateKeyBytes));
		System.out.println("-----------------------------------------");
		System.out.println("公钥(十六进制):" + Hex.encodeHexString(publicKeyBytes));
		System.out.println("-----------------------------------------");
		System.out.println("私钥模:" + privateKey.getModulus());
		System.out.println("-----------------------------------------");
		System.out.println("公钥模:" + publicKey.getModulus());
		System.out.println("-----------------------------------------");
		System.out.println("公钥模(十六进制):" + publicKey.getModulus().toString(16));
		System.out.println("-----------------------------------------");
	}

	public static String desEncrypt(String value, String key) throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		byte[] keyBytes = Hex.decodeHex(key);
		SecretKey desKey = new SecretKeySpec(keyBytes, "DESede");
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, desKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String resuslt = Hex.encodeHexString(resultBytes);
		return resuslt;
	}

	public static String desDecrypt(String value, String key) throws Exception {
		byte[] valueBytes = Hex.decodeHex(value);
		byte[] keyBytes = Hex.decodeHex(key);
		SecretKey desKey = new SecretKeySpec(keyBytes, "DESede");
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, desKey);
		byte[] resultBytes = cipher.doFinal(valueBytes);
		String result = new String(resultBytes, "UTF-8");
		return result;
	}

	public static void main(String[] args) throws Exception {
//		String key = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
//		String aa = "hello";
//		String bb = desEncrypt(aa, key);
//		System.out.println(bb);
//		String cc = desDecrypt(bb, key);
//		System.out.println(cc);
		generateRsaKey();

	}


}
