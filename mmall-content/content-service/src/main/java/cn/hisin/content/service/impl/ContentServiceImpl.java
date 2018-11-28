package cn.hisin.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hisin.bena.EasyUIDataGridResult;
import cn.hisin.content.service.ContentService;
import cn.hisin.mapper.TbContentMapper;
import cn.hisin.pojo.TbContent;
import cn.hisin.pojo.TbContentExample;
import cn.hisin.pojo.TbContentExample.Criteria;
import cn.hisin.utis.DataResult;

/**
 * 商品内容服务
 * @author hisin
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	/**
	 * 商品内容添加
	 * @param  content
	 */
	@Override
	public DataResult addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		int insert = contentMapper.insert(content);
		
		if(insert>0) return DataResult.ok();
		
		return DataResult.build(500, "添加失败");
	}

	@Override
	public List<TbContent> getContentListByCid(long cid) {
		if(cid==0){
			return null;
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
	}
	
	/**
	 * 分页查询
	 * @author hisin
	 * @param page 
	 * @param rows 
	 */
	@Override
	public EasyUIDataGridResult queryContent(Long id,Integer page, Integer rows) {
		//设置分页
		PageHelper.startPage(page, rows);
		//查询数据
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(id);
		List<TbContent> list = contentMapper.selectByExample(example );
		//添加分页信息
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
