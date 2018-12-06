package com.chanct.xdInsertOracle.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilePathName {
	
	public static String ECDR_FILENAME="xd_";
	public SimpleDateFormat name_sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	public SimpleDateFormat path_sdf=new SimpleDateFormat("yyyyMMdd");
	public  static String FILE_SUFFIX=".xlsx";
	public String getECDR_FILENAME() {
		String fileName=name_sdf.format(new Date());
		return ECDR_FILENAME+fileName+FILE_SUFFIX;
	}
    public String getLIST_FILEPATH() {
    	String filePath=path_sdf.format(new Date());
		return filePath+ File.separator;
	}
	public String getLIST_FILENAME() {
		String filePath=name_sdf.format(new Date());
		return filePath;
	}
	public static void main(String[] args){
    	System.out.println(new FilePathName().getLIST_FILENAME());
    }

}
