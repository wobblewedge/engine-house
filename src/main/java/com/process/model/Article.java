package com.process.model;

public class Article {

	private String url;
	private String author;
	private String id;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getId() {
		return id;
	}
	
	public Article() {}
	
	public Article(String url, String author, String id) {
		super();
		this.url = url;
		this.author = author;
		this.id = id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
