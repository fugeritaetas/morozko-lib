package org.morozko.java.core.util.result;

import java.io.Serializable;

public class ResultInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535056453333738172L;

	private String resultCode;
	
	private String resultMessage;
	
	private Exception resultError;
	
	private Object info;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public ResultInfo() {
		
	}
	
	public ResultInfo(String resultCode, String resultMessage, Exception resultError, Object info) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.resultError = resultError;
		this.info = info;
	}

	public ResultInfo(String resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Exception getResultError() {
		return resultError;
	}

	public void setResultError(Exception resultError) {
		this.resultError = resultError;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
	
}
