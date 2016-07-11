package com.edi.dto;

import java.io.Serializable;
import java.util.List;

/**
 * JqGrid通用返回
 * @author jjweng@ibenben.com
 *
 * @param <T>
 */
public class JqGrid<T> implements Serializable {

	private static final long serialVersionUID = 7294025168689095197L;

	private String errorCode;

	private String message;

	private List<T> rows;
	private Integer total;
	private Integer page;
	private Long records;
	private String jsonpCallback;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isSuccess() {
		return errorCode == null || "".equals(errorCode.trim());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public String getJsonpCallback() {
		return jsonpCallback;
	}

	public void setJsonpCallback(String jsonpCallback) {
		this.jsonpCallback = jsonpCallback;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
