package com.chanct.maliceUrl.dao;

import com.chanct.maliceUrl.entity.*;

import java.util.List;

public interface insertMaliceUrlDao {

    public int insertMaliceUrl(List<maliceUrlInterface> list);

    public int deleteMaliceUrl(String listId);

    public String queryMaliceUrl(String listId);

    int  insertOracleUrl(List<maliceUrlInterface> list);

    public List<maliceUrlResult>  queryUrlResult();

    int insertUrlResult(List<maliceUrlResult> list);

    int insertObjectAdress(List<UrlInterface> list);

    int insertUrlMysqlUrl(List<UrlInterface> list);


    List<maliceUrl> queryyPLMaliceUrl();

    int updateMaliceUrl();

    int insertGNMaliceUrl(List<maliceUrl> list);

    //公安下发的恶意url入库
    List<UrlIntercept>  queryUrl2TGJ();   //查询前置机的数据

    int updateUrl2TGJ();    //修改前置机状态

    int  insertUrlInterface(List<UrlIntercept> list);     //前置机数据入mysql

    //猫池数据推送公安
    List<McDetail>  queryMcDetail();   //查询mysql猫池数据

    int updateMcDetail();    //修改猫池状态

    int  insertMcDetail(List<McDetail> list);     //猫池数据推送mysql

}
