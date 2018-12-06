package com.chanct.insertEvilOracle.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chanct.sjhmdinterfaceForBack.entity.FeedBack;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;

import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.DateUtil;
import com.chanct.insertEvilOracle.dao.InsertEvilDao;
import com.chanct.insertEvilOracle.dao.impl.InsertEvilDaoImpl;
import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.insertEvilOracle.service.InsertEvilService;
import com.chanct.netsecur.constants.DBConstant;

public class InsertEvilServiceImpl implements InsertEvilService {
	private static Logger logger = Logger.getLogger(InsertEvilServiceImpl.class);
	private static InsertEvilDao insertEvilDao=new InsertEvilDaoImpl();

	@Override
	public void insertObject() {
		List<EvilInfo> evilInfoList = new ArrayList<EvilInfo>();
		List<EvilInfo> evilLowInfoList = new ArrayList<EvilInfo>();
		List<UcmdTgj> list = new ArrayList<UcmdTgj>();
		List<String>  idList = new ArrayList<>();
		evilInfoList = queryEvelInfo();
		evilLowInfoList =queryLowEvelInfo();
		logger.info("高度入oracle"+evilInfoList.size());
		if(evilLowInfoList!=null && evilLowInfoList.size()>0){          //插入低度疑似
			for (EvilInfo evilInfo : evilInfoList) {
				UcmdTgj ucmd = new UcmdTgj();
				if("未知".equals(evilInfo.getUserCity())){
					ucmd.setCityname("杭州");
				}else if(evilInfo.getUserCity() ==null){
					ucmd.setCityname("杭州");
				}else{
					ucmd.setCityname(evilInfo.getUserCity());
				}
				ucmd.setCallingnumber(evilInfo.getEvilNumber());
				ucmd.setCallednumber(evilInfo.getCheatedUser());
				String answerTime = evilInfo.getAnswerTime();
				Date stringtoDate = DateUtil.stringtoDate(answerTime, DateUtil.FORMAT_ONE);
				String dateToString = DateUtil.dateToString(stringtoDate, DateUtil.FORMAT_ONE);
				ucmd.setStarttalktime(dateToString);
				ucmd.setTalktime(evilInfo.getCallDuration());
				if(evilInfo.getEvilCity() == null){
					ucmd.setCalled_city_name(evilInfo.getEvilCity());
				}else{
					ucmd.setCalled_city_name("未知");
				}
				//list.add(ucmd);
			}
			insertLowEvil(list);
		}


		if(evilInfoList!=null && evilInfoList.size()>0){
			for (EvilInfo evilInfo : evilInfoList) {
				logger.info("电话："+evilInfo.getEvilNumber());
				UcmdTgj ucmd = new UcmdTgj();
				idList.add(evilInfo.getId());
				if("未知".equals(evilInfo.getUserCity())){
					ucmd.setCityname("杭州");
				}else if(evilInfo.getUserCity() ==null){
					ucmd.setCityname("杭州");
				}else{
					ucmd.setCityname(evilInfo.getUserCity());
				}
				ucmd.setCallingnumber(evilInfo.getEvilNumber());
				ucmd.setCallednumber(evilInfo.getCheatedUser());
				String answerTime = evilInfo.getAnswerTime();
				Date stringtoDate = DateUtil.stringtoDate(answerTime, DateUtil.FORMAT_ONE);
				String dateToString = DateUtil.dateToString(stringtoDate, DateUtil.FORMAT_ONE);
				ucmd.setStarttalktime(dateToString);
				ucmd.setTalktime(evilInfo.getCallDuration());
				if(evilInfo.getEvilCity() == null){
					ucmd.setCalled_city_name(evilInfo.getEvilCity());
				}else{
					ucmd.setCalled_city_name("未知");
				}
				list.add(ucmd);
			}
			updateEvil(idList);   //修改黑名单的状态，判断是否已经推送
			updateKaiTongEvil(idList);
			insertEvil(list);
		}
	}
	private List<EvilInfo> queryLowEvelInfo() {
		List<EvilInfo> temp = new ArrayList<EvilInfo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			temp = insertEvilDao.getLowEvilByTime();
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return temp ;
	}
	private List<EvilInfo> queryEvelInfo() {
		List<EvilInfo> temp = new ArrayList<EvilInfo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			temp = insertEvilDao.getEvilByTime();
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return temp ;
	}
	public Boolean insertEvil(List<UcmdTgj> list) {
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
		logger.info("高度入oracle"+flag);
		return flag ;
	}
	public Boolean insertLowEvil(List<UcmdTgj> list) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.oracle);
			if (list.size() > 0) {
				temp = insertEvilDao.insertLowEvil(list);
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
		logger.info("高度入oracle"+flag);
		return flag ;
	}
	public Boolean updateEvil(List<String> evilInfo) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			if (evilInfo != null) {
				temp = insertEvilDao.updateState(evilInfo);
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
		logger.info("修改黑名单状态"+flag);
		return flag ;
	}
	public Boolean updateKaiTongEvil(List<String> evilInfo) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			if (evilInfo != null) {
				temp = insertEvilDao.updateKaiTongState(evilInfo);
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
		logger.info("修改黑名单状态"+flag);
		return flag ;
	}
	
	public static void main(String argsp[]) throws SQLException{
		InsertEvilServiceImpl acd=new InsertEvilServiceImpl();
		acd.insertObject();

	}
}
