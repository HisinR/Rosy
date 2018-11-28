package cn.hisin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hisin.bena.EasyUITreeData;
import cn.hisin.service.ItemCatService;



@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCat;
	
	/**
	 * 展示商品类目
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeData> showItemTree(
			@RequestParam(name="id",defaultValue="0")
			Long parentId){
		//调用服务
				List<EasyUITreeData> list = itemCat.getItemCatlist(parentId);
	
		return list;
	}
}
