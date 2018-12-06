package com.chanct.cdrCountTotal.dao;

import java.util.Map;

import com.chanct.cdrCountTotal.vo.CdrDayCountVo;

public interface CdrTotalJobDao {
	public String getCountTimesToday(Map<String, Object> map); 
	public Boolean getCdrCountIsSave(CdrDayCountVo vo); 
	public int insertCdrCountToday(CdrDayCountVo vo);
	public int updateCdrCountToday(CdrDayCountVo vo);

}
