package com.chanct.sjhmdinterfaceForBack.dao.daoImpl;

import com.chanct.core.dao.BaseDAO;
import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;
import com.chanct.netsecur.constants.Constant;
import com.chanct.netsecur.constants.DBConstant;
import com.chanct.sjhmdinterfaceForBack.dao.InsertBackDao;
import com.chanct.sjhmdinterfaceForBack.entity.BlackNum;
import com.chanct.sjhmdinterfaceForBack.entity.FeedBack;
import com.chanct.sjhmdinterfaceForBack.job.insertForBlack;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/16.
 */
public class InsertBackDaoImpl  extends BaseDAO implements InsertBackDao {
	private static Logger logger = Logger.getLogger(InsertBackDaoImpl.class);
    @Override
    public List<FeedBack> queryInterFace() {
    	FeedBack feedBack = new FeedBack();
    	feedBack.setZxzt("0");
    	List<FeedBack> list =  null;
        list = this.selectList("interfaceForBlack.sjhmdinterfaceForBack.queryInterFace",feedBack, DBConstant.oracle);

        return list;
    }

    @Override
    public int updateInterFace(Map<String, Object> map) {
        return this.updateObject("interfaceForBlack.sjhmdinterfaceForBack.updateInterFace",map,DBConstant.oracle);
    }

	@Override
	public int insertSjhmdResult(List<FeedBack> list) {
		int temp=0;
		try {
			if(null!=list && list.size()>0){
				List<FeedBack> listPage = list.subList(0, list.size());
				temp = this.insertObject("interfaceForBlack.sjhmdinterfaceForBack.insertSjhmdResult", listPage,DBConstant.oracle);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return temp ;
	}

	@Override
    public int insertBlackNum(Map<String, Object> map) {
    	int flag = 0;
		try {
			flag =  this.insertObject("interfaceForBlack.sjhmdinterfaceForBack.insertBlackNum",map,DBConstant.managerDb);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return flag;
    	
    }

	@Override
	public BlackNum queryblackone(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.selectObject("interfaceForBlack.sjhmdinterfaceForBack.queryblackone",map,DBConstant.managerDb);
	}


	@Override
	public int updateOldBlackNum(BlackNum black) {
		int flag = 0;
		try {
			flag =  this.updateObject("interfaceForBlack.sjhmdinterfaceForBack.updateOldBlackNum",black,DBConstant.managerDb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
}
}