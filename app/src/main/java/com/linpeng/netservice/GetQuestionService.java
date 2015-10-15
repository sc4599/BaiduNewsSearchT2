package com.linpeng.netservice;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.linpeng.domain.Question;

public class GetQuestionService extends NetService{

	/**
	 * 获取百度知道列表服务类
	 * @param questionName 问题名称
	 * @param page 页码
	 * @return
	 */
	public static List<Question> getQuestionsByPage(String questionName,int page){
		List<Question> questions = new ArrayList<Question>();
		String url = "http://wapiknow.baidu.com/msearch/ajax/getsearchlist?word="+questionName+"&pn="+10*page;
		JSONObject jsonObject = getJsonObjectByUrl(url);
		if(jsonObject!=null){
			try {
				JSONArray jsonArray = (JSONArray) ((JSONObject)jsonObject.get("data")).get("entry");
				for(int i=0;i<jsonArray.length();i++){
					Question question = new Question();
					JSONObject object = jsonArray.getJSONObject(i);
					question.setTitle(object.getString("title"));
					question.setDetails(object.getString("rcontent"));
					question.setDate(object.getString("time"));
					question.setDaRen(object.getInt("isDaRen")==1);
					question.setAnswerName(object.getString("aname"));
					question.setAgreeNum(object.getInt("agreeNum")+"");
					questions.add(question);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return questions;
	}

}
