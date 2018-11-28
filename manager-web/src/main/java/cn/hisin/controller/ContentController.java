package cn.hisin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hisin.bena.EasyUIDataGridResult;
import cn.hisin.content.service.ContentService;
import cn.hisin.pojo.TbContent;
import cn.hisin.utis.DataResult;

@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public DataResult addContent(TbContent content){
		DataResult result = contentService.addContent(content);
		return result;
		
	}
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult queryContent(Long categoryId ,Integer page, Integer rows){
		EasyUIDataGridResult result = contentService.queryContent(categoryId,page, rows);
		return result;
		
	}
	
}
