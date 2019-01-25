package com.test.qianyue.controller;

import com.test.qianyue.ImgUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping(value = "/")
public class PicController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PicController.class);

	@RequestMapping(value = "/getPic.do",method = RequestMethod.POST)
	@ResponseBody
	public String getPic(HttpServletRequest request,String data){
		//如果有换行，转一下
		data = data.replaceAll("\n","");
		if(StringUtils.isNotBlank(data)){
			LOGGER.info("get data ok,{}",data);
		}
		Image image = ImgUtils.getImage(Base64Utils.decodeFromString(data));
		String fileName = UUID.randomUUID().toString().replaceAll("-","") + ".png";
		LOGGER.info("fileName:{}", fileName);
		File file  = new File("/usr/local/blogImage/"+ fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				LOGGER.info("创建文件失败");
				e.printStackTrace();
			}
		}
		LOGGER.info("开始渲染图片");
		try {
			ImageIO.write((RenderedImage) image,"png",file);
		} catch (IOException e) {
			LOGGER.info("异常了");
			e.printStackTrace();
		}
		LOGGER.info("save pic: {}",file.getName());
		String visitUrl = "http://47.98.179.66/image/" + fileName;
		LOGGER.info("visitUrl:{}",visitUrl);
		return visitUrl;
	}



}
