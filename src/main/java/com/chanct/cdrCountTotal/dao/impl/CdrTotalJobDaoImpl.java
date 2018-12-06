package com.chanct.cdrCountTotal.dao.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.chanct.cdrCountTotal.dao.CdrTotalJobDao;
import com.chanct.cdrCountTotal.vo.CdrDayCountVo;
import com.chanct.core.dao.BaseDAO;
import com.chanct.netsecur.constants.DBConstant;

public class CdrTotalJobDaoImpl extends BaseDAO implements CdrTotalJobDao {
	private static Logger logger = Logger.getLogger(CdrTotalJobDaoImpl.class);
	@Override
	public String getCountTimesToday(Map<String, Object> map) {
		Long count1=(long) 0;
		try {
			count1=this.selectObject("count.cdrTotalMapper.getCountTimesToday", map, DBConstant.mac_db);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return String.valueOf(count1/2);
	}

	@Override
	public int insertCdrCountToday(CdrDayCountVo vo) {
		int temp=0;
		try {
			temp =this.insertObject("count.cdrTotalMapper.insertCdrCountToday",vo,DBConstant.fraud_db);
		} catch (Exception e) {
			logger.error("插入失败");
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public Boolean getCdrCountIsSave(CdrDayCountVo vo) {
		Boolean flag=false;
		Long count1=(long) 0;
		try {
			try {
				count1=this.selectObject("count.cdrTotalMapper.getCdrCountIsSave", vo, DBConstant.fraud_db);
				if(count1>0){
					flag=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	@Override
	public int updateCdrCountToday(CdrDayCountVo vo) {
		int temp=0;
		try {
			temp =this.insertObject("count.cdrTotalMapper.updateCdrCountToday",vo,DBConstant.fraud_db);
		} catch (Exception e) {
			logger.error("更新失败");
			e.printStackTrace();
		}
		return temp;
	}


}
