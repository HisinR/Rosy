package cn.hisin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hisin.bena.EasyUITreeData;
import cn.hisin.mapper.TbItemCatMapper;
import cn.hisin.pojo.TbItemCat;
import cn.hisin.pojo.TbItemCatExample;
import cn.hisin.pojo.TbItemCatExample.Criteria;
import cn.hisin.service.ItemCatService;

/**
 * 商品类目
 * @author SYSTEM
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	TbItemCatMapper itemCatMapper;
	/**
	 * 获取商品类目列表
	 * 根据parentId
	 */
	@Override
	public List<EasyUITreeData> getItemCatlist(Long  parentId) {
		//创建条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//添加条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EasyUITreeData> resultList = new ArrayList<>();
		for (TbItemCat TbItemCat : list) {
			EasyUITreeData eaResult = new EasyUITreeData();
			eaResult.setId(TbItemCat.getId());
			eaResult.setText(TbItemCat.getName());
			eaResult.setState(TbItemCat.getIsParent()?"closed":"open");
			resultList.add(eaResult);
		}
		return resultList;
		
	}

}
