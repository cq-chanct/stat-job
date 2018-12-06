package com.chanct.cdrCountTotal.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chanct.cdrCountTotal.dao.CdrTotalJobDao;
import com.chanct.cdrCountTotal.dao.impl.CdrTotalJobDaoImpl;
import com.chanct.cdrCountTotal.service.CdrTotalJobService;
import com.chanct.core.util.DateUtil;
import com.chanct.netsecur.constants.DBConstant;



public class CdrTotalJobServiceImpl implements CdrTotalJobService{
	public CdrTotalJobDao cdrDao=new CdrTotalJobDaoImpl();
	private static Logger logger = Logger.getLogger(CdrTotalJobServiceImpl.class);
	//	@Override
	//	public void insertObject() {
	//		logger.info("定时任务-统计每日话单量 ：开始执行");
	//		Transaction ts = null;// 事物提交
	//		String cdrCountToday="";
	//		Boolean flag=false;
	//		Map map=new HashMap();
	//		CdrDayCountVo vo=new CdrDayCountVo();
	//		try {
	//			ts = BeanUtil.newTransaction(DBConstant.fraud_db);
	//			cdrCountToday=cdrDao.getCountTimesToday(map);
	//			vo.setStatDate(DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT));
	//			vo.setMachineNum(DBConstant.mac_db);
	//			vo.setCdrCount(cdrCountToday);
	//			flag=cdrDao.getCdrCountIsSave(vo);
	//			if(flag){
	//				cdrDao.updateCdrCountToday(vo);
	//			}else{
	//				cdrDao.insertCdrCountToday(vo);
	//			}
	//			ts.commit();
	//			logger.info("定时任务-统计每日话单量 ： 执行");
	//		} catch (Exception e) {
	//			logger.error("cdr数量更新失败");
	//			e.printStackTrace();
	//			try {
	//				ts.rollback();
	//			} catch (SQLException e1) {
	//				e1.printStackTrace();
	//			}
	//		}
	//		
	//	}


	@Override
	public void insertObject() {
		logger.info("定时任务-统计每日话单量 ：开始执行");
		Map<String, Object> map=new HashMap<String, Object>();
		String tableName="CallRecord_"+DateUtil.getCurrDate(DateUtil.FILE_DATE_DIR_FORMAT);
		map.put("tableName", tableName);
		String cdrTotal = cdrDao.getCountTimesToday(map);
		
		String nowDate = DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT);
		
		try {
			File fileResultDate = new File(DBConstant.FTP_CONFIG_CDR_TOTAL_PATH);
			if(!fileResultDate.isDirectory()){
				fileResultDate.mkdirs();
			}
			String fileName = DBConstant.FTP_CONFIG_CDR_TOTAL_PATH + "CDR_TOTAL_" + DBConstant.mac_db + "_" + nowDate;
			File file = new File(fileName);
			if(file.exists()){
				file.delete();
			}
			OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
			ow.write(nowDate + "," + cdrTotal + "," + DBConstant.mac_db);
			ow.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static  void main(String args[]){
		CdrTotalJobServiceImpl co=new CdrTotalJobServiceImpl();
		co.insertObject();
	}

}
