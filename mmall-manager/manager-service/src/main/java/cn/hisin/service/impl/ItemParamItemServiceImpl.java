package cn.hisin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hisin.mapper.TbItemParamItemMapper;
import cn.hisin.pojo.TbItemParamExample.Criteria;
import cn.hisin.pojo.TbItemParamItem;
import cn.hisin.pojo.TbItemParamItemExample;
import cn.hisin.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	
	@Autowired
	private TbItemParamItemMapper  ipm;
	/**
	 * 根据itemID 查询
	 */
	@Override
	public TbItemParamItem getByID(long id) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		 example.createCriteria().andIdEqualTo(id);
		 List<TbItemParamItem> list = ipm.selectByExample(example);
		 if(list.size()>0 && list!=null){
			 return  list.get(0);
		 }else{
			 return null;
		 }
		
	}

}
