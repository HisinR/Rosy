package cn.hisin.content.service;

import java.util.List;

import cn.hisin.bena.EasyUIDataGridResult;
import cn.hisin.pojo.TbContent;
import cn.hisin.utis.DataResult;

public interface ContentService {

	DataResult addContent(TbContent content);
	List<TbContent> getContentListByCid(long cid);
	EasyUIDataGridResult queryContent(Long id ,Integer page, Integer rows);
}
