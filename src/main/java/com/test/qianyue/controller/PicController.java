package com.test.qianyue.controller;

import com.test.qianyue.ImgUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by yezhangyuan on 2019-01-24.
 *
 * @author yezhangyuan
 */
@RequestMapping
public class PicController {

	@RequestMapping(value = "/getPic.do",method = RequestMethod.POST)
	@ResponseBody
	public String getPic(HttpServletRequest request,String data) throws IOException {
		Image image = ImgUtils.getImage(Base64Utils.decodeFromString(data));
		String fileName = UUID.randomUUID().toString().replaceAll("-","") + ".png";
		File file  = new File("/usr/local/blogImage/"+fileName);
		ImageIO.write((RenderedImage) image,"png",file);
		String visitUrl = "http://47.98.179.66/image/" + fileName;
		return visitUrl;
	}

}
