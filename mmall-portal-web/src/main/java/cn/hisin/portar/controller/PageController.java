package cn.hisin.portar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hisin.content.service.ContentService;
import cn.hisin.pojo.TbContent;

@Controller
public class PageController {
	
	@Autowired
	private ContentService contentService;
	
	@Value("${cid}")
	private Long CID;
	@RequestMapping("/index")
	public String showIndex(Model model){
		List<TbContent> list = contentService.getContentListByCid(CID);
		model.addAttribute("ad1List",list);
		System.out.println(list.get(0));
		return "index";
	}
}
