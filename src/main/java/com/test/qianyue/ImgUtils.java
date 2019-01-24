package com.test.qianyue;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yezhangyuan on 2018-10-23.
 *
 * @author yezhangyuan
 */
public class ImgUtils {

	/**
	 * @param p_Str Base64编码的字符窜
	 * @return byte[] 类型的照片信息
	 * @throws IOException
	 */
	public static byte[] fromBase64(String p_Str) throws IOException
	{
		byte[] byteBuffer = new BASE64Decoder().decodeBuffer(p_Str);
		return byteBuffer;
	}
	/**
	 * @param bytes byte[] 类型的照片信息
	 * @return Java Image对象。可以直接在java程序中绘制到UI界面
	 */
	public static Image getImage(byte[] bytes) {
		Image img = null;
		try {
			InputStream isPhoto = new ByteArrayInputStream(bytes);
			img = ImageIO. read(isPhoto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

}

