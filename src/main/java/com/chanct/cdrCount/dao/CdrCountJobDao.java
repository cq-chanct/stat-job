package com.chanct.cdrCount.dao;

import java.util.List;

import com.chanct.cdrCount.vo.EvilDetail;
import com.chanct.cdrCount.vo.IspVo;
import com.chanct.cdrCount.vo.NumBerArea;

public interface CdrCountJobDao {
	public int insertEvil(List<EvilDetail> EvilDetailDeep);
	public List<NumBerArea> getAreaList(NumBerArea num);
	public List<NumBerArea> getAreaListMobile(NumBerArea num);
	public List<NumBerArea> getCountryPhone(NumBerArea num);
	public List<IspVo> getIspList();

	public List<String> getWhiteList();
}
