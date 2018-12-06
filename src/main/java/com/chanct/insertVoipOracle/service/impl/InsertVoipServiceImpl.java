package com.chanct.insertVoipOracle.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;

import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.DateUtil;
import com.chanct.insertEvilOracle.entity.EvilInfo;
import com.chanct.insertEvilOracle.entity.UcmdTgj;
import com.chanct.insertVoipOracle.dao.InsertVoipDao;
import com.chanct.insertVoipOracle.dao.impl.InsertVoipDaoImpl;
import com.chanct.insertVoipOracle.entity.ECdrVo;
import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;
import com.chanct.insertVoipOracle.service.InsertVoipService;
import com.chanct.netsecur.constants.DBConstant;

public class InsertVoipServiceImpl implements InsertVoipService {
	private static Logger logger = Logger.getLogger(InsertVoipServiceImpl.class);
	private static InsertVoipDao insertVoipDao=new InsertVoipDaoImpl();

	@Override
	public void insertObject() {
		List<ECdrVo> eCdrInfoList = new ArrayList<ECdrVo>();
		List<VoipUcmdTgj> list = new ArrayList<VoipUcmdTgj>();
		eCdrInfoList = insertVoipDao.getEvilByTime();
		logger.info("voip入oracle"+eCdrInfoList.size());
		if(eCdrInfoList!=null && eCdrInfoList.size()>0){
			for (ECdrVo eCdrVo : eCdrInfoList) {
				VoipUcmdTgj ucmd = new VoipUcmdTgj();
				String callerlocaltion = eCdrVo.getCallerlocaltion();
				if(callerlocaltion!=null && callerlocaltion.length()>=9){
					callerlocaltion = callerlocaltion.substring(0, 3);
				}
				ucmd.setCityname(callerlocaltion!=null?callerlocaltion+ " ":"未知");
				String callertogatewaye164 = eCdrVo.getCallertogatewaye164();
				String calleetogatewaye164 = eCdrVo.getCalleetogatewaye164();
				if(callertogatewaye164!=null&&callertogatewaye164.length()>=11){
					callertogatewaye164 = callertogatewaye164.substring(callertogatewaye164.length()-11);
					boolean phoneLegal = isPhoneLegal(callertogatewaye164);
					if(!phoneLegal){
						callertogatewaye164 = eCdrVo.getCallertogatewaye164();
					}
				}
				if(calleetogatewaye164!=null&&calleetogatewaye164.length()>=11){
					calleetogatewaye164 = calleetogatewaye164.substring(calleetogatewaye164.length()-11);
					boolean phoneLegal = isPhoneLegal(calleetogatewaye164);
					if(!phoneLegal){
						calleetogatewaye164 =  eCdrVo.getCalleetogatewaye164();
					}
				}
				ucmd.setCallingnumber(callertogatewaye164);
				ucmd.setCallednumber(calleetogatewaye164);
				String answerTime = eCdrVo.getStarttime();
				String stopTime = eCdrVo.getStoptime();
				long stringDate = Long.parseLong(answerTime);
				Date stringtoDate = new Date(stringDate);
				String dateToString = DateUtil.dateToString(stringtoDate, DateUtil.FORMAT_ONE);
				long stringDate1 = Long.parseLong(stopTime);
				Date stringtoDate1 = new Date(stringDate1);
				String dateToString1 = DateUtil.dateToString(stringtoDate1, DateUtil.FORMAT_ONE);
				ucmd.setStarttalktime(dateToString);
				ucmd.setTalktime(eCdrVo.getHoldtime()+"");
				ucmd.setCalled_city_name(eCdrVo.getCalleelocaltion()!=null?eCdrVo.getCalleelocaltion()+ " ":"未知");
				ucmd.setStoptalktime(dateToString1);
				logger.info("Voip入oracle"+dateToString);
				list.add(ucmd);
			}
			insertVoip(list);
		}
	}

	public Boolean insertVoip(List<VoipUcmdTgj> list) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.oracle);
			if (list.size() > 0) {
				temp = insertVoipDao.insertVoip(list);
			}
			ts.commit();
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
		logger.info("Voip入oracle"+flag);
		return flag ;
	}
	
	/** 
     * 大陆号码或香港号码均可 
     */  
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {  
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);  
    }  
  
    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
  
    /** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */  
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {  
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
	
	public static void main(String argsp[]) throws SQLException{
		InsertVoipServiceImpl acd=new InsertVoipServiceImpl();
		acd.insertObject();

	}
    

	

	
	
}
