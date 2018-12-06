package com.chanct.insertFraudOracle.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.chanct.core.dao.BaseDAO;
import com.chanct.insertFraudOracle.dao.InsertFraudDao;
import com.chanct.insertFraudOracle.entity.FraudEvilInfo;
import com.chanct.insertFraudOracle.entity.FraudUcmdTgj;
import com.chanct.netsecur.constants.Constant;
import com.chanct.netsecur.constants.DBConstant;

public class InsertFraudDaoImpl extends BaseDAO implements InsertFraudDao {
	private static Logger logger = Logger.getLogger(InsertFraudDaoImpl.class);

	@Override
	public int insertEvil(List<FraudUcmdTgj> list) {
		int temp=0;
		try {
			if(null!=list&&list.size()>0){
				int pointsDataLimit =Constant.POINT_LIMIT ;//限制条数
				Integer size = list.size();
				if(pointsDataLimit<size){
					int part = size/pointsDataLimit;//分批数
			        for(int i = 0; i < part; i++){
			        	List<FraudUcmdTgj> listPage = list.subList(0, pointsDataLimit);
			        	temp=this.insertObject("evilOracle.insertFraudUcmdTgjMapper.insertUcmdTgj", listPage,DBConstant.oracle);
						list.subList(0, pointsDataLimit).clear();
			        }
			        if(!list.isEmpty()){
			        	temp=this.insertObject("evilOracle.insertFraudUcmdTgjMapper.insertUcmdTgj", list,DBConstant.oracle);
			        }
				}else{
					temp=this.insertObject("evilOracle.insertFraudUcmdTgjMapper.insertUcmdTgj", list,DBConstant.oracle);
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	
		return temp ;
	}

	@Override
	public List<FraudEvilInfo> getEvilByTime() {
		List<FraudEvilInfo> retList =  null;
		retList = this.selectList("insertEvil.insertFraudOracleMapper.getEvilByTime",null,DBConstant.managerDb);
		return retList;
	}

}
