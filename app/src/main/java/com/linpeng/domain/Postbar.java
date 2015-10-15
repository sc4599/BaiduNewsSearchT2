package com.linpeng.domain;

import java.util.List;

/**
 * 贴吧列表的bean类
 * @author linpeng123l
 *
 */
public class Postbar {
	private String title;
	private String url;
	private String details;
	private List<String> photoUrls;
	private String author;
	private String date;
	private String replyNum;


	public Postbar() {
		super();
	}
	public Postbar(String title, String url, String details,
				   List<String> photoUrls, String author, String date, String replyNum) {
		super();
		this.title = title;
		this.url = url;
		this.details = details;
		this.photoUrls = photoUrls;
		this.author = author;
		this.date = date;
		this.replyNum = replyNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public List<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(String replyNum) {
		this.replyNum = replyNum;
	}
	@Override
	public String toString() {
		return "Postbar [title=" + title + ", url=" + url + ", details="
				+ details + ", photoUrls=" + photoUrls + ", author=" + author
				+ ", date=" + date + ", replyNum=" + replyNum + "]";
	}

}
