/**
 * 
 */
package com.silu.web.entity;

/**
 * com.silu.entity
 * 
 * @author 廖金龙 2018年4月22日下午6:49:16 导航条 实体
 */

public class Navigation {

	private int id;
	private int order;
	private String title;
	private String context;
	private String url;
	private String desc;
	// 20180430 新增 url target default ——NEW
	private String target;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
