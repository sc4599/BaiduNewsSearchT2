package com.linpeng.domain;

/**
 * 百度知道列表的bean类
 * @author linpeng123l
 *
 */
public class Question {
	private String title;
	private String details;
	private String date;
	private String url;
	private String agreeNum;
	private String answerName;
	private String replyNum;
	private boolean isDaRen;


	public Question() {
		super();
	}
	public Question(String title, String details, String date, String url,
					String agreeNum, String answerName, String replyNum, boolean isDaRen) {
		super();
		this.title = title;
		this.details = details;
		this.date = date;
		this.url = url;
		this.agreeNum = agreeNum;
		this.answerName = answerName;
		this.replyNum = replyNum;
		this.isDaRen = isDaRen;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAgreeNum() {
		return agreeNum;
	}
	public void setAgreeNum(String agreeNum) {
		this.agreeNum = agreeNum;
	}
	public String getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(String replyNum) {
		this.replyNum = replyNum;
	}
	public String getAnswerName() {
		return answerName;
	}
	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}
	public boolean isDaRen() {
		return isDaRen;
	}
	public void setDaRen(boolean isDaRen) {
		this.isDaRen = isDaRen;
	}
	@Override
	public String toString() {
		return "Question [title=" + title + ", details=" + details + ", date="
				+ date + ", url=" + url + ", agreeNum=" + agreeNum
				+ ", answerName=" + answerName + ", replyNum=" + replyNum
				+ ", isDaRen=" + isDaRen + "]";
	}


}
