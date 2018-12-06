package com.chanct.netsecur.cronjob.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chanct.core.exception.AppISMPException;
import com.chanct.netsecur.cronjob.vo.TCronjob;


public interface ICronjobDAO {
	
	public List<TCronjob> getValidCronjobList(@Param(value="condition")String condition)
			throws AppISMPException ;
	
	public boolean 	updateByID(TCronjob cronjob) throws AppISMPException ;
	
}
