package cn.hisin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hisin.pojo.TbItemDesc;
import cn.hisin.service.ItemDescService;
import cn.hisin.utis.DataResult;



@Controller
public class ItemDescController {
	
	@Autowired
	private ItemDescService itds;
	
	@RequestMapping("/rest/item/query/item/desc/{id}")
	@ResponseBody
	public DataResult showDesc(@PathVariable Long id){
		//System.out.println(itds.getByID(id));
		return itds.getByID(id);
		
	}
}
