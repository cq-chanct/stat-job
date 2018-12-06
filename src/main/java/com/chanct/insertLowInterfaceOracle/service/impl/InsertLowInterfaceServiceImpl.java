package com.chanct.insertLowInterfaceOracle.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chanct.insertLowInterfaceOracle.dao.InsertLowInterfaceDao;
import com.chanct.insertLowInterfaceOracle.dao.impl.InsertLowInterfaceDaoImpl;
import com.chanct.insertLowInterfaceOracle.service.InsertLowInterfaceService;
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

public class InsertLowInterfaceServiceImpl implements InsertLowInterfaceService {
	private static Logger logger = Logger.getLogger(InsertLowInterfaceServiceImpl.class);
	private static InsertLowInterfaceDao insertEvilDao=new InsertLowInterfaceDaoImpl();

	@Override
	public void insertObject() {
		List<EvilInfo> evilInfoList = new ArrayList<EvilInfo>();
		List<EvilInfo> evilLowInfoList = new ArrayList<EvilInfo>();
		List<UcmdTgj> list = new ArrayList<UcmdTgj>();
		List<String>  idList = new ArrayList<>();
		evilLowInfoList =queryLowEvelInfo();
		logger.info("低度入oracle"+evilInfoList.size());
		if(evilLowInfoList!=null && evilLowInfoList.size()>0){          //插入低度疑似
			for (EvilInfo evilInfo : evilLowInfoList) {
				UcmdTgj ucmd = new UcmdTgj();
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
			insertLowEvil(list);
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
		logger.info("低度入oracle"+flag);
		return flag ;
	}
	public static void main(String argsp[]) throws SQLException{
		InsertLowInterfaceServiceImpl acd=new InsertLowInterfaceServiceImpl();
		acd.insertObject();

	}
}
