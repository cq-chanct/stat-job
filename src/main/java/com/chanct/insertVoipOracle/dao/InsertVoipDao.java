package com.chanct.insertVoipOracle.dao;

import java.util.List;
import java.util.Map;

import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.insertVoipOracle.entity.ECdrVo;
import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;

public interface InsertVoipDao {

	public List<ECdrVo> getEvilByTime();

	public int insertVoip(List<VoipUcmdTgj> list);

}
