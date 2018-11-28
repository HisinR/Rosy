package cn.hisin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hisin.bena.EasyUITreeData;
import cn.hisin.content.service.ContentCategoryService;
import cn.hisin.utis.DataResult;

/**
 * 内容分类管理Controller
 * @version 1.0
 */
@Controller
public class ContentCatController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeData> getContentCatList(
			@RequestParam(name="id", defaultValue="0")Long parentId) {
		List<EasyUITreeData> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}
	
	/**
	 * 添加分类节点
	 */

	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public DataResult createContentCategory(Long parentId, String name) {
		//调用服务添加节点
		DataResult Result = contentCategoryService.addContentCategory(parentId, name);
		return Result;
	}

	
}
