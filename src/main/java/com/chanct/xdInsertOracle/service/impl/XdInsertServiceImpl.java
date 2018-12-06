package com.chanct.xdInsertOracle.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chanct.core.util.PropertyUtil;
import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;
import com.chanct.xdInsertOracle.dao.XdInsertDao;
import com.chanct.xdInsertOracle.dao.impl.XdInsertDaoImpl;
import com.chanct.xdInsertOracle.service.XdInsertServcie;
import com.chanct.xdInsertOracle.utils.ExcelUtil;
import com.chanct.xdInsertOracle.utils.FilePathName;
import com.chanct.xdInsertOracle.utils.FileTools;
import org.apache.ibatis.transaction.Transaction;

import com.chanct.core.util.BeanUtil;
import com.chanct.netsecur.constants.DBConstant;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

public class XdInsertServiceImpl implements XdInsertServcie {

	private static Logger logger = Logger.getLogger(XdInsertServiceImpl.class);
	private static XdInsertDao dao = new XdInsertDaoImpl();
	private String path = PropertyUtil.getConfig("xd.file.up.path");
	private String bak =PropertyUtil.getConfig("xd.file.bak.path");
	private String err=PropertyUtil.getConfig("xd.file.err.path");
	private static String[] ecdr_fields=PropertyUtil.getConfig("ecdr.field").split(",");
	@Override
	public List<VoipUcmdTgj> readfile() {
		List<String> fields=new ArrayList<String>();
		for(String field:ecdr_fields){
			fields.add(field);
		}
		File file = new File(path);
		File [] dir = file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith("xlsx")) {
					return true;
				}
				return false;
			}
		});
		//取得文件列表
		Workbook wb = null;
		ExcelUtil eu = new ExcelUtil();
		FileTools fts =  new FileTools();
		List<VoipUcmdTgj> list = new ArrayList<VoipUcmdTgj>();
		logger.info("文件个数"+dir.length);
		for (int i = 0; i<dir.length;i++){
			File files = dir[i];
			try {
				/*list = FileTools.FileToList(path, files.getName(), VoipUcmdTgj.class, fields, "utf-8", ",", "\r\n", 0);//读取文件*/
				InputStream is = new FileInputStream(dir[i].getAbsoluteFile());
				list = eu.readFile(is);
				for(int j = 0;j< list.size();j++){
					if(list.get(j).getCallingnumber().substring(0,3).equals("1344")||
							list.get(j).getCallednumber().substring(list.get(j).getCallednumber().length()-11,list.get(j).getCallednumber().length()-7).equals("1344")){
					        list.remove(j);
					}
				}
				logger.info("list:"+list);
                insertXd(list);
                insertXdMysql(list);
				if(list.size() > 0){
					FileTools.MoveFile(files, bak+new FilePathName().getLIST_FILEPATH());
					logger.info("数据入库成功:"+list.size());
				}else{
					FileTools.MoveFile(files, err+new FilePathName().getLIST_FILEPATH());
					logger.info("数据入库成功:"+list.size());
				}

			}catch (Exception e){
				logger.error(e.getMessage());
			}
		}
		return list;
	}

	@Override
	public int insertOracle(List<VoipUcmdTgj> list) {
		return 0;
	}
	public Boolean insertXd(List<VoipUcmdTgj> list) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.oracle);
			if (list.size() > 0) {
				//temp = dao.insertXdForMysql(list);
				temp = dao.insertVoip(list);
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
	public Boolean insertXdMysql(List<VoipUcmdTgj> list) {
		Boolean flag = true;
		Transaction ts = null;// 事物提交
		try {
			int temp=0;
			ts = BeanUtil.newTransaction(DBConstant.tj_db);
			if (list.size() > 0) {
				temp = dao.insertXdForMysql(list);
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
	public static void main(String[] args){
		List<VoipUcmdTgj> list = new ArrayList<VoipUcmdTgj>();
		XdInsertServiceImpl x = new XdInsertServiceImpl();
		list = x.readfile();
		System.out.println("list:"+list.get(0).toString());
	}

}
