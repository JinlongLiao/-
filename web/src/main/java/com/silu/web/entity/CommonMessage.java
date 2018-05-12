package com.silu.web.entity;

/**
 * @author liaojl
 * 通用 消息 实体
 *
 */
public class CommonMessage {
	public boolean result;
	public String message;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
