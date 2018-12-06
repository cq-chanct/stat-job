package com.chanct.sjhmdinterfaceForBack.service.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chanct.sjhmdinterfaceForBack.entity.FeedBack;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;

import com.chanct.core.util.BeanUtil;
import com.chanct.netsecur.constants.DBConstant;
import com.chanct.sjhmdinterfaceForBack.dao.InsertBackDao;
import com.chanct.sjhmdinterfaceForBack.dao.daoImpl.InsertBackDaoImpl;
import com.chanct.sjhmdinterfaceForBack.entity.BlackNum;
import com.chanct.sjhmdinterfaceForBack.service.InsertBackService;

/**
 * Created by Administrator on 2017/9/16.
 */
public class InsertBackServiceImpl implements InsertBackService{
	private static Logger logger = Logger.getLogger(InsertBackServiceImpl.class);
    private static InsertBackDao dao = new InsertBackDaoImpl();
    
    
	@Override
	public void insertObject() {
		 List<FeedBack>  list = new ArrayList<FeedBack>();
		 list = queryInterFace();        //查询oracle黑名单
		List<BlackNum> listBlack = new ArrayList<>();
		for(FeedBack number: list){
			BlackNum b = new BlackNum();
			logger.info("拦截时间"+number.getGp());
			b.setInterceptTime(number.getGp());
			b.setIssueReason(number.getLhyy());
			b.setIssuePeople(number.getBallyxm());
			b.setIssuePhone(number.getBallydh());
			b.setIssueDepartment(number.getSsgajg());
			b.setPhonenum(number.getHm());
			listBlack.add(b);
		}
		 for (BlackNum blackNum : listBlack) {
			 String phone_num = blackNum.getPhonenum();
			 int interceptTime =  blackNum.getInterceptTime();
			 String issueReason = blackNum.getIssueReason();
			 String issuePeople = blackNum.getIssuePeople();
			 String issuePhone = blackNum.getIssuePhone();
			 String issueDepartment = blackNum.getIssueDepartment();
			 logger.info("插入黑名单号码："+phone_num+"时间"+interceptTime);
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("phone_num", phone_num);
			 map.put("interceptTime",interceptTime);
			 map.put("issueReason",issueReason);
			 map.put("issuePeople",issuePeople);
			 map.put("issuePhone",issuePhone);
			 map.put("issueDepartment",issueDepartment);
			 logger.info("map"+map);
			 BlackNum black = queryblackone(map);    //判断mysql库里是否已经有次黑名单

				if(black!=null){
					if("3".equals(black.getStatus())||"4".equals(black.getStatus())){
						updateOldBlackNum(black);     //更新原mysql
					}
				}else{
					insertBlackNum(map);  //     mysql  插入一条黑名单
				}
			 logger.error("此处修改sjhmd");
				updateInterFace(map);      //更新oracle黑名单状态，为已获取
		}
		insertSjhmdResult(list);
	}
	private BlackNum queryblackone(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		BlackNum bnum = new BlackNum();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.managerDb);
			bnum = dao.queryblackone(map);
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return bnum ;
	}
	private List<FeedBack> queryInterFace() {
		List<FeedBack> temp = new ArrayList<FeedBack>();
		Transaction ts = null;// 事物提交
		try {
			ts = BeanUtil.newTransaction(DBConstant.oracle);
			temp = dao.queryInterFace();
			ts.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return temp ;
	}
	public Boolean insertBlackNum( Map<String, Object> map) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.managerDb);
			temp = dao.insertBlackNum(map);
			ts.commit();
			// 数据库插入成功后删除该文件
			if (temp==0) {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag ;
	}
	public Boolean updateOldBlackNum(BlackNum black) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.managerDb);
			if (black!=null) {
				temp = dao.updateOldBlackNum(black);

			}
			ts.commit();
			// 数据库插入成功后删除该文件
			if (temp==0) {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag ;
	}
	public Boolean updateInterFace(Map<String, Object> map) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.oracle);
			temp = dao.updateInterFace(map);

			ts.commit();
			// 数据库插入成功后删除该文件
			if (temp==0) {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag ;
	}
	public Boolean insertSjhmdResult(List<FeedBack> list) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.oracle);
			temp = dao.insertSjhmdResult(list);

			ts.commit();
			// 数据库插入成功后删除该文件
			if (temp==0) {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				ts.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag ;
	}
	public static void main(String argsp[]) throws SQLException{
		InsertBackServiceImpl acd=new InsertBackServiceImpl();
		acd.insertObject();

	}
	
}
