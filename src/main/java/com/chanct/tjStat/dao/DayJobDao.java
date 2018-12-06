package com.chanct.tjStat.dao;

import java.util.List;
import java.util.Map;

import com.chanct.tjStat.entity.CallIspVo;
import com.chanct.tjStat.entity.CheatedTypeCountVo;
import com.chanct.tjStat.entity.CheatedUserCityVo;

public interface DayJobDao {
	public boolean statCheatedUserCity(Map<String,String>map);
	public boolean statCallIsp(Map<String,String>map);
	public boolean statCheatedTypeCount(Map<String,String>map);//天统计各类型诈骗数量，不分地域
	
	public List<CheatedUserCityVo> getCheatedUserCityList(Map<String,String>map);
	public List<CallIspVo> getCallIspVoList(Map<String,String>map);
	public List<CheatedTypeCountVo> getCheatedTypeCountList(Map<String,String>map);

}
