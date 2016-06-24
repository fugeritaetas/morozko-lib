package org.morozko.java.core.util.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1824211773585920573L;

	public String toString() {
		return this.getClass().getName()+"["+this.type+":"+this.result+"]";
	}
	
	public static ResultBean newResult( Map result ) {
		return new ResultBean( result, RESULT_TYPE_MAP );
	}
	
	public static ResultBean newResult( List result ) {
		return new ResultBean( result, RESULT_TYPE_LIST );
	}
	
	public static ResultBean newResult( Object result ) {
		return new ResultBean( result, RESULT_TYPE_OBJECT );
	}
	
	public static ResultBean newResult( PagedResult result ) {
		return new ResultBean( result, RESULT_TYPE_PAGED );
	}
	
	private ResultBean(Object result, int type) {
		super();
		this.result = result;
		this.type = type;
	}

	private Object result;
	
	private int type;
	
	public Object getResult() {
		return result;
	}

	public int getType() {
		return type;
	}
	
	public static final int RESULT_TYPE_OBJECT = 1;
	
	public static final int RESULT_TYPE_LIST = 2;
	
	public static final int RESULT_TYPE_MAP = 3;
	
	public static final int RESULT_TYPE_PAGED = 4;
	
	private ResultInfo info;

	public ResultInfo getInfo() {
		return info;
	}

	public void setInfo(ResultInfo info) {
		this.info = info;
	}
	
}
