package com.chanct.insertRejectPhone.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chanct.insertLowInterfaceOracle.dao.InsertLowInterfaceDao;
import com.chanct.insertLowInterfaceOracle.dao.impl.InsertLowInterfaceDaoImpl;
import com.chanct.insertLowInterfaceOracle.service.InsertLowInterfaceService;
import com.chanct.insertRejectPhone.dao.InsertRejectPhoneDao;
import com.chanct.insertRejectPhone.dao.impl.InsertRejectPhonDaoImpl;
import com.chanct.insertRejectPhone.entity.RejectBlackPhone;
import com.chanct.insertRejectPhone.service.InsertRejectPhonService;
import com.chanct.sjhmdinterfaceForBack.entity.FeedBack;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;

import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.DateUtil;
import com.chanct.insertEvilOracle.dao.InsertEvilDao;
import com.chanct.insertEvilOracle.dao.impl.InsertEvilDaoImpl;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.insertEvilOracle.service.InsertEvilService;
import com.chanct.netsecur.constants.DBConstant;

public class InsertRejectPhonServiceImpl implements InsertRejectPhonService {
	private static Logger logger = Logger.getLogger(InsertRejectPhonServiceImpl.class);
	private static InsertRejectPhoneDao insertEvilDao=new InsertRejectPhonDaoImpl();

	@Override
	public void insertObject() {
		List<RejectBlackPhone> RejectBlackPhoneList = new ArrayList<RejectBlackPhone>();
		List<RejectBlackPhone> evilLowInfoList = new ArrayList<RejectBlackPhone>();
		List<String>  idList = new ArrayList<>();
		evilLowInfoList =queryLowEvelInfo();
		logger.info(evilLowInfoList.size()+"----------");
		insertLowEvil(evilLowInfoList);
		/*logger.info("低度入oracle"+RejectBlackPhoneList.size());
		if(evilLowInfoList!=null && evilLowInfoList.size()>0){          //插入低度疑似
			for (RejectBlackPhone RejectBlackPhone : evilLowInfoList) {
				UcmdTgj ucmd = new UcmdTgj();
				ucmd.setCallingnumber(RejectBlackPhone.getEvilNumber());
				ucmd.setCallednumber(RejectBlackPhone.getCheatedUser());
				String answerTime = RejectBlackPhone.getAnswerTime();
				Date stringtoDate = DateUtil.stringtoDate(answerTime, DateUtil.FORMAT_ONE);
				String dateToString = DateUtil.dateToString(stringtoDate, DateUtil.FORMAT_ONE);
				ucmd.setStarttalktime(dateToString);
				ucmd.setTalktime(RejectBlackPhone.getCallDuration());
				if(RejectBlackPhone.getEvilCity() == null){
					ucmd.setCalled_city_name(RejectBlackPhone.getEvilCity());
				}else{
					ucmd.setCalled_city_name("未知");
				}
				list.add(ucmd);
			}
			insertLowEvil(list);
		}*/


	}
	private List<RejectBlackPhone> queryLowEvelInfo() {
		List<RejectBlackPhone> temp = new ArrayList<RejectBlackPhone>();
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
	public Boolean insertLowEvil(List<RejectBlackPhone> list) {
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
		InsertRejectPhonServiceImpl acd=new InsertRejectPhonServiceImpl();
		acd.insertObject();

	}
}
