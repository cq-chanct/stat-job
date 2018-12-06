package com.chanct.fraudCdr.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;

import com.chanct.cdrCount.dao.CdrCountJobDao;
import com.chanct.cdrCount.dao.impl.CdrCountJobDaoImpl;
import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.DateUtil;
import com.chanct.core.util.FtpUtil;
import com.chanct.core.util.PropertyUtil;
import com.chanct.fraudCdr.dao.FraudCdrJobDao;
import com.chanct.fraudCdr.dao.impl.FraudCdrJobDaoImpl;
import com.chanct.fraudCdr.service.FraudCdrService;
import com.chanct.fraudCdr.vo.FraudCdrDetail;
import com.chanct.netsecur.constants.DBConstant;

public class FraudCdrServiceImpl implements FraudCdrService {
	private static Logger logger = Logger.getLogger(FraudCdrServiceImpl.class);
	public FraudCdrJobDao fraudcdrDao=new FraudCdrJobDaoImpl();
	public CdrCountJobDao cdrDao=new CdrCountJobDaoImpl();
	public static Set<String> outType = new HashSet<String>();

	static{

		String resultType = PropertyUtil.getConfig("result.type");
		if(resultType!=null&&!"".equals(resultType)){
			for(String one : resultType.split(",")){
				outType.add(one);
			}

		}
	}
	/**
	 * 读取插入
	 */
	public void insertObject() {
		logger.info("疑似诈骗电话话单解析-》任务开始" + DateUtil.timeFormat.format(new Date())  );
		File file=new File(DBConstant.FTP_FRAUD_CDR_UP_PATH );
		List<File> tempList = (List<File>) FileUtils.listFiles(file, FileFilterUtils.trueFileFilter(), TrueFileFilter.INSTANCE);
		String fileName = null;
		InputStream ins = null;
		boolean flag = true;
		for(int i=0;i<tempList.size();i++){
			flag = true;
			fileName = tempList.get(i).getName();
			Boolean readFile = readFile(tempList.get(i) ,ins);
			if(readFile){
				this.operatorFile(tempList.get(i) ,flag);
			}
		}
		logger.info("疑似诈骗电话话单解析-》任务结束" + DateUtil.timeFormat.format(new Date()));
		
	}
	/**
	 * 切割
	 * @param line
	 * @return
	 */
	public String[] readLen(String line){
//		String [] lineArr=line.split("//|");
		String [] lineArr=StringUtils.splitByWholeSeparatorPreserveAllTokens(line, "|");
		return lineArr;
	}
	public String totrim(String objstr){
//		String [] lineArr=line.split("//|");
		if(objstr!=null&&!"".equals(objstr)){
			objstr = objstr.trim();
		}
		return objstr;
	}
	/**
	 * 读文件
	 * @param file
	 * @param ins
	 * @param type
	 * @return
	 */
	public Boolean readFile(File file ,InputStream ins){
		Boolean flag = true;
		List<FraudCdrDetail> datalist = new ArrayList<FraudCdrDetail>();
//		StringBuffer sb = new StringBuffer();
		try {
			ins = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, FtpUtil.encoding));
			String line;
			while ((line = reader.readLine()) != null) {
				FraudCdrDetail evil = null;
				evil = getEvilDetail(readLen(line));
				if(evil!=null){
					datalist.add(evil);
//					sb.append(getEvilStr(evil)+"\n");
				}
			}
			reader.close();
			if(insertEvil(datalist)){
				flag = true;
				logger.info(file.getName() + "入库成功");
			}else{
				flag = false;
				logger.info(file.getName() + "入库失败");
			}
			ins = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ins = null;
		}
		return flag;
	}

	/**
	 * 将ftp文本文件的数据插入到数据库中
	 * 
	 * @param fileName 文本文件名称
	 * @param webList web信息集合
	 * @param ftp FTP工具
	 */
	public Boolean insertEvil(List<FraudCdrDetail> list) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.managerDb);
			if (list.size() > 0) {
				temp = fraudcdrDao.insertEvil(list);

			}
			ts.commit();
			// 数据库插入成功后删除该文件
			if (temp==0) {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag ;
	}
	/**
	 * 保存复制，返回对象
	 * @param arr
	 * @param sourceFlag
	 * @return
	 */
	public FraudCdrDetail getEvilDetail(String[] arr){
		FraudCdrDetail evil=new FraudCdrDetail();
		evil.setFrontid(totrim(arr[1])==""?null:totrim(arr[1]));
		evil.setDirection(totrim(arr[2])==""?null:totrim(arr[2]));
		evil.setDomain(totrim(arr[3]));
		evil.setCallingnumber(totrim(arr[4]));
		evil.setCallednumber(totrim(arr[6]));
		evil.setStarttime(totrim(arr[7]));
		evil.setAnswertime(totrim(arr[8]));
		evil.setAnswerendtime(totrim(arr[10]));
		evil.setEndtime(totrim(arr[11]));
		evil.setCalllength(totrim(arr[14])==""?null:totrim(arr[14]));
		evil.setCallduration(totrim(arr[15])==""?null:totrim(arr[15]));
		evil.setCalltype(totrim(arr[16])==""?null:totrim(arr[16]));
		evil.setBarringtype(totrim(arr[17]));
		evil.setTrunkid(totrim(arr[19]));
		evil.setLocalcode(totrim(arr[20]));
		evil.setDestcode(totrim(arr[21]));
		evil.setListtype(totrim(arr[22])==""?null:totrim(arr[22]));
		evil.setAuditresult(totrim(arr[23])==""?null:totrim(arr[23]));
		evil.setRecordpath(totrim(arr[24]));
		evil.setRecordstarttime(totrim(arr[25]));
		evil.setRecordendtime(totrim(arr[26]));
		String recordpath = arr[24];
		if(recordpath!=null&&!"".equals(recordpath)){
			String voiceName = recordpath.trim().substring(0,recordpath.trim().length()-3)+"wav";
			evil.setVoiceName(voiceName);
			String substring = recordpath.substring(0, 8);
			evil.setVoiceUrl(DBConstant.FTP_FRAUDWAV_UP_PATH+substring+"/");
		}
		
		return evil;
	}
	public static void operatorFile(File file, boolean flag) {
		String date=DateUtil.getCurrDate(DateUtil.FORMATE_EIGHT)+"/";
		initDir(date);
		if(flag){
			File fnew = new File(DBConstant.FTP_FRAUD_CDR_BAK_PATH +date+file.getName());
			file.renameTo(fnew);
		}
		file.delete();
	}
	/**
	 * 文件夹准备
	 * @param date
	 */
	public static void initDir(String date){
		File fileBakDate = new File(DBConstant.FTP_FRAUD_CDR_BAK_PATH + date);
		if(!fileBakDate.isDirectory()){
			fileBakDate.mkdirs();
		}
	}
	public static void main(String argsp[]) throws SQLException{
		FraudCdrServiceImpl acd=new FraudCdrServiceImpl();
		acd.insertObject();

	}
}
