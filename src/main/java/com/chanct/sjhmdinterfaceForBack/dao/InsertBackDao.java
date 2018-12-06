package com.chanct.sjhmdinterfaceForBack.dao;

import com.chanct.sjhmdinterfaceForBack.entity.BlackNum;
import com.chanct.sjhmdinterfaceForBack.entity.FeedBack;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/16.
 */
public interface InsertBackDao {


    //查询oracle中的黑名单，
    public List<FeedBack>  queryInterFace();

    //修改oracle中的状态
    public int updateInterFace(Map<String, Object> map);

    //将oracle中sjhmd中的数据取出，放入sjhmdresult
    public int insertSjhmdResult(List<FeedBack> list);

  //查询黑名单中是否存在这条数据
	public BlackNum queryblackone(Map<String, Object> map);

	public int updateOldBlackNum(BlackNum black);

	public int insertBlackNum(Map<String, Object> map);


    
}
