package cn.hisin.content.service;

import java.util.List;

import cn.hisin.bena.EasyUITreeData;
import cn.hisin.utis.DataResult;

public interface ContentCategoryService {

	List<EasyUITreeData> getContentCatList(long parentId);
	DataResult addContentCategory(long parentId, String name);
	
}
