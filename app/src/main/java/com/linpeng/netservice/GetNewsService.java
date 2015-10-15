package com.linpeng.netservice;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import com.linpeng.domain.News;

public class GetNewsService extends NetService{

	/**
	 * 通过page返回新闻列表集合
	 * @param newsName 搜索时候使用，默认可以使用null作为传递值
	 * @param page 搜索新闻页码
	 * @return
	 */

	/**
	 *
	 "title": "EA出品《小黄人天堂》将正式上架苹果商店",
	 "url": "http://www.anqu.com/xin/news/112469.shtml",
	 "author": "安趣网",
	 "abs": "官网: 随着动画电影《小黄人大眼萌(Minions)》上个月在国内上映,这些可爱的黄色小人们在国内又火了一把。影",
	 "sortTime": "1444559737",
	 "publicTime": "1444559737",
	 "imgUrl": "http://t12.baidu.com/it/u=2960183702,3323585034&fm=82&s=3991F6B61C917DD04A000755030050F7&w=121&h=81&img.JPEG"
	 ==================================
	 "aid": "7",
	 "username": "lewki",
	 "title": "可怜可笑的逗B屌丝男",
	 "summary": "1、今天去相亲，女孩问我：“有车吗？”我说：“没有”又问：“有房吗？”我说：“也没有”最后她说：“你难道就是传说中的矮穷挫吗？”我灰溜溜的说：“不，我是现实中的！”\r\n2、女：不表白咱们还是好朋友。男：那 ...",
	 "dateline": "1439903438"
	 */
	public static List<News> getNewsByPage(int page){

		JSONObject[] jsonObjects = getJsonObjectsByUrl("http://www.qiangxie.cn/zz/index.php?class=list&name=zxxh&limit="+page);
		List<News> newss = new ArrayList<News>();
		try{
			if(jsonObjects!=null&&jsonObjects.length>0){
				for (JSONObject jsonObject : jsonObjects){
					News news = new News();
					news.setTitle(jsonObject.getString("title"));
					news.setUrl(jsonObject.getString("aid"));//笑话的具体ID
					news.setSource(jsonObject.getString("username"));//发布者
					news.setSummary(jsonObject.getString("summary"));
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.CHINA);
					news.setDate(dateFormat.format(new Date(jsonObject.getLong("dateline")*1000)));//时间戳
					newss.add(news);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return newss;
	}
	public static List<News> getNewsByPage(String URL,int page){

		JSONObject[] jsonObjects = getJsonObjectsByUrl(URL+page);
		List<News> newss = new ArrayList<News>();
		try{
			if(jsonObjects!=null&&jsonObjects.length>0){
				for (JSONObject jsonObject : jsonObjects){
					News news = new News();
					news.setTitle(jsonObject.getString("title"));
					news.setUrl(jsonObject.getString("aid"));//笑话的具体ID
					news.setSource(jsonObject.getString("username"));//发布者
					news.setSummary(jsonObject.getString("summary"));
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.CHINA);
					news.setDate(dateFormat.format(new Date(jsonObject.getLong("dateline")*1000)));//时间戳
					newss.add(news);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return newss;
	}

	public static String getJokyById(String aid){
		String jokyBody = getStringByUrl("http://www.qiangxie.cn/zz/index.php?class=body&name=zxxh&id=" + aid);
		Log.i("info",jokyBody);
		return jokyBody;
	}
}
