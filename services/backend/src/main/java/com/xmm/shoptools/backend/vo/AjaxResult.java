package com.xmm.shoptools.backend.vo;

/*
 * 负责ajax请求的响应消息封装
 */
public class AjaxResult {

	// 响应是否成功,默认值
	private boolean success = true;
	// 响应消息
	private String message = "操作成功~";
	// 错误码（前台可以根据错误码，进行特殊处理）
	private Integer errorCode = -99;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	// 成功时
	public AjaxResult() {

	}

	// 成功时可以自定义返回消息
	public AjaxResult(String message) {
		this.success = true;
		this.message = message;
	}

	// 失败时,返回错误码
	public AjaxResult(String message, Integer errorCode) {
		this.success = false;
		this.message = message;
		this.errorCode = errorCode;
	}

}
