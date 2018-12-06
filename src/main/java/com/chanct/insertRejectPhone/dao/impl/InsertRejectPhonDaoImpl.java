package com.chanct.insertRejectPhone.dao.impl;

import java.util.List;

import com.chanct.insertRejectPhone.dao.InsertRejectPhoneDao;
import com.chanct.insertRejectPhone.entity.RejectBlackPhone;
import org.apache.log4j.Logger;

import com.chanct.core.dao.BaseDAO;
import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.netsecur.constants.Constant;
import com.chanct.netsecur.constants.DBConstant;

public class InsertRejectPhonDaoImpl extends BaseDAO implements InsertRejectPhoneDao {
	private static Logger logger = Logger.getLogger(InsertRejectPhonDaoImpl.class);

	@Override
	public List<RejectBlackPhone> getLowEvilByTime() {
		List<RejectBlackPhone> retList =  null;
		retList = this.selectList("insertRejectPhone.insertRejectPhoneMapper.getRejectPhone",DBConstant.tj_db);
		return retList;
	}

	@Override
	public int insertLowEvil(List<RejectBlackPhone> list) {
		int temp=0;
		temp=this.insertObject("insertRejectPhone.insertRejectPhoneMapper.insertRejectPhone", list,DBConstant.oracle);

		return temp ;
	}

}
