package com.edi.dto;

import java.io.Serializable;

/**
 * aliyun sdk 相应信息。
 * 
 * @author lijie.ma
 * @since 1.0, May 19, 2014
 */
public class EdiResponse<T> implements Serializable {

	private static final long serialVersionUID = 7294025168689095197L;
	/**
	 * 错误码
	 */
	private String error;
	/**
	 * 错误描述
	 */
	private String errorDescription;

	private T body;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public boolean isSuccess() {
		return error==null||"".equals(error.trim());
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}


}
