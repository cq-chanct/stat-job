package com.chanct.tjStat.service;

import java.util.Map;

public interface HourJobServcie {
	public boolean statCheatedAreaUserCount(Map<String,String>map);
	public boolean statCheatedTypeCount(Map<String,String>map);
	public boolean statCheatedUserCity(Map<String,String>map);
	public boolean statCheatedAreaUserTop(Map<String,String>map);
	public boolean statDynamicFlow(Map<String,String>map);
	public boolean statCallTotalCount(Map<String,String>map);

}
