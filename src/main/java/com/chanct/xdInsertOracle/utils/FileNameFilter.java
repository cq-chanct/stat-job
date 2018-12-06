package com.chanct.xdInsertOracle.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
* @Title: FileNameFilter.java   
* @Package com.httc.utils   
* @Description: 文件名称过滤器  
* @author tangkl
* @date 2015年2月2日 上午11:00:08   
* @version V1.0
 */
public class FileNameFilter implements FilenameFilter {
    //prefix为需要过滤的条件，比如如果prefix="20150202"，则只能返回以20150202开头的文件
	private Pattern pattern;
	private String regex;
	public FileNameFilter(String regex){
	this.regex = regex;
	}
	@Override
	public boolean accept(File dir, String name) {
		pattern= Pattern.compile(regex);
		Matcher mathcer=pattern.matcher(name);
		return mathcer.matches();
	}

}