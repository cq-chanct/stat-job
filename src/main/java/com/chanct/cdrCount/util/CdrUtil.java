package com.chanct.cdrCount.util;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.chanct.cdrCount.vo.NumBerArea;
import com.chanct.core.util.DateUtil;
import com.chanct.netsecur.constants.DBConstant;

public class CdrUtil {
	public static Map<String,NumBerArea> moblieMap = new HashMap<String,NumBerArea>();
	public static Map<String,NumBerArea> telMap = new HashMap<String,NumBerArea>();
	public static Map<String,String> ispMap = new HashMap<String,String>();
	public static Map<String,String> FourMap = new HashMap<String,String>();
	public static Map<String,String> countryMap = new HashMap<String,String>();
	public static Set<String> white = new HashSet<String>();
	static{
		FourMap.put("4006", "联通");
		FourMap.put("4007", "移动");
		FourMap.put("4008", "电信");
		FourMap.put("4001", "移动");
		FourMap.put("4000", "联通");
		FourMap.put("4009", "电信");
	}
	/**
	 * 处理操作之后的文件，将文件移动到错误目录或者备份目录
	 * */
	public static void operatorFile(File file, boolean flag) {
		String date=DateUtil.getCurrDate(DateUtil.FORMATE_EIGHT)+"/";
		initDir(date);
		if(flag){
			File fnew = new File(DBConstant.FTP_CONFIG_CDR_BAK_PATH +date+file.getName());
			file.renameTo(fnew);
		}
		file.delete();
	}
	/**
	 * 文件夹准备
	 * @param date
	 */
	public static void initDir(String date){
		File fileBakDate = new File(DBConstant.FTP_CONFIG_CDR_BAK_PATH + date);
		if(!fileBakDate.isDirectory()){
			fileBakDate.mkdirs();
		}
		File fileResultDate = new File(DBConstant.FTP_CONFIG_CDR_RESULT_PATH);
		if(!fileResultDate.isDirectory()){
			fileResultDate.mkdirs();
		}
	}

}
