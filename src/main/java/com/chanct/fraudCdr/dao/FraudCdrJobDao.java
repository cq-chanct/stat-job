package com.chanct.fraudCdr.dao;

import java.util.List;

import com.chanct.fraudCdr.vo.FraudCdrDetail;

public interface FraudCdrJobDao {

	public int insertEvil(List<FraudCdrDetail> list);

}
