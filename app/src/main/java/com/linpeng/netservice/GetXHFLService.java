package com.linpeng.netservice;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.linpeng.domain.Postbar;
import com.linpeng.util.FileUtil;
import com.linpeng.util.StringUtil;

public class GetXHFLService extends NetService{

	private final static String postbarUrl = "http://tieba.baidu.com/f?ie=utf-8&mo_device=1";

	/**
	 * 获取笑话分类列表集合(从呛蟹笑话获取数据)
	 * 注：由于未找到json数据接口，此处直接解析html代码
	 * @param page 页码
	 * @return 集合
	 */
	public static List<Postbar> getPostbarsByPage(String postBarName,int page){
		List<Postbar> postbars = new ArrayList<Postbar>();
		try{
			Document document = getDocumentByUrl(postbarUrl+"&kw=&pn="+page*50);
		}catch(Exception e){
			FileUtil.addFile(e.getStackTrace().toString());
			e.printStackTrace();
		}
		return postbars;
	}

}
