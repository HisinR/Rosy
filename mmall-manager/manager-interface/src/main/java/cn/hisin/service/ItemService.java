package cn.hisin.service;

import cn.hisin.bena.EasyUIDataGridResult;
import cn.hisin.pojo.TbItem;
import cn.hisin.utis.DataResult;

public interface ItemService {
	public TbItem getByItemID(Object obj);
	public EasyUIDataGridResult getItemList(Integer page,Integer row);
	public DataResult saveItem(TbItem tbItem,String desc);
	public DataResult updateItem(TbItem tbItem,String desc);
	public DataResult deleteItem(long ids);
}
