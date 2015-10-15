package com.linpeng.netservice;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import com.linpeng.domain.Photo;

public class GetPhotoService extends NetService{

	/**
	 * 获取图片集合服务类
	 * @param page 页码
	 * @return 图片集合
	 */
	public static List<Photo> getPhotosByPage(int page){
		List<Photo> photos = new ArrayList<Photo>();
		String url = "http://www.qiangxie.cn/zz/index.php?class=list&name=gxtp&limit="+page;
		JSONObject[] jsonObjects = getJsonObjectsByUrl(url);
		try{
			for(int i=0;i<jsonObjects.length;i++){
				JSONObject object = jsonObjects[i];
				Photo photo = new Photo();
				photo.setAid(object.getString("aid"));
				photo.setUserName(object.getString("username"));
				photo.setTitle(object.getString("title"));
				Log.i("info","GetPhotoService中summary="+object.getString("summary"));
				photo.setSummary(object.getString("summary"));
				photo.setPhotoUrl(object.getString("pic"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
				photo.setDateline(dateFormat.format(new Date(object.getLong("dateline") * 1000)));//时间戳
				photos.add(photo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return photos;
	}

	public static String getphotoById(String aid){
		String photoBody = getStringByUrl("http://www.qiangxie.cn/zz/index.php?class=body&name=gxtp&id=" + aid);
		//处理开头符号
		photoBody=photoBody.substring(1);
		photoBody=photoBody.replace("\" />", "\" width=\"300px\"/>");
		Log.i("info", photoBody);
		return photoBody;
	}

	/**
	 *更具手机屏幕配置图片大小
	 * @param aid 当前访问条的详细ID
	 * @param px 屏幕宽度
	 * @return
	 */
	public static String getphotoById(String aid,int px){
		String photoBody = getStringByUrl("http://www.qiangxie.cn/zz/index.php?class=body&name=gxtp&id=" + aid);
		//处理开头符号
		photoBody=photoBody.substring(1);
		photoBody=photoBody.replace("\" />", "\" width=\"300px\"/>");
		Log.i("info", photoBody);
		return photoBody;
	}
}
