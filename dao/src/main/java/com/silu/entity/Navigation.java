/**
 * 
 */
package com.silu.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * com.silu.entity
 * 
 * @author 廖金龙 2018年4月22日下午6:49:16 导航条 实体
 */
@Entity
public class Navigation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private int order;
	@Column
	private String title;
	@Column
	private String context;
	@Column
	private String url;
	@Column
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws UnsupportedEncodingException {
		this.title = URLEncoder.encode(title, "utf-8");
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) throws UnsupportedEncodingException {
		this.context = URLEncoder.encode(context, "utf-8");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) throws UnsupportedEncodingException {
		this.url = URLEncoder.encode(url, "utf-8");
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) throws UnsupportedEncodingException {
		this.desc = URLEncoder.encode(desc, "utf-8");
	}

}
