package com.chanct.tjStat.dao;

import java.util.List;
import java.util.Map;

import com.chanct.tjStat.entity.CallTotalCountVo;
import com.chanct.tjStat.entity.CheatedTypeCountVo;
import com.chanct.tjStat.entity.CheatedUserCountVo;
import com.chanct.tjStat.entity.DynamicFlowVo;

public interface HourJobDao {
	public boolean statCheatedAreaUserCount(Map<String,String>map);
	public boolean statCheatedTypeCount(Map<String,String>map);
	public boolean statCheatedUserCity(Map<String,String>map);
	public boolean statCheatedAreaUserTop(Map<String,String>map);
	public boolean statDynamicFlow(Map<String,String>map);
	public boolean statCallTotalCount(Map<String,String>map);
	public List<CheatedUserCountVo> getCheatedUserCountList(Map<String,String>map);
	
	public List<CheatedTypeCountVo> getCheatedTypeCountList(Map<String,String>map);
    public List<DynamicFlowVo> getDynamicFlowList(Map<String,String>map);
    public List<CallTotalCountVo> getCallTotalCountList(Map<String,String>map);
}
