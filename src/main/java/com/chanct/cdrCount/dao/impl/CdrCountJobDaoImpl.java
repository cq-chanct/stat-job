package com.chanct.cdrCount.dao.impl;

import java.util.List;

import com.chanct.cdrCount.dao.CdrCountJobDao;
import com.chanct.cdrCount.vo.EvilDetail;
import com.chanct.cdrCount.vo.IspVo;
import com.chanct.cdrCount.vo.NumBerArea;
import com.chanct.core.dao.BaseDAO;
import com.chanct.netsecur.constants.DBConstant;

public class CdrCountJobDaoImpl extends BaseDAO implements CdrCountJobDao {
	@Override
	public int insertEvil(List<EvilDetail> list) {
		return this.insertObject("count.cdrCountMapper.insertEvil", list, DBConstant.tj_db);
	}

	@Override
	public List<NumBerArea> getAreaList(NumBerArea num) {
		List<NumBerArea> retList =  null;
		retList = this.selectList("count.cdrCountMapper.getPhoneArea", num,DBConstant.tj_db);
		return retList;
	}
	public List<IspVo> getIspList() {
		List<IspVo> retList =  null;
		retList = this.selectList("count.cdrCountMapper.getIspList", null,DBConstant.tj_db);
		return retList;
	}
	@Override
	public List<NumBerArea> getAreaListMobile(NumBerArea num) {
		List<NumBerArea> retList =  null;
		retList = this.selectList("count.cdrCountMapper.getPhoneAreaMobile", num,DBConstant.tj_db);
		return retList;
	}
	@Override
	public List<NumBerArea> getCountryPhone(NumBerArea num) {
		List<NumBerArea> retList =  null;
		retList = this.selectList("count.cdrCountMapper.getPhoneCountry", num,DBConstant.tj_db);
		return retList;
	}

	@Override
	public List<String> getWhiteList() {
		List<String> list = this.selectList("getWhiteList");
		return list;
	}
}
