package com.aop.exception;


/**
 * 各种错误类型
 * 
 * @author 
 *
 */
public enum WebResponMessage {
	//校验
	NULL_PARAMETER(100000,"null_parameter","请检查参数，参数不能为空"),
	CODE_MISMATCHING(100001,"code_mismatching","验证码不匹配"),
	CODE_ERROR(100002,"code_error","验证码错误"),
	EMAIL_EXIST(100003,"code_error","邮箱已存在"),
	PWD_MISMATCHING(100004,"password_mismatching","两次密码不匹配"),
	PWD_ERROR(100005,"password_error","密码错误"),
	EMAIL_FORMAT_MISMATCHING(100006,"email_format_mismatching","邮箱格式有误"),
	MOBILE_FORMAT_MISMATCHING(100007,"mobile_format_mismatching","手机格式有误"),
	MOBILE_EXIST(100008,"mobile_exist","手机号码已存在"),
	GOOGLE_CODE_ERROR(100009,"google_code_error","google 验证码错误"),
	MEMBER_CODE_IS_STARTWITHNUMBER_ERROR(100010,"memberCode_is_startwith_number","用户名不能以数字开头"),
	EAMIL_NOT_EXIST(100011,"email_not_exist","邮箱不存在"),
    EAMIL_DELPWD_EXIST(100012,"email_delpwd_exist","交易密码已存在"),
    EAMIL_DELPWD_NOT_EXIST(100013,"email_delpwd_not_exist","交易密码不存在"),
	MOBILE_NOT_EXIST(100014,"mobile_not_exist","手机不存在"),
	IMG_MAX_ERROR(100015,"img_max_error","图片最多三张"),
	WORK_ORDER_END_ERROR(100016,"work_order_end_error","工单已结束"),
	DEAL_PWD_ERROR(100017,"deal_password_error","安全密码错误"),
	BUY_OVERFLOW_ERROR(100018,"buy_overflow_error","购买数量超过剩余数量"),
	SUFFICIENT_FUNDS_ERROR(100019,"sufficient_funds_error","余额不足"),
	RAISE_TIME_OVER_ERROR(100020,"raise_time_over_eerror","众筹已结束"),
	DELPWD_NOT_EXIST(100021,"delpwd_not_exist","请设置交易密码"),
	RAISE_TIME_NOT_BEGIN_ERROR(100022,"raise_time_not_begin_eerror","众筹未开始"),
	RAISE_CODE_EXIST(100023,"raise_code_exist","众筹标的不存在"),
	RAISE_CODENAME_EXIST(100024,"raise_code_name_exist","币种不存在"),
	USER_REAL_NAME_NOT(100025,"user_real_name_not","用户未实名认证"),
	TRAN_DEAL_PUBLISH_NUM_OVER(100026,"tran_deal_publish_num_over","一天之内只能发布一次"),
	TRAN_DEAL_PUBLISH_HAVE_WAY(100027,"tran_deal_publish_have_way","有未完成的广告"),
	TRAN_DEAL_SELECT_PLAY_WAY(100028,"tran_deal_select_play_way","请选择支付方式"),
	TRAN_NUMBER_ERROR(100029,"tran_number_error","委托数据错误"),
	TRAN_DEAL_NOT_EXIST(100030,"tran_deal_not_exist","广告不存在"),
	TRAN_DEAL_ORDER_TIMEOUT(100031,"tran_deal_order_timeout","订单超时"),
	TRAN_DEAL_ORDER_ERROR(100032,"tran_deal_order_error","订单异常，请联系管理人员"),
	PWD_TOO_SHORT(100033,"pwd_too_short","密码太短"),
	TRAN_PUBLISH_NOT_DEAL(100034,"tran_publish_not_deal","您为区域父节点，不能购买此广告"),
	TRAN_DEAL_NOT_PUBLISH(100035,"tran_deal_not_publish","您为区域父节点，不能发布广告"),
	TRAN_NOT_IN_AREA(100036,"tran_not_in_area","不符合分区规则不能购买"),
	MOBILE_MAIL_NOT_ALL_NULL(100037,"mobile_mail_not_all_null","邮箱和手机号不能都为空"),
	MOBILE_AREACODE_NOT_NULL(100038,"mobile_areacode_not_null","选手机区号不能为空"),

	DEAL_PAY_WAY_EXIST(100039,"deal_pay_way_exist","用户交易方式不存在"),
	BUY_NUMBER_ERROR(100040,"buy_number_error","自由子链数量只能为正整数"),
	NOT_BUY_SELF(100041,"not_buy_self","不能认购自己的广告"),
	PUBLISH_IS_SHELVES(100042,"publish_is_shelves","广告已下架"),
	TYPE_IS_EXIST(100043,"type_is_exist","类型不存在"),
	PUBLISH_TYPE_ERROR(100045,"publish_type_error","广告类型错误"),
	PUBLISH_HAVE_NOTEND_ORDER(100046,"publish_have_notend_order","有未完成订单"),
	PUBLISH_STATE_ERROR(100047,"publish_state_error","状态错误"),
	ORDER_CANT_CANCEL(100048,"order_cant_cancel","无法取消"),
	ORDER_NOT_EXIST(100049,"order_not_exist","订单不存在"),
	ORDER_IS_PAY(100050,"order_is_pay","订单已支付"),
	ILLEGAL_OPERATION(100051,"illegal_operation","操作违法"),
	PAY_WAY_ERROR(100052,"pay_way_error","支付方式错误"),
	NOT_CLAIM_AGAINST_ORDER(100053,"no_claim_against_order","无权申诉此订单"),
	CURRENT_SURPLUS(100054,"current_surplus","当前剩余数量不足"),
	SYSTEM_IS_BUSY(100055,"system_is_busy","系统忙请稍后重试"),
	PWD_FORMAT_ERROR(100056,"pwd_format_error","密码格式必须包含字母数字"),
	DEAL_PWD_NOT_AGREE(100057,"pwd_format_error","安全密码不匹配"),
	PHONE_NOT_BELONG_TO_USER(100058,"phone_not_belong_to_user","该手机号不是该用户所绑定的"),
	EMAIL_NOT_BELONG_TO_USER(100059,"email_not_belong_to_user","该邮箱不是该用户所绑定的"),
	PAY_WAY_EXIST(100060,"pay_way_exist","请添加支付方式"),



	//用户
	USER_MEMBER_CODE_EXIST(200001,"user_memberCode_exist","用户名已存在"),
	USER_PWD_ERROR(200002,"user_password_error","用户名或密码错误"),
	USER_INVALID(200003,"user_invalid","用户未登录或者token过期"),
	USER_MEMBER_CODE_ISCHINESE(200004,"user_memberCode_isChinese","用户名不可以是中文"),
//	USER_API_EXIST(200005,"user_api_exist","用户api 已存在"),
//	USER_API_ISNULL(200006,"user_api_is_null","用户api 不存在"),
	USER_ACCOUNT_IS_LOCK(200007,"user_account_is_lock","用户账户余额已锁定"),
	USER_ACCOUNT_BALANCE_INSUFFICIENT(200008,"user_account_balance_insufficient ","用户账户余额不足"),
//	USER_API_CALLBACK_ERROR(200009,"user_api_callback_error ","设置回调地址错误"),
//	USER_CREATE_API_ERROR(200010,"user_create_api_error ","创建API地址错误"),
//	USER_INIT_MEMBERECODE_ERROR(200011,"user_init_membercode_error ","商户初始化错误"),
	USER_UNBIND_MOBILE_ERROR(200012,"user_unbind_mobile_error ","解绑手机失败"),
	USER_BIND_MOBILE_ERROR(200013,"user_unbind_mobile_error ","绑定手机失败"),
	USER_RECOMMENDID_ERROR(200014,"user_recommend_error ","推荐人不存在"),
    USER_BIND_MAIL_ERROR(200015,"user_bind_mail_error ","绑定邮箱失败"),
    USER_NOT_EXIST(200016,"user_not_exist ","用户不存在"),
	USER_ACCOUNT_ISNULL(200017,"user_account_is_null","用户账户不存在，请初始化"),
	USER_OVERDUE_ERROR(200018,"user_voerdue_error","您的账户已失效（因未在48小时内激活），请重新注册"),
	USER_BAN_ERROR(200019,"user_ban_error","你的账号已被禁用"),
	USER_BAN_MOBILE_ERROR(200020,"user_ban_mobile_error","您未绑定手机不能进行操作"),
	USER_MOBILE_LOSE_ERROR(200021,"user_ban_mobile_lose_error","该用户已失效"),
	USER_NUM_ERROR(200022,"user_num_error","账号已被禁用"),
	USER_NUM_BUPP_ERROR(200023,"user_num_bupp_error","账户和手机不匹配"),



	//认证
	NOT_AUTH_GOOGLE(300001,"Not_open_Google_authentication","未开启google验证"),
	GOOGLE_SECRET_IS_NULL(300002,"google_secret_is_null","未绑定google 验证"),
	NOT_BIND_MOBILE(300003,"google_secret_is_null","未绑定手机，无法购买服务"),
	API_SECRET_ISNULL(300004,"api_secret_isnull","没有找到目标的api_secret"),
	GOOGLE_NOT_AUTH(300005,"google_secret_is_null","未验证google身份"),
	SECRET_IS_MISMATCHING(300006,"secret_is_mismatching","密钥不匹配"),
    VERIFICATION_FAILED(300007,"verification_failed","认证失败"),
	VERIFIED_TOAUDIT_REPEAT(300008,"verified_toaudit_repeat","已存在待审核实名,请勿重复提交"),
	VERIFIED_APPROVED_REPEAT(300009,"verified_approved_repeat","已存在已通过审核实名,请勿重复提交"),

	//请求
	REQUEST_SMS_OFTEN(400001,"request_sms_often","手机短信请求太过频繁，请稍后重试"),
//	REQUEST_MAIN_ADDRESS_ERROR(400002,"request_main_address_error","请求公账地址错误，请稍后重试"),
//	REQUEST_WITHDRAW_MONEY_ERROR(400003,"request_withdraw_error","请求提币失败，请稍后重试"),
//	REQUEST_BINDPHONE_ERROR(400004,"request_bind_phone_error","请求绑定手机失败，请稍后重试"),
//	REQUEST_UNBINDPHONE_ERROR(400005,"request_unbind_phone_error","请求解绑手机失败，请稍后重试"),
//	REQUEST_WITHDRAW_CONFIG_ERROR(400006,"request_withdraw_config_error","请求提币失败，服务器缺少配置"),
//	REQUEST_ASSET_LIST_ERROR(400007,"request_asset_list_error","请求资产列表失败，请稍后重试"),
	REQUEST_EMAIL_OFTEN(400008,"request_sms_often","邮箱请求太过频繁，请稍后重试"),
	REQUEST_SMS_ERROR(400009,"request_sms_error","发送短信失败，请稍后重试"),
	REQUEST_AGAIN_CLICK(400010,"request_again_click","请勿重复点击"),

	//采集
//	MONEY_MINE_IS_NOT_EXIST(500001,"money_mine_is_not_exist","不存在可采集的数据"),
//	MONEY_MINE_IS_REPEAT_RECEIVE(500002,"money_mine_is_not_exist","不可领取重复的数据"),
//	MONEY_MINE_NOT_IS_CURRENT_USER(500003,"money_mine_is_not_current_user","非当前用户的积分矿"),
//	MONEY_MINE_RECEIVE_IS_EXPIRED(500004,"money_mine_receive_is_expired","该积分已过期无法采集"),
	MONEY_MINE_PLUCK_ERROR(500005,"ERROR","采集失败"),

	//文件上传
	FILE_IS_NULL(600001,"file_is_null","文件为空"),
	FILE_NOT_IS_PIC(600002,"file_is_not_pic","不是图片文件"),
	FILE_SAVE_ERROR(600003,"file_save_error","保存文件失败"),
	FILE_NOT_FOUND(600004,"file_not_found","找不到文件"),

	//消息
	MESSAGE_IS_NOT_EXIST(700001,"message_is_not_exist","找不到该信息"),
	CONFIG_IS_NOT_EXIST(700002,"config_is_not_exist","会员等级信息未配置"),

	//交易
	TRANSFER_ACCOUNT_CAN_NOT_ZERO(800001,"transfer_account_can_not_zero","交易金额不能为0"),
	TRANSFER_CAN_NOT_TO_YOURSELF(800002,"Can't transfer money to yourself","不能给自己转账"),
	TRANSFER_DIFFERENT_AREA_CANNOT_TRANSFER (800003,"Different regions cannot be transferred","无法转账"),
	TRANS_NOT_OPEN(800004,"trans_not_open","交易未开放")

	//支付通道
,	PAYMENT_CHANNEL_IS_EXIST(900001,"payment_channel_is_exist","该支付通道已经存在绑定"),

	//短信
	MOBILE_AREACODE_NOT_SERVICE(1000001,"mobile_areacode_not_service","该区号不在服务范围"),
	MOBILE_NOT_BELONG_TO_AREACODE(1000002,"mobile_not_belong_to_areacode","该手机号码长度不在该区号范围"),
	;


	
	private final int type;
	private final String code;
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


	private WebResponMessage(int type, String code, String message) {
		this.type = type;
		this.code = code;
		this.message = message;
	}
	
	 
}
