package com.chanct.insertFraudOracle.dao;

import java.util.List;

import com.chanct.insertFraudOracle.entity.FraudEvilInfo;
import com.chanct.insertFraudOracle.entity.FraudUcmdTgj;

public interface InsertFraudDao {

	public int insertEvil(List<FraudUcmdTgj> list);

	public List<FraudEvilInfo> getEvilByTime();

}
