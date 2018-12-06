package com.chanct.maliceUrl.dao.dao;

import com.chanct.core.dao.BaseDAO;
import com.chanct.maliceUrl.dao.insertMaliceUrlDao;
import com.chanct.maliceUrl.entity.*;
import com.chanct.netsecur.constants.DBConstant;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class insertMaliceUrlDaoImpl extends BaseDAO implements insertMaliceUrlDao{
    private static Logger logger = Logger.getLogger(insertMaliceUrlDaoImpl.class);
    @Override
    public int insertMaliceUrl(List<maliceUrlInterface> list) {
        return this.insertObject("maliceUrl.maliceUrlInterface.insertMaliceInterface",list, DBConstant.tj_db);
    }

    @Override
    public int deleteMaliceUrl(String listId) {
        return this.deleteObject("maliceUrl.maliceUrlInterface.deleteMaliceUrl",listId, DBConstant.tj_db);
    }

    @Override
    public String  queryMaliceUrl(String listId) {
        System.out.println(listId);
        List<String> list = this.selectList("maliceUrl.maliceUrlInterface.queryMaliceUrlInterface",listId, DBConstant.tj_db);
        if(list.size()!=0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int insertOracleUrl(List<maliceUrlInterface> list) {

        int i = 0;
        try{
            i = this.insertObject("maliceUrl.maliceUrlInterface.insertObjectUrl",list, DBConstant.oracle);
        }catch (Exception e){
            logger.error("~"+e.getMessage());
        }
        return i;

    }

    @Override
    public List<maliceUrlResult> queryUrlResult() {
        List<maliceUrlResult> list = new ArrayList<>();
       try {
           logger.info("1");
           list =  this.selectList("maliceUrl.maliceUrlInterface.queryUrlResult","",DBConstant.oracle);
           logger.info("2");
       }catch (Exception e){
           logger.error("!!:!"+e.getMessage());
       }
       logger.info("3");
       return list;
    }

    @Override
    public int insertUrlResult(List<maliceUrlResult> list) {
        int i = 0;
        try {
            i = this.insertObject("maliceUrl.maliceUrlInterface.insertUrlMysqlReslut",list,DBConstant.tj_db);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return i;
    }

    @Override
    public int insertObjectAdress(List<UrlInterface> list) {
        int i =0;
        try {
            i = this.insertObject("maliceUrl.maliceUrlInterface.insertObjectAdress",list,DBConstant.oracle);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return i;
    }

    @Override
    public int insertUrlMysqlUrl(List<UrlInterface> list) {
        int i =0;
        try {
            i = this.insertObject("maliceUrl.maliceUrlInterface.insertUrlMysqlUrl",list,DBConstant.tj_db);
        }catch (Exception e){
            logger.error(list.get(0));
            logger.error(e.getMessage());
        }
        return i;
    }

    @Override
    public List<maliceUrl> queryyPLMaliceUrl() {
        List<maliceUrl> list = new ArrayList<>();
        try {
            list =  this.selectList("maliceUrl.maliceUrlInterface.queryMaliceUrl","",DBConstant.dispose_web);
        }catch (Exception e){
            logger.error("推送公安的恶意url"+e.getMessage());
        }
        logger.info("3");
        return list;
    }

    @Override
    public int updateMaliceUrl() {
        return this.updateObject("maliceUrl.maliceUrlInterface.updateMailceUrl","",DBConstant.dispose_web);
    }

    @Override
    public int insertGNMaliceUrl(List<maliceUrl> list) {
        return this.insertObject("maliceUrl.maliceUrlInterface.insertObjectAdress",list,DBConstant.oracle);
    }

    @Override
    public List<UrlIntercept> queryUrl2TGJ() {
        List<UrlIntercept> list = new ArrayList<>();
        try {
            logger.info("查询开始");
            list =  this.selectList("maliceUrl.maliceUrlInterface.queryUrl2TGJ","",DBConstant.oracle);
            logger.info("查询完成");
        }catch (Exception e){
            logger.error("查询出错"+e.getMessage());
        }
        return list;
    }

    @Override
    public int updateUrl2TGJ() {
        return this.updateObject("maliceUrl.maliceUrlInterface.updateUrl2TGJ","",DBConstant.oracle);
    }

    @Override
    public int insertUrlInterface(List<UrlIntercept> list) {
        return this.insertObject("maliceUrl.maliceUrlInterface.insertUrlInterface",list,DBConstant.dispose_web);
    }

    @Override
    public List<McDetail> queryMcDetail() {
        List<McDetail> list = new ArrayList<>();
        try {
            logger.info("猫池查询开始");
            list =  this.selectList("maliceUrl.maliceUrlInterface.queryMcDetial","",DBConstant.dispose_web);
            logger.info("猫池查询完成");
        }catch (Exception e){
            logger.error("猫池查询出错"+e.getMessage());
        }
        return list;
    }

    @Override
    public int updateMcDetail() {
        return this.updateObject("maliceUrl.maliceUrlInterface.updateMcDetail","",DBConstant.dispose_web);

    }

    @Override
    public int insertMcDetail(List<McDetail> list) {
        return this.insertObject("maliceUrl.maliceUrlInterface.insertMcDetail",list,DBConstant.oracle);
    }


}
