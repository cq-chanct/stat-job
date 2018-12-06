package com.chanct.fraudCdr.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.chanct.core.dao.BaseDAO;
import com.chanct.fraudCdr.dao.FraudCdrJobDao;
import com.chanct.fraudCdr.vo.FraudCdrDetail;
import com.chanct.netsecur.constants.Constant;
import com.chanct.netsecur.constants.DBConstant;

public class FraudCdrJobDaoImpl extends BaseDAO implements FraudCdrJobDao {
	private static Logger logger = Logger.getLogger(FraudCdrJobDaoImpl.class);

	@Override
	public int insertEvil(List<FraudCdrDetail> list) {
		int temp=0;
		try {
			if(null!=list&&list.size()>0){
				int pointsDataLimit =Constant.POINT_LIMIT ;//限制条数
				Integer size = list.size();
				if(pointsDataLimit<size){
					int part = size/pointsDataLimit;//分批数
			        for(int i = 0; i < part; i++){
			        	List<FraudCdrDetail> listPage = list.subList(0, pointsDataLimit);
			        	temp=this.insertObject("fraudcdr.fraudCdrMapper.insertCdr", listPage,DBConstant.managerDb);
						list.subList(0, pointsDataLimit).clear();
			        }
			        if(!list.isEmpty()){
			        	temp=this.insertObject("fraudcdr.fraudCdrMapper.insertCdr", list,DBConstant.managerDb);
			        }
				}else{
					temp=this.insertObject("fraudcdr.fraudCdrMapper.insertCdr", list,DBConstant.managerDb);
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	
		return temp ;
	}

}
