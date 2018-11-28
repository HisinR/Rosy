package cn.hisin.search;

import cn.hisin.bena.SearchResult;

public interface SearchService {
	
	SearchResult search(String keyword,int page,int row);
}
