package cn.hisin.test;

import org.junit.Test;

import cn.hisin.utis.FastDFSUtis;

public class FastDFSTest {
	@Test
	public void fun() throws Exception{
		FastDFSUtis fd = new FastDFSUtis("F:/Tools/jee-neon/e3mmall/manager-web/src/main/resources/conf/client.conf");
		String file = fd.uploadFile("H:\\浏览器下载文件\\壁纸\\86KGvxN.jpg");
		System.out.println(file);
		
	}
}
