package com.chanct.insertEvilOracle.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chanct.cdrCount.vo.NumBerArea;
import com.chanct.core.dao.BaseDAO;
import com.chanct.fraudCdr.vo.FraudCdrDetail;
import com.chanct.insertEvilOracle.dao.InsertEvilDao;
import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.netsecur.constants.Constant;
import com.chanct.netsecur.constants.DBConstant;

public class InsertEvilDaoImpl extends BaseDAO implements InsertEvilDao {
	private static Logger logger = Logger.getLogger(InsertEvilDaoImpl.class);

	@Override
	public List<EvilInfo> getEvilByTime() {
		List<EvilInfo> retList =  null;
		retList = this.selectList("insertEvil.insertEvilOracleMapper.getEvilByTime",DBConstant.tj_db);
		return retList;
	}


	public int insertEvil(List<UcmdTgj> list) {
		int temp=0;
		try {
			if(null!=list&&list.size()>0){
				temp=this.insertObject("evilOracle.insertUcmdTgjMapper.insertUcmdTgj", list,DBConstant.oracle);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return temp ;
	}

	@Override
	public int updateState(List<String> list) {
		int i = 0;
			i += this.updateObject("insertEvil.insertEvilOracleMapper.updateBlackStateById",list,DBConstant.tj_db);
		return i;
	}

	@Override
	public int updateKaiTongState(List<String> list) {
		int i = 0;
		i += this.updateObject("insertEvil.insertEvilOracleMapper.updateBlackStateKTById",list,DBConstant.tj_db);
		return i;
	}

	@Override
	public List<EvilInfo> getLowEvilByTime() {
		List<EvilInfo> retList =  null;
		retList = this.selectList("insertEvil.insertEvilOracleMapper.getLowEvilByTime",DBConstant.tj_db);
		return retList;
	}

	@Override
	public int insertLowEvil(List<UcmdTgj> list) {
		int temp=0;
		try {
			if(null!=list&&list.size()>0){
				int pointsDataLimit =Constant.POINT_LIMIT ;//限制条数
				Integer size = list.size();
				if(pointsDataLimit<size){
					int part = size/pointsDataLimit;//分批数
					for(int i = 0; i < part; i++){
						List<UcmdTgj> listPage = list.subList(0, pointsDataLimit);
						temp=this.insertObject("evilOracle.insertUcmdTgjMapper.insertLowUcmdTgj", listPage,DBConstant.oracle);
						list.subList(0, pointsDataLimit).clear();
					}
					if(!list.isEmpty()){
						temp=this.insertObject("evilOracle.insertUcmdTgjMapper.insertLowUcmdTgj", list,DBConstant.oracle);
					}
				}else{
					temp=this.insertObject("evilOracle.insertUcmdTgjMapper.insertLowUcmdTgj", list,DBConstant.oracle);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return temp ;
	}
}