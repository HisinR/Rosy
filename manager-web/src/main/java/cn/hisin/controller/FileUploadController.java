package cn.hisin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hisin.utis.FastDFSUtis;
import cn.hisin.utis.JsonUtils;


/*
 * 文件上传
 */
@Controller
public class FileUploadController {
	
	@Value("${image.upUrl}")
	private String imageUpUrl;
	
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	/**
	 * 图片上传
	 * @param uploadFile
	 * @return
	 */
	public  String  uploadFile(MultipartFile uploadFile){	
		try {
			//获取文件名称：getOriginalFilename();
			String fileName = uploadFile.getOriginalFilename();
			//截取图片后缀
			String extName = fileName.substring(fileName.indexOf(".")+1);
			//上传图片
			FastDFSUtis fd = new FastDFSUtis("classpath:conf/client.conf");
			String uplodFileURL = fd.uploadFile(uploadFile.getBytes(), extName);
			//拿到完整URL
			String url = imageUpUrl + uplodFileURL;
			Map<Object,Object> result = new HashMap<>();
			result.put("error",0);
			result.put("url",url);
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<Object,Object> result = new HashMap<>();
			result.put("error",1);
			result.put("message","上传图片失败");
			return JsonUtils.objectToJson(result);
		}
	}
}
