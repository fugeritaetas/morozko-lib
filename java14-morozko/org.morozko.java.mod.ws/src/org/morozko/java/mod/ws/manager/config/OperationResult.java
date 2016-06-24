package org.morozko.java.mod.ws.manager.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OperationResult implements Serializable {

	public static final String CODE_NOT_IMPLEMENTED = "98";
	public static final String MESSAGE_NOT_IMPLEMENTED = "NOT IMPLEMENTED";

	public static final String CODE_OK = "00";
	public static final String MESSAGE_OK = "SUCCESS";
	
	public static final String CODE_KO = "99";
	public static final String MESSAGE_KO = "FAILURE";	
	
	public static final OperationResult RESULT_NOT_IMPLEMENTED = new OperationResult( CODE_NOT_IMPLEMENTED , MESSAGE_NOT_IMPLEMENTED );
	
	public static OperationResult newResultOk() {
		return new OperationResult( CODE_OK , MESSAGE_OK );
	}
	
	public static OperationResult newResultKo() {
		return new OperationResult( CODE_KO , MESSAGE_KO );
	}	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7405675774797277188L;

	public OperationResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
		this.infoList = new ArrayList<InfoResult>();
	}

	public List<InfoResult> getInfoList() {
		return infoList;
	}

	private List<InfoResult> infoList;
	
	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
