package cn.hisin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hisin.pojo.TbItemParamItem;
import cn.hisin.service.ItemParamItemService;



@Controller
public class ItemParamItemController {
	
	@Autowired
	private ItemParamItemService tpis;
	
	@RequestMapping("/rest/item/param/item/query/{id}")
	@ResponseBody
	public  TbItemParamItem showParamTtem(@PathVariable Long id){
		System.out.println("TbItemParamItem() ="+id);
		return tpis.getByID(id);
		
	}
}
