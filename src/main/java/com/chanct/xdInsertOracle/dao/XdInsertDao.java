package com.chanct.xdInsertOracle.dao;

import java.util.List;
import java.util.Map;

import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;
import com.chanct.tjStat.entity.CallTotalCountVo;
import com.chanct.tjStat.entity.CheatedTypeCountVo;
import com.chanct.tjStat.entity.CheatedUserCountVo;
import com.chanct.tjStat.entity.DynamicFlowVo;

public interface XdInsertDao {

    public int insertVoip(List<VoipUcmdTgj> list);

    int insertXdForMysql(List<VoipUcmdTgj> list);

}
