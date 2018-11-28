package cn.hisin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hisin.search.SearchItemService;
import cn.hisin.utis.DataResult;

@Controller
public class SearchItemController {
	
	@Autowired
	private SearchItemService serarchItem;
	
	@RequestMapping("/index/item/import")
	@ResponseBody
	public DataResult importItem(){
		return serarchItem.importAllItems();
		
	}
}
