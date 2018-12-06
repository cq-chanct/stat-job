package com.chanct.xdInsertOracle.service;

import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;

import java.util.List;
import java.util.Map;

public interface XdInsertServcie {

	public List<VoipUcmdTgj> readfile();

	public int insertOracle(List<VoipUcmdTgj> list);

}
