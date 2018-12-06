package com.chanct.netsecur.cronjob.dao.impl;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chanct.core.dao.BaseDAO;
import com.chanct.core.exception.AppISMPException;
import com.chanct.netsecur.constants.DBConstant;
import com.chanct.netsecur.cronjob.dao.ICronjobDAO;
import com.chanct.netsecur.cronjob.vo.TCronjob;


public class CronjobDAOImpl extends BaseDAO implements ICronjobDAO{

	@Override
	public List<TCronjob> getValidCronjobList(@Param(value="condition")String condition)
			throws AppISMPException {		
		return this.selectList("cronjob.TCronjobMapper.selectValidCronjob",condition);	
	}
	private final String dbIndex = DBConstant.tj_db;
	@Override
	public boolean 	updateByID(TCronjob cronjob) throws AppISMPException {
		this.updateObject("cronjob.TCronjobMapper.updateByPK", cronjob,dbIndex);
		return true;
	}
	
}
