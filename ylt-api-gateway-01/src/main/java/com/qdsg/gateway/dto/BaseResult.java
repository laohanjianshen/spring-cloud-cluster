package com.qdsg.gateway.dto;

/**
 * 
 * @ClassName: BaseResult
 * @Description:对返回值进行统一包装
 * @author: ZJ
 * @date: 2018年8月1日 下午4:30:33
 * @version 1.0
 * @Copyright: 2018 www.qdsgvision.com Inc. All rights reserved.
 */
public class BaseResult<T> {
	/**
	 * 操作状态，1代表成功 0代表失败,-1代表系统出错
	 */
	private Integer status;
	/**
	 * 错误码，提前按照规定定义错误码，根据错误码就知道对应的错误类型 eg："0011000"，001可以定义为application代码 1000为具体错误码
	 */
	private String code;
	/**
	 * 返回结果描述
	 */
	private String message;
	/**
	 * 返回结果
	 */
	private T data;

	/**
	 * 只返回操作状态
	 */
	public BaseResult(Integer status) {
		super();
		this.status = status;
	}

	/**
	 * 返回操作状态和数据
	 */
	public BaseResult(Integer status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

	/**
	 * 返回所有
	 */
	public BaseResult(Integer status, String code, String message, T data) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 操作成功
	 */
	public static BaseResult success() {
		return new BaseResult<>(1);
	}
	/**
	 * 操作失败
	 */
	public static BaseResult failure() {
		return new BaseResult<>(0);
	}
	
	/**
	 * 系统抛错
	 */
	public static BaseResult error() {
		return new BaseResult<>(-1);
	}

	@Override
	public String toString() {
		return "BaseResult{" +
				"status=" + status +
				", code='" + code + '\'' +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
