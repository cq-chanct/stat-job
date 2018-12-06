package com.chanct.tjStat.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;

import com.chanct.core.dao.BaseDAO;
import com.chanct.core.util.BeanUtil;
import com.chanct.netsecur.constants.DBConstant;
import com.chanct.tjStat.dao.DayJobDao;
import com.chanct.tjStat.entity.CallIspVo;
import com.chanct.tjStat.entity.CheatedTypeCountVo;
import com.chanct.tjStat.entity.CheatedUserCityVo;

public class DayJobDaoImpl extends BaseDAO implements DayJobDao {
	private static Logger logger = Logger.getLogger(DayJobDaoImpl.class);
	@Override
	public boolean statCheatedUserCity(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<CheatedUserCityVo> dataCity=null;
		boolean flag=true;
		try {
			dataCity=this.getCheatedUserCityList(map);
			if(null!=dataCity&&dataCity.size()>0){
				flag=this.insertObject("com.chanct.statDayMapper.insertCheatedUserCity", dataCity, DBConstant.fraud_db)>0;
				if(flag){
					logger.info("更新各城市被骗用户数量=======:"+dataCity.size());
				}
			}
			} catch (Exception e) {
				logger.error("更新失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	@Override
	public boolean statCallIsp(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<CallIspVo> dataCity=null;
		boolean flag=true;
		try {
			dataCity=this.getCallIspVoList(map);
			if(null!=dataCity&&dataCity.size()>0){
				flag=this.insertObject("com.chanct.statDayMapper.insertCallIspDayCount", dataCity, DBConstant.fraud_db)>0;
				if(flag){
					logger.info("更新运营诈骗数量=======:"+dataCity.size());
				}
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	@Override
	public boolean statCheatedTypeCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<CheatedTypeCountVo> dataCity=null;
		boolean flag=true;
		try {
			dataCity=this.getCheatedTypeCountList(map);
			if(null!=dataCity&&dataCity.size()>0){
				flag=this.insertObject("com.chanct.statDayMapper.inserttCheatedTypeDayCount", dataCity, DBConstant.fraud_db)>0;
				if(flag){
					logger.info("更新诈骗类型数量=======:"+dataCity.size());
				}
			}
		
			} catch (Exception e) {
				logger.error("更新失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	@Override
	public List<CheatedUserCityVo> getCheatedUserCityList(
			Map<String, String> map) {
		List<CheatedUserCityVo> dataList=new ArrayList<CheatedUserCityVo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			try {
				dataList=this.selectList("com.chanct.statDayMapper.getCheatedUserCity", map,DBConstant.tj_db);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ts.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dataList;
	
	}
	@Override
	public List<CallIspVo> getCallIspVoList(Map<String, String> map) {
		List<CallIspVo> dataList=new ArrayList<CallIspVo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			try {
				dataList=this.selectList("com.chanct.statDayMapper.getCallIspDayCount", map,DBConstant.tj_db);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ts.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dataList;
	
	}
	@Override
	public List<CheatedTypeCountVo> getCheatedTypeCountList(
			Map<String, String> map) {
		List<CheatedTypeCountVo> dataList=new ArrayList<CheatedTypeCountVo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			try {
				dataList=this.selectList("com.chanct.statDayMapper.gettCheatedTypeDayCount", map,DBConstant.tj_db);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ts.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dataList;
	
	}

}
