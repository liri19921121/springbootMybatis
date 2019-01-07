package com.aop.exception;


/**
 * 各种错误类型
 * 
 * @author 
 *
 */
public enum ApiResponMessage {
	
	//配置异常
	CONFIG_NOT_FOUND(20001,"config_not_found","配置文件缺少。"),
	FILE_EXISTS(20002,"file_exists","文件已经存在。"),

	;

	
	private final int type;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	private final String code;

	private ApiResponMessage(int type, String code, String message) {
		this.type = type;
		this.code = code;
		this.message = message;
	}
	
	public static ApiResponMessage getApiResponMessageByCode(int type){
		for(ApiResponMessage m : ApiResponMessage.values()){
		    if(type==m.getType()){
		        return m;
		      }
		}
	    return null;
	}
	 
}
