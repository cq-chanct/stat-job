package com.chanct.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import com.chanct.core.exception.ISMPException;

/**
 * 
 * 
 * @author : lihx create date : 2013-9-12
 */
public class ExceptionUtil {
	/**
	 * 参数信息标志的开始符
	 */
	private static final String STARTELEMENT = "<";
	/**
	 * 参数信息标志的结束符
	 */
	private static final String ENDELEMENT = ">";

	public static String getErrorMessage(ISMPException e) {
		StringBuilder sb = new StringBuilder();
		if (e.getErrorCode() != null && !"".equals(e.getErrorCode())) {
			String message = PropertyUtil.getErrorInfo(e.getErrorCode());
			if (e.getParams() != null && e.getParams().length > 0) {
				message = getErrorByParam(message, e.getParams());
			}
			sb.append(message);
		} else if (e.getParentException() != null) {
			Throwable exception = e.getParentException();
			while (exception instanceof InvocationTargetException) {
				exception = ((InvocationTargetException) exception).getTargetException();
			}
			sb.append(e.getParentException().getMessage());
		}

		return sb.toString();
	}

	/**
	 * 参数替换错误信息
	 * 
	 * @param message
	 * @param params
	 * @return
	 */
	private static String getErrorByParam(String message, Object[] params) {
		String errorDesc = message;
		if (message.indexOf(STARTELEMENT) != -1) {
			if (params == null) {
				errorDesc = PropertyUtil.getErrorInfo("CORE.EXCPTION.0001") + ":错误信息[" + message + "]，而参数为空！";
			}

			for (int i = 0; i < params.length; i++) {
				String replaceStr = STARTELEMENT + i + ENDELEMENT;
				String targetStr = params[i].toString();
				if (errorDesc.indexOf(replaceStr) != -1) {
					errorDesc = errorDesc.replaceAll(replaceStr, targetStr);
				} else {
					errorDesc = PropertyUtil.getErrorInfo("CORE.EXCPTION.0001") + ":错误信息[" + message + "]，而参数有"
							+ params.length + "个:参数个数或者参数排序错误!";
				}
			}

			/*if (errorDesc.indexOf(STARTELEMENT) != -1) {
				errorDesc = PropertyUtil.getErrorInfo("CORE.EXCPTION.0001") + ":错误信息[" + message + "]，而参数有"
						+ params.length + "个:参数个数错误！";
			}*/
		}

		return errorDesc;
	}
	/**
	 * 拼接错误堆栈
	 * @param e 异常
	 * @return 堆栈
	 */
	public static String getStackTraceMessage(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
