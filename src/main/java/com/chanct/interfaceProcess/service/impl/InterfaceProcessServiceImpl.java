package com.chanct.interfaceProcess.service.impl;

import com.chanct.core.util.BeanUtil;
import com.chanct.interfaceProcess.dao.InterfaceProcessDao;
import com.chanct.interfaceProcess.dao.impl.InterfaceProcessDaoImpl;
import com.chanct.interfaceProcess.entity.InterfaceProcess;
import com.chanct.interfaceProcess.service.InterfaceProcessService;
import com.chanct.netsecur.constants.DBConstant;
import org.apache.log4j.Logger;
import org.apache.ibatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InterfaceProcessServiceImpl implements InterfaceProcessService {
    private static Logger logger = Logger.getLogger(InterfaceProcessServiceImpl.class);
    private static InterfaceProcessDao dao =new InterfaceProcessDaoImpl();

    @Override
    public List<InterfaceProcess> query() {
        logger.info("进入service");
        List<InterfaceProcess> list = new ArrayList<InterfaceProcess>();
        try {
            list = dao.query();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("数量"+list.size());
        boolean b = insertInterfaceProcess(list);
        if(b){
            updateProcess();      //修改掉原来的数据的状态
        }
        logger.info(b);
        return list;
    }

    public Boolean insertInterfaceProcess(List<InterfaceProcess> list) {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        try {
            int temp=0;
            ts = BeanUtil.newTransaction(DBConstant.tj_db);
            if (list.size() > 0) {
                temp = dao.insertProcess(list);
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

    public Boolean updateProcess() {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        try {
            int temp=0;
            ts = BeanUtil.newTransaction(DBConstant.oracle);
                temp = dao.updateProcess();
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
    public static void main(String[] args){
        InterfaceProcessServiceImpl i = new InterfaceProcessServiceImpl();
        List<InterfaceProcess> list = i.query();
        System.out.println(":"+list);
    }

}
