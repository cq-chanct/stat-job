package com.chanct.xdInsertOracle.dao.impl;

import java.util.List;

import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;
import com.chanct.xdInsertOracle.dao.XdInsertDao;
import org.apache.log4j.Logger;

import com.chanct.core.dao.BaseDAO;
import com.chanct.netsecur.constants.DBConstant;

public class XdInsertDaoImpl extends BaseDAO implements XdInsertDao {
    private static Logger logger = Logger.getLogger(XdInsertDaoImpl.class);

    public int insertVoip(List<VoipUcmdTgj> list) {
        int temp=0;
        try {
            if(null!=list&&list.size()>0){
                temp=this.insertObject("evilOracle.xdInsertOracleMapper.insertXdOracle", list,DBConstant.oracle);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return temp ;
    }

    public int insertXdForMysql(List<VoipUcmdTgj> list) {
        int  num = 0;
        try {
            if(null!=list&&list.size()>0){
               num = this.insertObject("evilOracle.xdInsertOracleMapper.insertXdMysql", list,DBConstant.tj_db);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return num;
    }
}
