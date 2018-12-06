package com.chanct.tjStat.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.transaction.Transaction;

import com.chanct.core.util.BeanUtil;
import com.chanct.netsecur.constants.DBConstant;
import com.chanct.tjStat.dao.DayJobDao;
import com.chanct.tjStat.dao.impl.DayJobDaoImpl;
import com.chanct.tjStat.service.DayJobServcie;

public class DayJobServiceImpl implements DayJobServcie {
	private static DayJobDao dayDao=new DayJobDaoImpl();
	@Override
	public boolean statCheatedUserCity(Map<String, String> map) {
		Transaction ts = null;// 事物提交
		boolean flag=true;
		try {
			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
		    flag= dayDao.statCheatedUserCity(map);
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
	public boolean statCallIsp(Map<String, String> map) {
		Transaction ts = null;// 事物提交
		boolean flag=true;
		try {
			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
		    flag= dayDao.statCallIsp(map);
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
	public boolean statCheatedTypeDayCount(Map<String, String> map) {
		Transaction ts = null;// 事物提交
		boolean flag=true;
		try {
			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
		    flag= dayDao.statCheatedTypeCount(map);
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
