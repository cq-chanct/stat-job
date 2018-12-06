package com.chanct.insertVoipOracle.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chanct.cdrCount.vo.NumBerArea;
import com.chanct.core.dao.BaseDAO;
import com.chanct.fraudCdr.vo.FraudCdrDetail;
import com.chanct.insertEvilOracle.dao.InsertEvilDao;
import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.insertVoipOracle.dao.InsertVoipDao;
import com.chanct.insertVoipOracle.entity.ECdrVo;
import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;
import com.chanct.netsecur.constants.Constant;
import com.chanct.netsecur.constants.DBConstant;

public class InsertVoipDaoImpl extends BaseDAO implements InsertVoipDao {
	private static Logger logger = Logger.getLogger(InsertVoipDaoImpl.class);

	@Override
	public List<ECdrVo> getEvilByTime() {
		List<ECdrVo> retList =  null;
		retList = this.selectList("insertVoip.insertVoipOracleMapper.getVoipByTime",null,DBConstant.managerDb);
		return retList;
	}


	public int insertVoip(List<VoipUcmdTgj> list) {
		int temp=0;
		try {
			if(null!=list&&list.size()>0){
				int pointsDataLimit =Constant.POINT_LIMIT ;//限制条数
				Integer size = list.size();
				if(pointsDataLimit<size){
					int part = size/pointsDataLimit;//分批数
			        for(int i = 0; i < part; i++){
			        	List<VoipUcmdTgj> listPage = list.subList(0, pointsDataLimit);
			        	temp=this.insertObject("voipOracle.insertVoipUcmdTgjMapper.insertVoipUcmdTgj", listPage,DBConstant.oracle);
						list.subList(0, pointsDataLimit).clear();
			        }
			        if(!list.isEmpty()){
			        	temp=this.insertObject("voipOracle.insertVoipUcmdTgjMapper.insertVoipUcmdTgj", list,DBConstant.oracle);
			        }
				}else{
					temp=this.insertObject("voipOracle.insertVoipUcmdTgjMapper.insertVoipUcmdTgj", list,DBConstant.oracle);
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	
		return temp ;
	}
}
