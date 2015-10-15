package com.linpeng.netservice;


import android.util.Log;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.linpeng.netutil.NetUtil;

/**
 * 获取新闻服务类的父类，提供一些方法获取数据 
 * @author linpeng123l
 *
 */
public class NetService {

	public final static  String UESRAGENT_PHONE = "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A405 Safari/8536.25";

	/**
	 * 通过URL请求得到json数组
	 * @param url
	 * @return
	 */
	public static JSONObject[] getJsonObjectsByUrl(String url){
		try {
			String response = NetUtil.postAndGetDaet(url);
			Log.i("info",response);
			JSONArray jsonArray = new JSONArray(response);
			JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
			for(int i = 0;i<jsonArray.length();i++){
				jsonObjects[i] = jsonArray.getJSONObject(i);
			}
			return jsonObjects;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过URL请求得到json
	 * @param url
	 * @return
	 */
	public static JSONObject getJsonObjectByUrl(String url){
		try {
			String response = NetUtil.postAndGetDaet(url).replace("&quot;", "\'");
			JSONObject jsonObject = new JSONObject(response);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取 单条笑话的消息信息
	 * @param url
	 * @return
	 */
	public static String getStringByUrl(String url){
		try {
			String response = NetUtil.postAndGetDaet(url);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取 单条照片的消息信息
	 * @param url
	 * @return
	 */
	public static String getPhotoByUrl(String url){
		try {
			String response = NetUtil.postAndGetDaet(url);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过url请求得到document
	 * @param url
	 * @return
	 */
	public static Document getDocumentByUrl(String url){
		try {
			Document document = Jsoup.connect(url).userAgent(UESRAGENT_PHONE).get();
			return document;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
