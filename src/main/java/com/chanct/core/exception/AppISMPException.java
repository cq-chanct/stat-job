package com.chanct.core.exception;


import com.chanct.core.util.ExceptionUtil;

/**
 * 自定义捕获异常
 * 
 * @author : lihx create date : 2013-5-20
 */
public class AppISMPException extends Exception implements ISMPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 错误码
	private String errorCode;
	// 源异常
	private Throwable exception;
	// 参数
	private Object[] params;
	// 模块
	private String module;
	// 编号
	private String excepNo;
	// 级别
	private String excepLevel;
	// 类型
	private String excepType;
	/**
	 * 记录源异常
	 * 
	 * @param e
	 */
	public AppISMPException(Exception e) {
		super(e);
		this.exception = e;
	}
	/**
	 * 异常中只含错误码
	 * 
	 * @param errorCode
	 *            错误码
	 */
	public AppISMPException(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * 异常中包含错误码和参数
	 * 
	 * @param errorCode
	 *            错误码
	 * @param param
	 *            参数
	 */
	public AppISMPException(String errorCode, Object[] param) {
		this.errorCode = errorCode;
		this.params = param;
	}
	/**
	 * 源异常和错误码
	 * 
	 * @param e
	 *            源异常
	 * @param errorCode
	 *            错误码
	 */
	public AppISMPException(Exception e, String errorCode) {
		super(e);
		this.errorCode = errorCode;
		this.exception = e;
	}
	public AppISMPException(String errorCode,Exception e) {
		super(e);
		this.errorCode = errorCode;
		this.exception = e;
	
	}
	public AppISMPException(String errorCode,Exception e,Object[] param) {
		super(e);
		this.errorCode = errorCode;
		this.exception = e;
		this.params = param;
	}
	public AppISMPException(Exception e, String errorCode, String module, String excepNo, String excepLevel,
			String excepType) {
		super(e);
		this.errorCode = errorCode;
		this.exception = e;
		this.module = module;
		this.excepNo = excepNo;
		this.excepLevel = excepLevel;
		this.excepType = excepType;
	}
	public AppISMPException(Exception e, String errorCode,Object[] param, String module, String excepNo, String excepLevel,
			String excepType) {
		super(e);
		this.exception = e;
		this.errorCode = errorCode;
		this.params = param;
		this.module = module;
		this.excepNo = excepNo;
		this.excepLevel = excepLevel;
		this.excepType = excepType;
	}
	public AppISMPException(String errorCode, Object[] param, String module, String excepNo, String excepLevel,
			String excepType) {
		this.errorCode = errorCode;
		this.params = param;
		this.module = module;
		this.excepNo = excepNo;
		this.excepLevel = excepLevel;
		this.excepType = excepType;
	}
	public AppISMPException(String errorCode,  String module, String excepNo, String excepLevel,
			String excepType) {
		this.errorCode = errorCode;
		this.module = module;
		this.excepNo = excepNo;
		this.excepLevel = excepLevel;
		this.excepType = excepType;
	}
	/**
	 * 源异常 错误码和参数
	 * 
	 * @param e
	 * @param errorCode
	 * @param param
	 */
	public AppISMPException(Exception e, String errorCode, Object[] param) {
		super(e);
		this.errorCode = errorCode;
		this.exception = e;
		this.params = param;
	}
	public AppISMPException() {
	}
	/**
	 * 获取错误信息
	 */

	public String getErrorMessage() {
		return ExceptionUtil.getErrorMessage(this);
	}

	@Override
	public String toString() {
		return getErrorMessage();
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public Object[] getParams() {
		return params;
	}

	@Override
	public Throwable getParentException() {
		return exception;
	}

	/**
	 * 取异常堆栈
	 */
	@Override
	public String getStackTraceMessage() {
		return ExceptionUtil.getStackTraceMessage(this);
	}
	public String getModule() {
		return module;
	}
	public String getExcepNo() {
		return excepNo;
	}
	public String getExcepLevel() {
		return excepLevel;
	}
	public String getExcepType() {
		return excepType;
	}
}
