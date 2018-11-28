package cn.hisin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hisin.bena.EasyUIDataGridResult;
import cn.hisin.pojo.TbItem;
import cn.hisin.service.ItemService;
import cn.hisin.utis.DataResult;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService items;
	
	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
	@RequestMapping("/rest/page/item-edit/{id}")
	@ResponseBody
	public TbItem getByid(@PathVariable Long id){
		System.out.println(id);
		TbItem tbTitem = items.getByItemID(id);
		System.out.println(tbTitem);
		return tbTitem;
		
	}	
	@RequestMapping("/rest/page/item-edit")
	public String getJosn(@RequestParam("_") long id){
		return "item-edit" ;
	}
	
	/**
	 * 分页展示商品
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		 
		return items.getItemList(page, rows);
	}
	
	/**
	 * 添加商品
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public DataResult addItem(TbItem tbItem,String desc){
		return items.saveItem(tbItem, desc);
		
	}
	
	/**
	 * 删除商品
	 * @param obj
	 * @return
	 */
	
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public DataResult deleteItem(String ids){
		System.out.println("deletaItem="+ids);
		Long long1 = Long.valueOf(ids);
		return items.deleteItem(long1);
		
	}
	/**
	 * 更新商品
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public DataResult updataItem(TbItem tbItem,String desc){
		return items.updateItem(tbItem, desc);
		
	}
	
}