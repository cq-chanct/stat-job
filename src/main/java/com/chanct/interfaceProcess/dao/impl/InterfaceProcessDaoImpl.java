package com.chanct.interfaceProcess.dao.impl;

import com.chanct.core.dao.BaseDAO;
import com.chanct.interfaceProcess.dao.InterfaceProcessDao;
import com.chanct.interfaceProcess.entity.InterfaceProcess;
import com.chanct.interfaceProcess.service.impl.InterfaceProcessServiceImpl;
import com.chanct.netsecur.constants.DBConstant;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class InterfaceProcessDaoImpl extends BaseDAO implements InterfaceProcessDao {
    private static Logger logger = Logger.getLogger(InterfaceProcessDaoImpl.class);

    @Override
    public List<Map<String, String>> queryLastTime() {
        return this.selectList("interface.interfaceProcess.queryBigTime", null);
    }

    @Override
    public List<InterfaceProcess> query() {
        List<InterfaceProcess>  list = null;
        list = this.selectList("interface.interfaceProcess.queryInterFaceProcess","", DBConstant.oracle);
        return list;
    }

    @Override
    public int insertProcess(List<InterfaceProcess> list) {
        logger.info("dao"+list.size());
        int i = 0;
        try {
            i = this.insertObject("interface.interfaceProcess.inserInterfaceProcess",list, DBConstant.tj_db);
        } catch (Exception e) {
            logger.info("数据插入错误："+i);
            logger.error(e);
        }
        logger.info("插入的数据为："+i);
        return i;
    }

    @Override
    public int updateProcess() {
        return this.updateObject("interface.interfaceProcess.updateProcess","",DBConstant.oracle);
    }


    @Override
    public int deleteProcess() {
        int i = 0;
        i = this.deleteObject("interface.interfaceProcess.deleteInterFace","", DBConstant.tj_db);
        return i;

    }
}
