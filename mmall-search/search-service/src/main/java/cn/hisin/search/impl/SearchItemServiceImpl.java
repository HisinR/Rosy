package cn.hisin.search.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hisin.bena.SearchItem;
import cn.hisin.search.SearchItemService;
import cn.hisin.search.mapper.ItemMapper;
import cn.hisin.utis.DataResult;

@Service
public class SearchItemServiceImpl implements SearchItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public DataResult importAllItems() {
		try{
			List<SearchItem> itemList = itemMapper.getItemList();
			for (SearchItem searchItem : itemList) {
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());	
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
			//返回导入成功
			return DataResult.ok();
		}catch (Exception e) {
			e.printStackTrace();
			return DataResult.build(500, "数据导入时发生异常");
		}
		
	}

}
