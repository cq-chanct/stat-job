package com.chanct.core.exception;


public interface ISMPException {

	/**
	 * 获取错误码
	 * @return
	 */
	String getErrorCode();
	/**
	 * 取错误参数
	 * @return
	 */
	Object[] getParams();
	/**
	 * 取源exception
	 * @return
	 */
	Throwable getParentException();
	/**
	 * 获取错误信息
	 * @return
	 */
	String getErrorMessage();
	/**
	 * 取堆栈信息
	 * @return
	 */
	String getStackTraceMessage();
	
	String getModule();
	String getExcepNo();
	String getExcepLevel();
	String getExcepType();
}
