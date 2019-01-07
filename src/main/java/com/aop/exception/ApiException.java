package com.aop.exception;

public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5401922877961547792L;
	private int type;
	private String code;
	private String message;
	private String innerException;
	private Object[] objects;
	private Exception e;

	public String getInnerException() {
		return innerException;
	}

	public void setInnerException(String innerException) {
		this.innerException = innerException;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 */

//	public ApiException() {
//	}

	public ApiException(int type, String innerMessage) {
		this.type = type;
		ApiResponMessage m = ApiResponMessage.getApiResponMessageByCode(type);
		this.code = m==null?null:m.getCode();
		this.message = m==null?null:m.getMessage();
		this.innerException = innerMessage;
	}
	public ApiException(int type, String innerMessage,Object[] objects) {
		this.type = type;
		ApiResponMessage m = ApiResponMessage.getApiResponMessageByCode(type);
		this.code = m==null?null:m.getCode();
		this.message = m==null?null:m.getMessage();
		this.innerException = innerMessage;
		this.objects = objects;
	}
	public ApiException(int type, Exception e) {
		this.type = type;
		ApiResponMessage m = ApiResponMessage.getApiResponMessageByCode(type);
		this.code = m==null?null:m.getCode();
		this.message = m==null?null:m.getMessage();
		this.setE(e);
	}
	public ApiException(WebResponMessage responseMessage, Exception e) {
		this.type = responseMessage.getType();
		this.code = responseMessage.getCode();
		this.message = responseMessage.getMessage();
		this.setE(e);
	}
	public ApiException(WebResponMessage responseMessage,Object s, Exception e) {
		this.type = responseMessage.getType();
		this.code = responseMessage.getCode();
		this.message = responseMessage.getMessage()+s;
		this.setE(e);
	}
	public ApiException(int type, Exception e,Object[] objects) {
		this.type = type;
		ApiResponMessage m = ApiResponMessage.getApiResponMessageByCode(type);
		this.code = m==null?null:m.getCode();
		this.message = m==null?null:m.getMessage();
		this.objects = objects;
		this.setE(e);
	}
	
	public ApiException(String message) {
		super();
		this.message = message;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}

}
