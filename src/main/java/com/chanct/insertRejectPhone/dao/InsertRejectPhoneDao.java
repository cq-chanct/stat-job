package com.chanct.insertRejectPhone.dao;

import java.util.List;

import com.chanct.insertRejectPhone.entity.RejectBlackPhone;

public interface InsertRejectPhoneDao {

	public List<RejectBlackPhone> getLowEvilByTime();

	/*public int insertEvil(List<UcmdTgj> list);


	public int updateState(List<String> list);

	public int updateKaiTongState(List<String> list);

	public List<EvilInfo> getLowEvilByTime();*/

	public int insertLowEvil(List<RejectBlackPhone> list);

}
