package cn.hisin.service;

import java.util.List;

import cn.hisin.bena.EasyUITreeData;

public interface ItemCatService {
	
	public List<EasyUITreeData> getItemCatlist(Long parentId);
}
