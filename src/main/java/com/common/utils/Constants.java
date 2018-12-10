package com.common.utils;


public interface Constants {

	/** session中放置当前用户的session key */
	public static final String CURRENT_ADMIN = "userName";

	public static final String YES = "Y";

	public static final String NO = "N";

	public static final String TOKEN_FORMAT = "TOKEN-%s";
	
	public static final Long TOKEN_EXPIRES = 600L;
	/*tooken过期时间*/
	public static final Long USER_TOKEN_EXPIRES = 1800L;
	
	public static final String USER_TOKEN = "userToken";
	
	// google登录状态
	public static final String GOOGLE_LOGIN = "googleLogin";
	
	public static final String REDIRECT = "redirect";
	
	public static final String PAY_TYPE_USDT = "USDT";
	
	public static final Byte ORDER_STATUS_WAIT = 1;
	
	public static final Byte ORDER_STATUS_PAYED = 2;
	
	public static final Byte ORDER_STATUS_FINISH = 3;
	
	public static final String WALLET_GET_ADDRESS_URL = "payment/getAddress";
	
	public static final String MEMBER_CODE_PAYMENT = "payment";

	//注册类型
	public static final  String REGISTER_MOBILE = "1";
	public static final  String REGISTER_EMAIL = "2";

	//交易转入转出
	public static final  String TRANSATION_OUT = "0";
	public static final  String TRANSATION_IN = "1";

}