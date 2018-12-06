package com.chanct.cdrCount.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.chanct.cdrCount.dao.CdrCountJobDao;
import com.chanct.cdrCount.dao.impl.CdrCountJobDaoImpl;
import com.chanct.cdrCount.service.CdrCountService;
import com.chanct.cdrCount.util.CdrUtil;
import com.chanct.cdrCount.vo.EvilDetail;
import com.chanct.cdrCount.vo.IspVo;
import com.chanct.cdrCount.vo.NumBerArea;
import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.DateUtil;
import com.chanct.core.util.FtpUtil;
import com.chanct.core.util.PropertyUtil;
import com.chanct.netsecur.constants.Constant;
import com.chanct.netsecur.constants.DBConstant;
public class CdrCountServiceImpl implements CdrCountService{
	private static Logger logger = Logger.getLogger(CdrCountServiceImpl.class);

	/**
	 * 读取文件
	 * */
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
	@Override
	public void insertObject() {
		initAreaInfo();
		logger.info("话单解析-》任务开始" + DateUtil.timeFormat.format(new Date())  );
		File file=new File(DBConstant.FTP_CONFIG_CDR_OUT_PATH );
		List<File> tempList = (List<File>) FileUtils.listFiles(file, FileFilterUtils.trueFileFilter(), TrueFileFilter.INSTANCE);
		String fileName = null;
		InputStream ins = null;
		boolean flag = true;
		for(int i=0;i<tempList.size();i++){
			flag = true;
			fileName = tempList.get(i).getName();
			if(fileName.contains(Constant.LEVEL_DEEP)){
				readFile(tempList.get(i) ,ins, "2");
			}else if(fileName.contains(Constant.LEVEL_EASY)){
				readFile(tempList.get(i) ,ins, "1");
			}else{
				logger.info("该文件名不正确"+fileName);
				flag = false;
			}
			CdrUtil.operatorFile(tempList.get(i) ,flag);
		}
		logger.info("话单解析-》任务结束" + DateUtil.timeFormat.format(new Date()));
	}

	/**
	 * 读文件
	 * @param file
	 * @param ins
	 * @param type
	 * @return
	 */
	public void readFile(File file ,InputStream ins ,String type){
		List<EvilDetail> datalist = new ArrayList<EvilDetail>();
		try {
			ins = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, FtpUtil.encoding));
			String line;
			while ((line = reader.readLine()) != null) {
				EvilDetail evil = null;
				evil = getEvilDetail(readLen(line) , type);
				if(evil!=null){
					datalist.add(evil);
				}
			}
			reader.close();
			StringBuffer buffer = new StringBuffer();
			for(EvilDetail one : datalist){
				buffer.append(one.getStatDate() + ",");

				buffer.append(one.getCheatedUser() + ",");
				buffer.append(one.getUserCountry() + ",");
				buffer.append(one.getUserProv() + ",");
				buffer.append(one.getUserCity() + ",");
				buffer.append(one.getUserIsp() + ",");

				buffer.append(one.getEvilNumber() + ",");
				buffer.append(one.getEvilCountry() + ",");
				buffer.append(one.getEvilProv() + ",");
				buffer.append(one.getEvilCity() + ",");
				buffer.append(one.getEvilNumberIsp() + ",");
				buffer.append(one.getEvilNumberType() + ",");

				buffer.append(one.getCallType() + ",");
				buffer.append(one.getAnswerTime() + ",");
				buffer.append(one.getCallDuration() + ",");
				buffer.append(one.getAllTimes() + ",");
				buffer.append(one.getAllCallDuration() + ",");

				buffer.append(one.getCheatedType() + ",");
				buffer.append(one.getEngineTime() + ",");

				buffer.append(0 + ",");
				buffer.append("" + ",");
				buffer.append(0 + ",");
				buffer.append(0 + ",");

				buffer.append(one.getEvilType() + ",");
				buffer.append(one.getCdrSource());
				buffer.append("\n");

			}


			//			if(outType.contains("1")){
			//				if(insertEvil(datalist ,type)){
			//					logger.info(file.getName() + "入库成功");
			//				}else{
			//					logger.info(file.getName() + "入库失败");
			//				}
			//			}
			//			if(outType.contains("2")){
			OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(DBConstant.FTP_CONFIG_CDR_RESULT_PATH + file.getName() + "." + DBConstant.MAC), "UTF-8");
			ow.write(buffer.toString());
			ow.flush();
			ow.close();
			logger.info(file.getName() + "写出成功");
			//			}

			ins = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ins = null;
		}
	}

	/**
	 * 切割
	 * @param line
	 * @return
	 */
	public String[] readLen(String line){
		String [] lineArr=line.split(",");
		return lineArr;
	}
	/**
	 * 保存复制，返回对象
	 * @param arr
	 * @param sourceFlag
	 * @return
	 */
	public EvilDetail getEvilDetail(String[] arr ,String type){
		if(CdrUtil.white.contains(arr[4])){
			logger.info("白名单过滤-evilnumber:"+arr[4]);
			return null;
		}
		EvilDetail evil=new EvilDetail();
		evil.setStatDate(arr[0]);
		evil.setCheatedUser(arr[1]);
		evil.setUserCountry(getPhoneCountry(arr[1]));
		Map<String,Object> map=getPhoneMap(arr[1]);
		evil.setUserProv(map.get("phoneProv")+"");
		evil.setUserCity(map.get("phoneCity")+"");
		String userIsp=getUserIsp(evil.getCheatedUser());
		evil.setUserIsp(userIsp);
		evil.setAllTimes(arr[2]);
		evil.setAllCallDuration(arr[3]);
		evil.setEvilNumber(arr[4]);
		evil.setEvilCountry(getPhoneCountry(arr[4]));
		Map<String,Object> evilArea=getPhoneMap(arr[4]);
		evil.setEvilProv(evilArea.get("phoneProv")+"");
		evil.setEvilCity(evilArea.get("phoneCity")+"");
		String numType=getNumberType(arr[4]);
		evil.setEvilNumberType(numType);
		String ispName=getIsp(evil.getEvilNumber(),evil.getEvilNumberType());
		evil.setEvilNumberIsp(ispName);
		evil.setCallType(arr[5]);
		evil.setAnswerTime(arr[6]);	
		evil.setCallDuration(arr[7]);
		evil.setCheatedType(arr[8]);	
		evil.setEngineTime(arr[9]);
		evil.setEvilType(type);
		evil.setCdrSource(DBConstant.CDR_SOURCE);
		return evil;
	}


	/**
	 * 将ftp文本文件的数据插入到数据库中
	 * 
	 * @param fileName 文本文件名称
	 * @param webList web信息集合
	 * @param ftp FTP工具
	 */
	public Boolean insertEvil(List<EvilDetail> list ,String type) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			if (list.size() > 0) {
				temp = cdrDao.insertEvil(list);

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
	 * 返回省，市归属信息
	 * @param phone
	 * @return
	 */
	public  Map<String,Object> getPhoneMap(String phone){
		Map<String,Object> map = new HashMap<String, Object>();
		String regTel = "^(0\\d{2,3}?-?)?\\d{7,8}$";
		NumBerArea nba=new NumBerArea();
		if(phone.matches(regTel)){
			if(phone.length()>=4){
				nba.setPhoneNum(phone.substring(0, 4));
				if(CdrUtil.telMap.containsKey(nba.getPhoneNum())){
					map.put("phoneProv", CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneProv());
					map.put("phoneCity", CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneCity());
					return map;
				}
				nba.setPhoneNum(phone.substring(0, 3));
				if(CdrUtil.telMap.containsKey(nba.getPhoneNum())){
					map.put("phoneProv", CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneProv());
					map.put("phoneCity", CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneCity());
					return map;
				}
			}else{
				nba.setPhoneNum(phone);
			}

			if(CdrUtil.telMap.containsKey(nba.getPhoneNum())){
				map.put("phoneProv", CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneProv());
				map.put("phoneCity", CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneCity());
				return map;
			}


		}else if(phone!=null&&phone.startsWith("1")){
			if(phone.length()>=7){
				nba.setPhoneNum(phone.substring(0, 7));
			}else{
				nba.setPhoneNum(phone);
			}
			if(CdrUtil.moblieMap.containsKey(nba.getPhoneNum())){
				map.put("phoneProv", CdrUtil.moblieMap.get(nba.getPhoneNum()).getPhoneProv());
				map.put("phoneCity", CdrUtil.moblieMap.get(nba.getPhoneNum()).getPhoneCity());
				return map;
			}


		}
		map.put("phoneProv", "未知");
		map.put("phoneCity", "未知");
		return map;
	}
	/**
	 * 返回国家
	 * @param phone
	 * @return
	 */
	public String getPhoneCountry(String phone){
		String country="未知";
		if(phone.startsWith("00")){
			if(phone.length()>7){
				for(int i=6;i>2;i--){
					String num=phone.substring(0, i);
					if(CdrUtil.countryMap.containsKey(num)){
						country= CdrUtil.countryMap.get(num);
					}
				}
			}
			return country;
		}else{
			country="中国";
			return country;
		}
	}
	/**
	 * 返回号码类型
	 * @param phone
	 * @return
	 */
	public  String  getNumberType(String phone){
		String numType="未知";
		String regTel = "^(0\\d{2,3}?-?)?\\d{7,8}$";
		if(phone!=""&&phone!=null){
			if(phone.startsWith("1")&&phone.length()==11){
				numType="手机号码";
			}else if(phone.startsWith("400")){
				numType="400号码";
			}else if(phone.startsWith("00")){
				numType="海外号码";
			}else if(phone.matches(regTel)){
				numType="固话";
			}else{
				numType="未知";
			}
		}
		return numType;
	}
	/**
	 * 返回运营商
	 * @param phone
	 * @param numType
	 * @return
	 */
	public  String  getIsp(String phone,String numType){
		String isp="未知";
		if(phone.length()<4){
			return isp;
		}else{
			switch (numType){
			case "手机号码":
				if(CdrUtil.ispMap.containsKey(phone.subSequence(0, 3))){
					isp=CdrUtil.ispMap.get(phone.subSequence(0, 3));
				}
				break;
			case "400号码":
				if(CdrUtil.FourMap.containsKey(phone.subSequence(0, 4))){
					isp=CdrUtil.FourMap.get(phone.subSequence(0, 4));
				}
				break;
			default:
				break;	
			}
			return isp;
		}

	}
	/**
	 * 返回运营商
	 * @param phone
	 * @return
	 */
	public  String  getUserIsp(String phone){
		String isp="未知";
		String regPhone = "^(86)?\\s*1[3|4|5|7|8]\\d{9}$";
		if(phone.length()<4){
			return isp;
		}else{
			if(phone.matches(regPhone)){
				if(CdrUtil.ispMap.containsKey(phone.subSequence(0, 3))){
					isp=CdrUtil.ispMap.get(phone.subSequence(0, 3));
				}
			}else if(phone.startsWith("400")){
				if(CdrUtil.FourMap.containsKey(phone.subSequence(0, 4))){
					isp=CdrUtil.FourMap.get(phone.subSequence(0, 4));
				}
			}
			return isp;
		}

	}
	/**
	 * 初始化区域map信息，将区县和省市的对应关系放入map，方便后面进行读取
	 * */
	@Test
	public void initAreaInfo(){
		logger.info("----------------初始化相关信息-------------------");
		if(CdrUtil.moblieMap.size() ==0){
			List<NumBerArea> mobileAreaList = cdrDao.getAreaListMobile(new NumBerArea());
			if(mobileAreaList != null && mobileAreaList.size() >0){
				for(NumBerArea ai : mobileAreaList){
					CdrUtil.moblieMap.put(ai.getPhoneNum(), ai);
				}
			}
		}
		if(CdrUtil.telMap.size() ==0){
			List<NumBerArea> telAreaList = cdrDao.getAreaList(new NumBerArea());
			if(telAreaList != null && telAreaList.size() >0){
				for(NumBerArea ai : telAreaList){
					CdrUtil.telMap.put(ai.getPhoneNum(), ai);
				}
			}
		}
		if(CdrUtil.ispMap.size() ==0){
			List<IspVo> isplist = cdrDao.getIspList();
			if(isplist != null && isplist.size() >0){
				for(IspVo ai : isplist){
					CdrUtil.ispMap.put(ai.getPreFix(), ai.getIspName());
				}
			}
		}
		if(CdrUtil.countryMap.size() ==0){
			List<NumBerArea> isplist = cdrDao.getCountryPhone(null);
			if(isplist != null && isplist.size() >0){
				for(NumBerArea ai : isplist){
					CdrUtil.countryMap.put(ai.getPhoneNum(), ai.getPhoneCountry());
				}
			}
		}
		List<String> whitelist = cdrDao.getWhiteList();
		logger.info("---------------------更新白名单-----------------------");
		for(String str:whitelist){
			CdrUtil.white.add(str);
		}
	} 
	public static void main(String argsp[]) throws SQLException{
		CdrCountServiceImpl acd=new CdrCountServiceImpl();
		acd.insertObject();

	}
}