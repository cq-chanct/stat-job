package com.chanct.insertLowInterfaceOracle.dao.impl;

import java.util.List;

import com.chanct.insertLowInterfaceOracle.dao.InsertLowInterfaceDao;
import org.apache.log4j.Logger;

import com.chanct.core.dao.BaseDAO;
import com.chanct.insertEvilOracle.dao.InsertEvilDao;
import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.netsecur.constants.Constant;
import com.chanct.netsecur.constants.DBConstant;

public class InsertLowInterfaceDaoImpl extends BaseDAO implements InsertLowInterfaceDao {
	private static Logger logger = Logger.getLogger(InsertLowInterfaceDaoImpl.class);

	@Override
	public List<EvilInfo> getLowEvilByTime() {
		List<EvilInfo> retList =  null;
		retList = this.selectList("insertLowInterface.insertLowInterfaceOracleMapper.getEvilByTime",DBConstant.tj_db);
		return retList;
	}

	@Override
	public int insertLowEvil(List<UcmdTgj> list) {
		int temp=0;
		temp=this.insertObject("insertLowInterface.insertLowInterfaceOracleMapper.insertLowUcmdTgj", list,DBConstant.oracle);

		return temp ;
	}

}
