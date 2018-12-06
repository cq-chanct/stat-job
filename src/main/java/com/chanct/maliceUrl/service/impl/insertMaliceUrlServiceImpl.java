package com.chanct.maliceUrl.service.impl;

import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.PropertyUtil;
import com.chanct.interfaceProcess.entity.InterfaceProcess;
import com.chanct.maliceUrl.dao.dao.insertMaliceUrlDaoImpl;
import com.chanct.maliceUrl.dao.insertMaliceUrlDao;
import com.chanct.maliceUrl.entity.*;
import com.chanct.maliceUrl.service.insertMaliceUrlService;
import com.chanct.maliceUrl.util.WriteTxt;
import com.chanct.maliceUrl.util.readFile;
import com.chanct.netsecur.constants.DBConstant;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FilenameFilter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class insertMaliceUrlServiceImpl implements insertMaliceUrlService {
    private static Logger logger = Logger.getLogger(insertMaliceUrlServiceImpl.class);
    private static String path = PropertyUtil.getConfig("maliceUrl.path");
    private static String resuktPath = PropertyUtil.getConfig("maliceUrl.result.path");
    static  readFile read = new readFile();
   public static insertMaliceUrlDao dao = new insertMaliceUrlDaoImpl();
    //@Override
    public   int insert() {
        File file = new File(path);
        File [] dir = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.startsWith("url")) {
                    return true;
                }
                return false;
            }
        });

        File [] dirGjf = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.startsWith("gjf")) {
                    return true;
                }
                return false;
            }
        });
        List<maliceUrlInterface> list = new ArrayList<>();
        for(int i=0;i<dir.length;i++){
            list.addAll(read.readFileByLinesOnMalice(dir[i].getAbsoluteFile()));
        }


        int i = isertMalice(list);   //恶意url插入mysql
        isertOracleUrl(list);   //恶意插入oracle
        List<maliceUrlResult> listResult = new ArrayList<>();
        listResult = dao.queryUrlResult();
        logger.info("listttt:"+listResult.size());
        insertUrlResult(listResult);
        WriteTxt writeTxt = new WriteTxt();
        if(listResult.size()>0){
            writeTxt.writeResult(resuktPath,listResult);
        }
        List<UrlInterface>  listGjf = new ArrayList<>();
        for(int j=0;j<dirGjf.length;j++){
            listGjf.addAll(read.readFileByLinesOnUrl(dirGjf[j].getAbsoluteFile()));
        }
        return i;
    }
    public  void insertMalice(){
       try{
           //数据推送公安
           List<maliceUrl> listPut = queryMaliceUrl();
           int i = insertGNMaliceUrl(listPut);
           //读取公安数据
           List<UrlIntercept> list = queryUrl2TGJ();
           for(UrlIntercept url:list ){
               String[] Contacts = url.getContacts().split("_");
               try{
                   url.setContacts(Contacts[0]);
                   url.setContactsPhone(Contacts[1]);
                   url.setType(Contacts[2]);
               }catch (Exception e){
                   url.setContacts("");
                   url.setContactsPhone("");
                   url.setType("603");
               }
           }
           insertUrlIntercept(list);
       }catch (Exception e){
           logger.error("推送公安恶意url"+e.getMessage());
       }
        //猫池数据推送公安
        logger.info("猫池开始");
        List<McDetail>  mcList =   quertMcDetail();
        logger.info("猫池数量："+mcList.get(0).toString());
        try {
            int j = insertMcDetail(mcList);
            logger.info("猫池结束："+j);
        }catch (Exception e){
            logger.error("猫池结束："+e.getMessage());
        }


    }



    public static void main(String[] args){
        //猫池数据推送公安
        logger.info("猫池开始");
        List<McDetail>  mcList =   quertMcDetail();
        logger.info("猫池数量："+mcList.size());
        int i = insertMcDetail(mcList);
        logger.info("猫池结束："+i);
    }


    public static  Boolean insertUrlResult(List<maliceUrlResult> listResult) {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        int temp=0;
        try {
            ts = BeanUtil.newTransaction(DBConstant.tj_db);
            if (listResult.size() != 0) {
                temp = dao.insertUrlResult(listResult);
            }
            ts.commit();
            if (temp==0) {
                flag = false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
    public static  Boolean deleteMalice(String id) {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        int temp=0;
        try {
            ts = BeanUtil.newTransaction(DBConstant.tj_db);
            if (id != "") {
                temp = dao.deleteMaliceUrl(id);
             }
            ts.commit();
            if (temp==0) {
                flag = false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
    public static  int isertMalice(List<maliceUrlInterface> list) {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        int temp=0;
        try {

            ts = BeanUtil.newTransaction(DBConstant.tj_db);
            if (list.size()!=0) {
                temp = dao.insertMaliceUrl(list);
            }
            ts.commit();
            if (temp==0) {
                flag = false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            flag = false;
            try {
                ts.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return temp ;
    }
    public   int isertOracleUrl(List<maliceUrlInterface> list) {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        int temp=0;
        try {

            ts = BeanUtil.newTransaction(DBConstant.oracle);
            if (list.size()!=0) {
                temp = dao.insertOracleUrl(list);
            }
            ts.commit();
            if (temp==0) {
                flag = false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            flag = false;
            try {
                ts.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return temp ;
    }

    public   int insertGNMaliceUrl(List<maliceUrl> list) {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        int temp=0;
        try {

            ts = BeanUtil.newTransaction(DBConstant.oracle);
            if (list.size()!=0) {
                temp = dao.insertGNMaliceUrl(list);
            }
            ts.commit();
            if (temp==0) {
                flag = false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            flag = false;
            try {
                ts.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return temp ;
    }
    public  static List<maliceUrl>  queryMaliceUrl() {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        int temp=0;
        List<maliceUrl> list = null;
        try {

            ts = BeanUtil.newTransaction(DBConstant.dispose_web);
            list = dao.queryyPLMaliceUrl();
            temp = dao.updateMaliceUrl();
            ts.commit();
            if (temp==0) {
                flag = false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            flag = false;
            try {
                ts.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return list ;
    }
    //公安下发的恶意url入库
    public  static List<UrlIntercept>  queryUrl2TGJ() {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        int temp=0;
        List<UrlIntercept> list = null;
        try {
            ts = BeanUtil.newTransaction(DBConstant.oracle);
            list = dao.queryUrl2TGJ();
            ts = BeanUtil.newTransaction(DBConstant.oracle);
            temp = dao.updateUrl2TGJ();
            ts.commit();
            if (temp==0) {
                flag = false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            flag = false;
            try {
                ts.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return list ;
    }

    public  static int  insertUrlIntercept(List<UrlIntercept> list) {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        int temp=0;
        try {

            ts = BeanUtil.newTransaction(DBConstant.dispose_web);
            temp = dao.insertUrlInterface(list);
            ts.commit();
            if (temp==0) {
                flag = false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            flag = false;
            try {
                ts.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return temp ;
    }

    //读取猫池数据
    public  static List<McDetail>  quertMcDetail() {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        List<McDetail> list = null;
        int temp = 0;
        try {
            ts = BeanUtil.newTransaction(DBConstant.dispose_web);
            list = dao.queryMcDetail();
            ts.commit();
            temp = dao.updateMcDetail();
            ts.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                ts.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return list ;
    }
    //猫池数据推送公安
    public  static int  insertMcDetail(List<McDetail> list) {
        Transaction ts = null;// 事物提交
        int temp=0;
        try {

            ts = BeanUtil.newTransaction(DBConstant.oracle);
            temp = dao.insertMcDetail(list);
            ts.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                ts.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return temp ;
    }
}
