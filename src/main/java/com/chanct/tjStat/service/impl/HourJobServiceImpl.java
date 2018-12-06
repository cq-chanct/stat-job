package com.chanct.tjStat.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.transaction.Transaction;

import com.chanct.core.util.BeanUtil;
import com.chanct.netsecur.constants.DBConstant;
import com.chanct.tjStat.dao.HourJobDao;
import com.chanct.tjStat.dao.impl.HourJobDaoImpl;
import com.chanct.tjStat.service.HourJobServcie;

public class HourJobServiceImpl  implements HourJobServcie {
	private static HourJobDao hourDao=new HourJobDaoImpl();
	@Override
	public boolean statCheatedAreaUserCount(Map<String, String> map) {
		Transaction ts = null;// 事物提交
		boolean flag=true;
		try {
			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
		    flag= hourDao.statCheatedAreaUserCount(map);
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean statCheatedTypeCount(Map<String, String> map) {
		Transaction ts = null;// 事物提交
		boolean flag=true;
		try {
			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
		    flag= hourDao.statCheatedTypeCount(map);
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean statCheatedUserCity(Map<String, String> map) {
		return false;
	}

	@Override
	public boolean statCheatedAreaUserTop(Map<String, String> map) {
		Transaction ts = null;// 事物提交
		boolean flag=true;
		try {
			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
		    flag= hourDao.statCheatedAreaUserTop(map);
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean statDynamicFlow(Map<String, String> map) {
		Transaction ts = null;// 事物提交
		boolean flag=true;
		try {
			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
		    flag= hourDao.statDynamicFlow(map);
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}
	@Override
	public boolean statCallTotalCount(Map<String, String> map) {
		Transaction ts = null;// 事物提交
		boolean flag=true;
		try {
			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
		    flag= hourDao.statCallTotalCount(map);
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}


}
