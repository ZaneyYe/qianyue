import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yezhangyuan on 2019-01-08.
 *
 * @author yezhangyuan
 */
public class GenRSA {

	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair keyPair = keyGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put("RSAPrivateKey", privateKey);
		keyMap.put("RSAPublicKey", publicKey);
		return keyMap;
	}

	/**
	 *  私钥
	 * @param map
	 * @return
	 */
	public static String getPrivateKey(Map<String, Object> map){
		//PRIVATE_KEY
		Key key = (Key) map.get("RSAPrivateKey");
		return DatatypeConverter.printBase64Binary(key.getEncoded());
	}

	/**
	 * 公钥
	 * @param map
	 * @return
	 */
	public static String getPublicKey(Map<String, Object> map){
		//PUBLIC_KEY
		Key key = (Key) map.get("RSAPublicKey");
		return DatatypeConverter.printBase64Binary(key.getEncoded());
	}


	public static void main(String[] args) throws Exception {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair keyPair = keyGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		System.out.println("RSAPublicKey :: " +  DatatypeConverter.printBase64Binary(privateKey.getEncoded()));
		System.out.println("RSAPrivateKeyModule :: "+ privateKey.getModulus());
		System.out.println("RSAPrivateKeyExponent :: " +privateKey.getPrivateExponent());

		System.out.println("RSAPublicKey :: " +  DatatypeConverter.printBase64Binary(publicKey.getEncoded()));
		System.out.println("RSAPublicKeyModule :: "+ publicKey.getModulus());
		System.out.println("RSAPublicKeyExponent ::"  + publicKey.getPublicExponent());
	}



}
