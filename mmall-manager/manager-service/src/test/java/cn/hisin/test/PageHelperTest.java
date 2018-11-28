package cn.hisin.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hisin.mapper.TbItemMapper;
import cn.hisin.pojo.TbItem;
import cn.hisin.pojo.TbItemExample;
public class PageHelperTest {
	/**
	 * 测试分页
	 */
	@Test
	public void fun(){
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-config/springConfig-dao.xml");
		TbItemMapper item =(TbItemMapper) app.getBean(TbItemMapper.class);
		PageHelper.startPage(1, 10);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = item.selectByExample(example);
		//取出分页信息
		PageInfo<TbItem> pa = new PageInfo<>(list);
		System.out.println(pa.getTotal());
		System.out.println(pa.getPages());
		System.out.println(pa.getPageSize());
		
		
	}
}
