package cn.hisin.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hisin.bena.SearchItem;
import cn.hisin.bena.SearchResult;

@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 从索引库查询数据
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	public SearchResult search(SolrQuery query) throws Exception{
		QueryResponse response = solrServer.query(query);
		//取查询结果。
		SolrDocumentList results = response.getResults();
		//取总记录数
		long numFound = results.getNumFound();
		SearchResult searchResult = new SearchResult();
		//设置总记录数
		searchResult.setRecordCount(numFound);
		//取高亮
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		//查询结果集合
		List<SearchItem> itemList = new ArrayList<>();
		for (SolrDocument sdoc : results) {
			SearchItem item = new SearchItem();
			item.setId((String) sdoc.get("id"));
			item.setCategory_name((String) sdoc.get("item_category_name"));
			item.setImage((String) sdoc.get("item_image"));
			item.setPrice((long) sdoc.get("item_price"));
			item.setSell_point((String) sdoc.get("item_sell_point"));
			//取高亮显示
			List<String> list = highlighting.get(sdoc.get("id")).get("item_title");
			String title="";
		    if(list !=null && list.size()>0){
		    	title=list.get(0);
		    }else{
		    	title=(String)sdoc.get("item_title");
		    }
		    item.setTitle(title);
		    itemList.add(item);	
		}
		searchResult.setItemList(itemList);
		return searchResult;
		
	}
}
