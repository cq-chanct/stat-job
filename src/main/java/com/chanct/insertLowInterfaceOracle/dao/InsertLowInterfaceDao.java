package com.chanct.insertLowInterfaceOracle.dao;

import java.util.List;
import java.util.Map;

import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;

public interface InsertLowInterfaceDao {

	public List<EvilInfo> getLowEvilByTime();

	/*public int insertEvil(List<UcmdTgj> list);


	public int updateState(List<String> list);

	public int updateKaiTongState(List<String> list);

	public List<EvilInfo> getLowEvilByTime();*/

	public int insertLowEvil(List<UcmdTgj> list);

}
