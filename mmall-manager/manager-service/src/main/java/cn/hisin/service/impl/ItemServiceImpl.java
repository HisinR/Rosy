package cn.hisin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hisin.bena.EasyUIDataGridResult;
import cn.hisin.mapper.TbItemDescMapper;
import cn.hisin.mapper.TbItemMapper;
import cn.hisin.pojo.TbItem;
import cn.hisin.pojo.TbItemDesc;
import cn.hisin.pojo.TbItemDescExample;
import cn.hisin.pojo.TbItemExample;
import cn.hisin.pojo.TbItemExample.Criteria;
import cn.hisin.service.ItemService;
import cn.hisin.utis.DataResult;
import cn.hisin.utis.IDUtils;

@Service
public class ItemServiceImpl implements ItemService {
	
	private static final Object TbItem = null;

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Override
	public TbItem getByItemID(Object obj) {
		
		TbItem item = tbItemMapper.selectByPrimaryKey((Long)obj);
		return item;
	}
	
	/**
	 * 商品分页
	 * @see cn.hisin.service.ItemService#getItemList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	/**
	 * 保存商品信息和商品描述
	 */
	@Override
	public DataResult saveItem(TbItem tbItem, String desc) {
		//生成id
		long Id = IDUtils.genItemId();
		//添加商品的信息
		tbItem.setId(Id);
		//商品状态，1-正常，2-下架，3-删除
		tbItem.setStatus((byte)1);
		//创建时间和更新时间
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		//插入数据
		tbItemMapper.insert(tbItem);
		//添加商品描述
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(Id);
		tbItemDescMapper.insert(tbItemDesc);
		return DataResult.ok();
	}

	@Override
	public DataResult updateItem(TbItem tbItem,String desc) {
		TbItemExample itemExample = new TbItemExample();
		//创建查询条件
		Criteria criteria = itemExample.createCriteria();
		criteria.andIdEqualTo(tbItem.getId());
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		//添加商品描述
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setUpdated(new Date());
		tbItemDesc.setItemId(tbItem.getId());
		try {
			int i = tbItemMapper.updateByExample(tbItem, itemExample);
			tbItemDescMapper.updateByPrimaryKey(tbItemDesc);
			if(i>0){
				return DataResult.ok();
			}else{
				return DataResult.build(300, "更新失败");
			}
		} catch (Exception e) {
			return DataResult.build(500, "添加出现异常");
		}
	}
	
	@Override
	public DataResult deleteItem(long ids) {
		TbItemDescExample example = new TbItemDescExample();
		cn.hisin.pojo.TbItemDescExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(ids);
		int i = tbItemMapper.deleteByPrimaryKey(ids);
				tbItemDescMapper.deleteByExample(example);
		if(i>0){
			return DataResult.ok();
		}else{
			return DataResult.build(300, "删除失败", ids);
		}
	}

}
