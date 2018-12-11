
package com.common.components.redis.exception;


import com.common.exception.BaseException;

/**
 * CoinOrderException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2018-4-18 新建
 */
public class RedisException extends BaseException {

	private static final long serialVersionUID = 1L;

	public RedisException(String message, Exception e){
		super(message, e);
	}

	public RedisException(String message){
		super(message);
	}

}