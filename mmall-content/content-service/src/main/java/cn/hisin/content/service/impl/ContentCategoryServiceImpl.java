	package cn.hisin.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.hisin.bena.EasyUITreeData;
import cn.hisin.content.service.ContentCategoryService;
import cn.hisin.mapper.TbContentCategoryMapper;
import cn.hisin.pojo.TbContentCategory;
import cn.hisin.pojo.TbContentCategoryExample;
import cn.hisin.pojo.TbContentCategoryExample.Criteria;
import cn.hisin.redis.JedisClient;
import cn.hisin.utis.DataResult;
import cn.hisin.utis.JsonUtils;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
	@Autowired
	private TbContentCategoryMapper contentCateMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${redis.category}")
	private String CATEGORY_LIST;
	
	@Override
	public List<EasyUITreeData> getContentCatList(long parentId) {
		try{
			//查询缓存
			String string = jedisClient.hget(CATEGORY_LIST, Long.toString(parentId));
			if(StringUtils.isNotBlank(string)){
				List<EasyUITreeData> esyList = JsonUtils.jsonToList(string, EasyUITreeData.class);
				return esyList;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		// 如果缓存中没有 查询数据库  开始装配条件
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbContentCategory> contentCateList = 
								contentCateMapper.selectByExample(example);
		
		//创建EasyUI集合
		 List<EasyUITreeData> EUIList = new ArrayList<>();
		 for (TbContentCategory cateList: contentCateList) {
			 EasyUITreeData EUI = new EasyUITreeData();
			 EUI.setId(cateList.getId());
			 EUI.setText(cateList.getName());
			 EUI.setState(cateList.getIsParent() ? "closed":"open");
			 EUIList.add(EUI);
		 }
		 //添加缓存
		 try{
			 jedisClient.hset(CATEGORY_LIST, Long.toString(parentId), JsonUtils.objectToJson(EUIList));

		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return EUIList;
	}

	@Override
	public DataResult addContentCategory(long parentId, String name) {
		TbContentCategory contentCate = new TbContentCategory();
		try{
			contentCate.setParentId(parentId);
			contentCate.setName(name);
			
			//1(正常),2(删除)
			contentCate.setStatus(1);
			//默认排序就是1
			contentCate.setSortOrder(1);
			//新添加的节点一定是叶子节点
			contentCate.setIsParent(false);
			contentCate.setCreated(new Date());
			contentCate.setUpdated(new Date());
		    contentCateMapper.insert(contentCate);
		    //同步缓存 添加之后 删除缓存中对应的缓存
		    jedisClient.hdel(CATEGORY_LIST, Long.toString(parentId));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		TbContentCategory category = contentCateMapper.selectByPrimaryKey(parentId);
		if(!category.getIsParent()){
			category.setIsParent(true);
			//更新到数数据库
			contentCateMapper.updateByPrimaryKey(category);
		}
		return DataResult.ok(contentCate);
	}

}
