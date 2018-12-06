package com.chanct.tjStat.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;

import com.chanct.core.dao.BaseDAO;
import com.chanct.core.util.BeanUtil;
import com.chanct.netsecur.constants.DBConstant;
import com.chanct.tjStat.dao.HourJobDao;
import com.chanct.tjStat.entity.CallTotalCountVo;
import com.chanct.tjStat.entity.CheatedTypeCountVo;
import com.chanct.tjStat.entity.CheatedUserCountVo;
import com.chanct.tjStat.entity.CheatedUserTopVo;
import com.chanct.tjStat.entity.DynamicFlowVo;

public class HourJobDaoImpl  extends BaseDAO implements HourJobDao {
	private static Logger logger = Logger.getLogger(HourJobDaoImpl.class);
	@Override
	public boolean statCheatedAreaUserCount(Map<String, String> map) {
		List<CheatedUserCountVo> dataCity=null;
		List<CheatedUserCountVo> dataProv=null;
		List<CheatedUserCountVo> dataNation=null;
		boolean flag=true;
		try {
			map.put("statArea", "city");
			dataCity=this.getCheatedUserCountList(map);
			if(null!=dataCity&&dataCity.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedCityUserCount", dataCity, DBConstant.fraud_db)>0;
				logger.info("更新各城市被骗用户数量小时=======:"+dataCity.size());
			}
			map.put("statArea", "prov");
			dataProv=this.getCheatedUserCountList(map);
			if(null!=dataProv&&dataProv.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedProvUserCount", dataProv, DBConstant.fraud_db)>0;
				logger.info("更新各省份被骗用户数量小时=======:"+dataProv.size());
			}
			map.put("statArea", "nation");
			dataNation=this.getCheatedUserCountList(map);
			if(null!=dataNation&&dataNation.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedNationUserCount", dataNation, DBConstant.fraud_db)>0;
				logger.info("更新各国家被骗用户数量小时=======:"+dataNation.size());
			}
			} catch (Exception e) {
				logger.error("异常信息=======:"+e.getMessage());
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean statCheatedTypeCount(Map<String, String> map) {
		List<CheatedTypeCountVo> dataCity=null;
		List<CheatedTypeCountVo> dataProv=null;
		List<CheatedTypeCountVo> dataNation=null;
		boolean flag=true;
		try {
			map.put("statArea", "city");
			dataCity=this.getCheatedTypeCountList(map);
			if(null!=dataCity&&dataCity.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedCityTypeCount", dataCity, DBConstant.fraud_db)>0;
				logger.info("更新各城市各诈骗类型数量小时=======:"+dataCity.size());
			}
			map.put("statArea", "prov");
			dataProv=this.getCheatedTypeCountList(map);
			if(null!=dataProv&&dataProv.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedProvTypeCount", dataProv, DBConstant.fraud_db)>0;
				logger.info("更新各省份各诈骗类型数量小时=======:"+dataProv.size());
			}
			map.put("statArea", "nation");
			dataNation=this.getCheatedTypeCountList(map);
			if(null!=dataNation&&dataNation.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedNationTypeCount", dataNation, DBConstant.fraud_db)>0;
				logger.info("更新各国家各诈骗类型数量小时=======:"+dataProv.size());
				
			}
			} catch (Exception e) {
				logger.error("异常信息=======:"+e.getMessage());
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean statCheatedUserCity(Map<String, String> map) {
		return false;
	}

	@Override
	public boolean statCheatedAreaUserTop(Map<String, String> map) {
		List<CheatedUserTopVo> dataCity=null;
		List<CheatedUserTopVo> dataProv=null;
		List<CheatedUserTopVo> dataNation=null;
		boolean flag=true;
		try {
			map.put("statArea", "city");
			dataCity=this.selectList("com.chanct.statMapper.getCheatedTop", map);
			if(null!=dataCity&&dataCity.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedCityTop", dataCity, DBConstant.fraud_db)>0;
				logger.info("更新各城市各诈骗类型数量小时=======:"+dataCity.size());
			}
			map.put("statArea", "prov");
			dataProv=this.selectList("com.chanct.statMapper.getCheatedTop", map);
			if(null!=dataProv&&dataProv.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedProvTop", dataProv, DBConstant.fraud_db)>0;
				logger.info("更新各省份各诈骗类型数量小时=======:"+dataProv.size());
			}
			map.put("statArea", "nation");
			dataNation=this.selectList("com.chanct.statMapper.getCheatedTop", map);
			if(null!=dataNation&&dataNation.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCheatedNationTop", dataNation, DBConstant.fraud_db)>0;
				logger.info("更新各国家诈骗类型数量小时=======:"+dataNation.size());
			}
			} catch (Exception e) {
				logger.error("异常信息=======:"+e.getMessage());
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean statDynamicFlow(Map<String, String> map) {
		List<DynamicFlowVo> dataCity=null;
		List<DynamicFlowVo> dataProv=null;
		List<DynamicFlowVo> dataNation=null;
		boolean flag=true;
		try {
			map.put("statArea", "city");
			dataCity=this.getDynamicFlowList(map);
			if(null!=dataCity&&dataCity.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertDynamicCityFlowVo", dataCity, DBConstant.fraud_db)>0;
				logger.info("更新各城市流向数据小时=======:"+dataCity.size());
			}
			map.put("statArea", "prov");
			dataProv=this.getDynamicFlowList(map);
			if(null!=dataProv&&dataProv.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertDynamicProvFlowVo", dataProv, DBConstant.fraud_db)>0;
				logger.info("更新各省份流向数据小时=======:"+dataProv.size());
			}
			map.put("statArea", "nation");
			dataNation=this.getDynamicFlowList(map);
			if(null!=dataNation&&dataNation.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertDynamicNationFlowVo", dataNation, DBConstant.fraud_db)>0;
				logger.info("更新各国家流向数据小时=======:"+dataNation.size());
			}
			} catch (Exception e) {
				logger.error("异常信息=======:"+e.getMessage());
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean statCallTotalCount(Map<String, String> map) {
		List<CallTotalCountVo> dataTotal=null;
		boolean flag=true;
		try {
			dataTotal=this.getCallTotalCountList(map);
			if(null!=dataTotal&&dataTotal.size()>0){
				flag=this.insertObject("com.chanct.statMapper.insertCallTotalCount", dataTotal, DBConstant.fraud_db)>0;
				logger.info("更新每小时电话数量=======:"+dataTotal.size());
			}
			} catch (Exception e) {
				logger.error("异常信息=======:"+e.getMessage());
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public List<CheatedUserCountVo> getCheatedUserCountList(
			Map<String, String> map) {
		List<CheatedUserCountVo> dataList=new ArrayList<CheatedUserCountVo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			try {
				dataList=this.selectList("com.chanct.statMapper.getCheatedAreaUserCount", map,DBConstant.tj_db);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ts.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dataList;
	
	}

	@Override
	public List<CheatedTypeCountVo> getCheatedTypeCountList(
			Map<String, String> map) {
		List<CheatedTypeCountVo> dataList=new ArrayList<CheatedTypeCountVo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			try {
				dataList=this.selectList("com.chanct.statMapper.getCheatedTypeUserCount", map,DBConstant.tj_db);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ts.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dataList;
	
	}

	@Override
	public List<DynamicFlowVo> getDynamicFlowList(Map<String, String> map) {
		List<DynamicFlowVo> dataList=new ArrayList<DynamicFlowVo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			try {
				dataList=this.selectList("com.chanct.statMapper.getDynamicFlowVo", map,DBConstant.tj_db);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ts.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dataList;
	
	}

	@Override
	public List<CallTotalCountVo> getCallTotalCountList(Map<String, String> map) {
		List<CallTotalCountVo> dataList=new ArrayList<CallTotalCountVo>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			try {
				dataList=this.selectList("com.chanct.statMapper.getCallTotalCount", map,DBConstant.tj_db);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ts.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dataList;
	
	}

}
