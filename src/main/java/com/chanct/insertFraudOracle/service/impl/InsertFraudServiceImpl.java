package com.chanct.insertFraudOracle.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.chanct.cdrCount.dao.CdrCountJobDao;
import com.chanct.cdrCount.dao.impl.CdrCountJobDaoImpl;
import com.chanct.cdrCount.util.CdrUtil;
import com.chanct.cdrCount.vo.IspVo;
import com.chanct.cdrCount.vo.NumBerArea;
import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.DateUtil;
import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.insertEvilOracle.service.impl.InsertEvilServiceImpl;
import com.chanct.insertFraudOracle.dao.InsertFraudDao;
import com.chanct.insertFraudOracle.dao.impl.InsertFraudDaoImpl;
import com.chanct.insertFraudOracle.entity.FraudEvilInfo;
import com.chanct.insertFraudOracle.entity.FraudUcmdTgj;
import com.chanct.insertFraudOracle.service.InsertFraudService;
import com.chanct.netsecur.constants.DBConstant;

public class InsertFraudServiceImpl implements InsertFraudService {
	private static Logger logger = Logger.getLogger(InsertFraudServiceImpl.class);
	private static InsertFraudDao insertEvilDao=new InsertFraudDaoImpl();
	public CdrCountJobDao cdrDao=new CdrCountJobDaoImpl();

	@Override
	public void insertObject() {
		initAreaInfo();
		logger.info("自然人话单-》任务开始" + DateUtil.timeFormat.format(new Date())  );
		List<FraudEvilInfo> evilInfoList = new ArrayList<FraudEvilInfo>();
		List<FraudUcmdTgj> list = new ArrayList<FraudUcmdTgj>();
		evilInfoList = insertEvilDao.getEvilByTime();
		logger.info("自然人话单入oracle："+evilInfoList.size());
		if(evilInfoList!=null && evilInfoList.size()>0){
			for (FraudEvilInfo evilInfo : evilInfoList) {
				FraudUcmdTgj ucmd = new FraudUcmdTgj();
				String callingnumber = evilInfo.getCallingnumber();
				String callednumber = evilInfo.getCallednumber();
				ucmd.setCityname(getPhoneArea(callingnumber));
				ucmd.setCallingnumber(callingnumber);
				ucmd.setCallednumber(callednumber);
				ucmd.setStarttalktime(evilInfo.getAnswertime());
				ucmd.setStoptalktime(evilInfo.getAnswerendtime());
				ucmd.setStartcalltime(evilInfo.getStarttime());
				ucmd.setStopcalltime(evilInfo.getEndtime());
				ucmd.setTalktime(evilInfo.getCallduration());
				ucmd.setCalled_city_name(getPhoneArea(callednumber));
				list.add(ucmd);
			}
			insertEvil(list);
		}
	}

	public Boolean insertEvil(List<FraudUcmdTgj> list) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.oracle);
			if (list.size() > 0) {
				temp = insertEvilDao.insertEvil(list);
			}
			ts.commit();
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
		logger.info("自然人话单入oracle"+flag);
		return flag ;
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

	} 
	/**
	 * 返回省，市归属信息
	 * @param phone
	 * @return
	 */
	public  String getPhoneArea(String phone){
		String phoneCity = "未知";
		String regTel = "^(0\\d{2,3}?-?)?\\d{7,8}$";
		NumBerArea nba=new NumBerArea();
		if(phone.matches(regTel)){
			if(phone.length()>=4){
				nba.setPhoneNum(phone.substring(0, 4));
				if(CdrUtil.telMap.containsKey(nba.getPhoneNum())){
					phoneCity = CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneCity();
					return phoneCity;
				}
				nba.setPhoneNum(phone.substring(0, 3));
				if(CdrUtil.telMap.containsKey(nba.getPhoneNum())){
					phoneCity = CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneCity();
					return phoneCity;
				}
			}else{
				nba.setPhoneNum(phone);
			}

			if(CdrUtil.telMap.containsKey(nba.getPhoneNum())){
				phoneCity = CdrUtil.telMap.get(nba.getPhoneNum()).getPhoneCity();
				return phoneCity;
			}


		}else if(phone!=null&&phone.startsWith("1")){
			if(phone.length()>=7){
				nba.setPhoneNum(phone.substring(0, 7));
			}else{
				nba.setPhoneNum(phone);
			}
			if(CdrUtil.moblieMap.containsKey(nba.getPhoneNum())){
				phoneCity = CdrUtil.moblieMap.get(nba.getPhoneNum()).getPhoneCity();
				return phoneCity;
			}else{
				return phoneCity;
			}
		}

		return phoneCity;
	}
	public static void main(String argsp[]) throws SQLException{
		InsertFraudServiceImpl acd=new InsertFraudServiceImpl();
		acd.insertObject();

	}
}
