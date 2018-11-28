package cn.hisin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hisin.mapper.TbItemDescMapper;
import cn.hisin.pojo.TbItemDesc;
import cn.hisin.service.ItemDescService;
import cn.hisin.utis.DataResult;

@Service
public class ItemDescServiceImpl implements ItemDescService {
	
	@Autowired
	private TbItemDescMapper tid;
	@Override
	public DataResult getByID(long id) {
		TbItemDesc itemDesc = tid.selectByPrimaryKey(id);
		if(! "".equals(itemDesc) && null!=itemDesc){
			return new DataResult(itemDesc.getItemDesc());
		}
		return DataResult.build(300, "回显失败");
	}

}
