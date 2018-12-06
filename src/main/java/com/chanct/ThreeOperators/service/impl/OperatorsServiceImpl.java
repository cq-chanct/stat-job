package com.chanct.ThreeOperators.service.impl;

import com.chanct.ThreeOperators.dao.OperatorsDao;
import com.chanct.ThreeOperators.dao.impl.OperatorsDaoImpl;
import com.chanct.ThreeOperators.entity.*;
import com.chanct.ThreeOperators.service.OperatorsService;
import com.chanct.ThreeOperators.util.ExcelUtil;
import com.chanct.core.util.BeanUtil;
import com.chanct.core.util.PropertyUtil;
import com.chanct.netsecur.constants.DBConstant;
import com.chanct.xdInsertOracle.utils.FilePathName;
import com.chanct.xdInsertOracle.utils.FileTools;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */
public class OperatorsServiceImpl implements OperatorsService{

    private static Logger logger = Logger.getLogger(OperatorsServiceImpl.class);
    //移动目录
    private String ydPath = PropertyUtil.getConfig("yd.file.up.path");
    private String ydPathBlackCard = PropertyUtil.getConfig("yd.file.up.path.blackCard");
    private String ydPathBlackPhone = PropertyUtil.getConfig("yd.file.up.path.blackPhone");
    //联通目录lt
    private String ltPath = PropertyUtil.getConfig("lt.file.up.path");
    private String ltPathBlackCard = PropertyUtil.getConfig("lt.file.up.path.blackCard");
    private String ltPathBlackPhone = PropertyUtil.getConfig("lt.file.up.path.blackPhone");
    //电信目录
    private String dxPath = PropertyUtil.getConfig("dx.file.up.path");
    private String dxPathBlackCard = PropertyUtil.getConfig("dx.file.up.path.blackCard");
    private String dxPathBlackPhone = PropertyUtil.getConfig("dx.file.up.path.blackPhone");

    private static OperatorsDao dao = new OperatorsDaoImpl();
    //14个sheet页
    public String[]  fileNames = {dxPath,ydPath,ltPath};
    //黑卡名单
    public String[]  blackCard = {dxPathBlackCard,ydPathBlackCard,ltPathBlackCard};
    //黑名单反馈
    public String[]  blackPhone = {ydPathBlackPhone,ltPathBlackPhone,dxPathBlackPhone};
    @Override
    public void readOperatorsExcel(String fileName,int type) {

        ExcelUtil eu = new ExcelUtil();
        File files = new File(fileName);
        Calendar c1 = new GregorianCalendar();
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        System.out.println();
        Calendar c2 = new GregorianCalendar();
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
        System.out.println();
        File [] dir = files.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("xlsx") || name.endsWith("xls")) {
                    return true;
                }
                return false;
            }
        });
        for(File file:dir){
            logger.info("文件名:"+file.getName());
            System.out.println("文件名:"+file.getName());
            try {
                InputStream is = new FileInputStream(file);
                List<Sheet> sheets = eu.readFile(is);
                FraudClass fraudClass = new FraudClass();
                logger.info("文件sheet数:"+sheets.size());
                for(int i = 0;i< sheets.size();i++){
                    List<Row>  rows = null;
                    if(sheets.size() < 14){
                        if(i<12){
                            System.out.println("123i:"+i);

                            rows =  eu.readSheet(sheets.get(i+1));
                        }else{
                            break;
                        }

                    }else{
                        rows =  eu.readSheet(sheets.get(i));
                    }
                    switch(i){

                            //暂时只有一行，所以只读取一次就行
                        case 0:
                            IrregularPhone irr = new IrregularPhone();
                            if(rows.size()==1) continue;
                            for (int j = 1; j < rows.get(1).getLastCellNum(); j++) {
                                Cell cell = rows.get(1).getCell(j);
                                String cellStr = "";
                                cellStr = eu.ConvertCellStr(cell);
                                System.out.println(":"+cellStr);
                                if(cellStr !=null){
                                    irr = eu.addEntityInfo(j,irr,cellStr,type);
                                }

                            }
                            logger.info("irr"+irr.toString());
                            insertSjhmdResult(irr,"irr");
                            break;
                        case 1:
                            BadcallNumber bad = new BadcallNumber();
                            if( rows.size()==1) continue;
                            for (int j = 1; j < rows.get(1).getLastCellNum(); j++) {
                                Cell cell = rows.get(1).getCell(j);
                                String cellStr = "";
                                cellStr = eu.ConvertCellStr(cell);
                                if(cellStr !=null) {
                                    bad = eu.addEntityInfo(j, bad, cellStr,type);
                                }
                            }
                            logger.info("bad"+bad);
                            insertSjhmdResult(bad,"bad");
                            break;
                        case 2:
                            AbroadPhoneNumber  abr =  new AbroadPhoneNumber();
                            if(rows.size()==1 ) continue;
                            for (int j = 1; j < rows.get(1).getLastCellNum(); j++) {
                                Cell cell = rows.get(1).getCell(j);
                                String cellStr = "";
                                int leix = cell.getCellType();
                                cellStr = eu.ConvertCellStr(cell);
                                if(cellStr !=null) {
                                    abr = eu.addEntityInfo(j, abr, cellStr,type);
                                }
                            }
                            logger.info("abr:"+abr);
                            insertSjhmdResult(abr,"abr");
                            break;
                        case 3:
                            if( rows.size()==1) continue;
                            for (int j = 1; j < rows.get(1).getPhysicalNumberOfCells(); j++) {
                                Cell cell = rows.get(1).getCell(j);
                                String cellStr = "";
                                cellStr = eu.ConvertCellStr(cell);
                                if(cellStr !=null) {
                                    fraudClass = eu.addEntityInfo(j, fraudClass, cellStr,type);
                                }
                            }
                            break;
                        case 4:
                            if(rows.size()==1) continue;
                            Cell cell5 = rows.get(1).getCell(3);
                            String startTime =  eu.ConvertCellStr(rows.get(1).getCell(1))=="0"?c1.getTime().toLocaleString():eu.change(eu.ConvertCellStr(rows.get(1).getCell(1))) ;
                            logger.info("开始时间为："+startTime);
                            String endTime = eu.ConvertCellStr(rows.get(1).getCell(2))=="0"?c2.getTime().toLocaleString():eu.change(eu.ConvertCellStr( rows.get(1).getCell(2))) ;
                            String cellStr5 = "";
                            cellStr5 = eu.ConvertCellStr(cell5);
                            fraudClass.setStartTime(startTime);
                            fraudClass.setEndTime(endTime);
                            fraudClass.setGarbageMessage(Integer.parseInt(cellStr5));
                            break;
                        case 5:
                            if(rows.size()==1) continue;
                            Cell cell6 = rows.get(1).getCell(3);
                            String cellStr6 = "";
                            cellStr6 = eu.ConvertCellStr(cell6);
                            fraudClass.setFraudMessage(Integer.parseInt(cellStr6));
                            break;
                        case 6:
                            if(rows.size()==1) continue;
                            Cell cell7 = rows.get(1).getCell(3);
                            String cellStr7 = "";
                            cellStr7 = eu.ConvertCellStr(cell7);
                            fraudClass.setHighlySuspectedNumber(Integer.parseInt(cellStr7));
                            break;
                        case 7:
                            if(rows.size()==1) continue;
                            int success = Integer.parseInt(eu.ConvertCellStr(rows.get(1).getCell(3)));
                            int failure = Integer.parseInt(eu.ConvertCellStr(rows.get(1).getCell(4)));
                            fraudClass.setDissuadeSuccess(success);
                            fraudClass.setDissuadeFailure(failure);
                            fraudClass.setPublicFraudNumber(success+failure);
                            break;
                        case 8:
                            if(rows.size()==1) continue;
                            Cell cell9 = rows.get(1).getCell(3);
                            String cellStr9 = "";
                            cellStr9 = eu.ConvertCellStr(cell9);
                            fraudClass.setStopMoney(Double.parseDouble(cellStr9));
                            break;
                        case 9:
                            if(rows.size()==1) continue;
                            Cell cell10 = rows.get(1).getCell(3);
                            String cellStr10 = "";
                            cellStr10 = eu.ConvertCellStr(cell10);
                            fraudClass.setFraudUrlNumber(Integer.parseInt(cellStr10));
                            break;
                        case 10:
                            if(rows.size()==1) continue;
                            Cell cell11 = rows.get(1).getCell(3);
                            String cellStr11 = "";
                            cellStr11 = eu.ConvertCellStr(cell11);
                            fraudClass.setEnterpriseUrl(Integer.parseInt(cellStr11));
                            break;
                        case 11:
                            if(rows.size()==1) continue;
                            Cell cell12 = rows.get(1).getCell(3);
                            String cellStr12 = "";
                            cellStr12 = eu.ConvertCellStr(cell12);
                            fraudClass.setWjzNumber(Integer.parseInt(cellStr12));
                            break;
                        case 12:
                            EnterpriseManagement ent = new EnterpriseManagement();
                            if( rows.size()==1) continue;
                            for (int j = 1; j < rows.get(1).getLastCellNum(); j++) {
                                Cell cell = rows.get(1).getCell(j);
                                String cellStr = "";
                                cellStr = eu.ConvertCellStr(cell);
                                if(cellStr !=null) {
                                    ent = eu.addEnterpriseManagement(j, ent, cellStr,type);
                                }
                            }
                            logger.info("企业内部管理ent"+ent);
                            insertSjhmdResult(ent,"ent");
                            break;
                        case 13:
                            TelecomRectify tel = new TelecomRectify();
                            if( rows.size()==1) continue;
                            for (int j = 1; j < rows.get(1).getLastCellNum(); j++) {
                                Cell cell = rows.get(1).getCell(j);
                                String cellStr = "";
                                cellStr = eu.ConvertCellStr(cell);
                                if(cellStr !=null) {
                                    tel = eu.addTelecomRectify(j, tel, cellStr,type);
                                }
                            }
                            logger.info("14.重点电信业务市场经营秩序清理整顿tel"+tel);
                            insertSjhmdResult(tel,"tel");
                            break;
                    }
                }
                if(fraudClass!=null){
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    //fraudClass.setStartTime(sdf.format(d)+" 00:00:00");
                    //fraudClass.setEndTime(sdf.format(d)+" 23:59:59");
                }
                fraudClass.setType(type);
                insertSjhmdResult(fraudClass,"fra");
                if(fraudClass != null){
                    FileTools.MoveFile(file, fileName+"/bak/");
                }else{
                    FileTools.MoveFile(file, fileName+"/err/");
                }
            } catch (Exception e) {
                logger.error("错误信息："+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public Boolean insertSjhmdResult(Object object,String name) {
        Boolean flag = true;
        Transaction ts = null;// 事物提交
        try {
            int temp=0;
            ts = BeanUtil.newTransaction(DBConstant.tj_db);
            switch (name){
                case "phone": System.out.println("黑名单上报"); temp = dao.insertBlacPhone((BlackphoneResult)object);break;
                case "irr": System.out.println("irr"); temp = dao.insertIrr((IrregularPhone)object);break;
                case "bad": System.out.println("bad"); temp = dao.insertBadCall((BadcallNumber)object);break;
                case "fra": System.out.println("fra"); temp = dao.insertFraudClass((FraudClass)object);break;
                case "abr": System.out.println("abr"); temp = dao.insertAbroad((AbroadPhoneNumber)object);break;
                case "ent": System.out.println("ent"); temp = dao.insertEnterprise((EnterpriseManagement)object);break;
                case "tel": System.out.println("tel"); temp = dao.insertTel((TelecomRectify)object);break;
                case "black":ts = BeanUtil.newTransaction(DBConstant.dispose_web); System.out.println("black"); temp = dao.insertBlackCard((BlackCardVo) object);break;
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
        return flag ;
    }
    public void insertThree(){
        logger.info("service");
        //读取上报的14个sheet页
        for(int i=1;i<fileNames.length+1;i++){
            readOperatorsExcel(fileNames[i-1],i);
        }
        //读取黑卡
        for(int i=1;i<blackPhone.length+1;i++){
            readOperatorsBlackPhone(blackPhone[i-1],i);
        }
        //读取黑名单反馈信息
        for(int i=1;i<blackCard.length+1;i++){
            readOperatorsExcelBlackCard(blackCard[i-1],i);
        }
    }
    public static void main(String[] args){
       /* OperatorsServiceImpl service = new OperatorsServiceImpl();
        for(int i=1;i<service.fileNames.length+1;i++){
            service.readOperatorsExcel(service.fileNames[i-1],i);
        }*/

        OperatorsServiceImpl service = new OperatorsServiceImpl();
        for(int i=1;i<service.blackPhone.length+1;i++){
            service.readOperatorsBlackPhone(service.blackPhone[i-1],i);
        }
    }
    public void readOperatorsBlackPhone(String fileName,int type) {
        ExcelUtil eu = new ExcelUtil();
        File files = new File(fileName);
        File [] dir = files.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("xlsx") || name.endsWith("xls")) {
                    return true;
                }
                return false;
            }
        });
        if(dir == null)  return;
        for(File file:dir){
            try{
                InputStream is = new FileInputStream(file);
                List<Sheet> sheets = eu.readFile(is);
                List<Row>  rows = eu.readSheet(sheets.get(0));
                BlackphoneResult phoneResult = new BlackphoneResult();
                if(rows.size()==1) continue;
                for (int j = 1; j < rows.get(1).getLastCellNum(); j++) {
                    Cell cell = rows.get(1).getCell(j);
                    String cellStr = "";
                    cellStr = eu.ConvertCellStr(cell);
                    if(cellStr !=null){
                        phoneResult = eu.addEntityInfo(j,phoneResult,cellStr,type);
                    }
                }
                is.close();
                boolean result = insertSjhmdResult(phoneResult,"phone");
                if(result){
                    FileTools.MoveFile(file, fileName+"/bak/");
                }else{
                    FileTools.MoveFile(file, fileName+"/err/");
                }
            }catch (Exception e){
                logger.error("读取文件出错"+e.getMessage());
            }

        }

    }
    public void readOperatorsExcelBlackCard(String fileName,int type) {
        ExcelUtil eu = new ExcelUtil();
        File files = new File(fileName);
        File [] dir = files.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith("xlsx") || name.endsWith("xls")) {
                    return true;
                }
                return false;
            }
        });
        for(File file:dir){
            try{
                InputStream is = new FileInputStream(file);
                List<Sheet> sheets = eu.readFile(is);
                List<Row>  rows = eu.readSheet(sheets.get(0));
                BlackCardVo black = new BlackCardVo();
                if(rows.size()==1) continue;
                for (int j = 1; j < rows.get(1).getLastCellNum(); j++) {
                    Cell cell = rows.get(1).getCell(j);
                    String cellStr = "";
                    cellStr = eu.ConvertCellStr(cell);
                    if(cellStr !=null){
                        black = eu.addEntityInfo(j,black,cellStr,type);
                    }
                }
                is.close();
                boolean result = insertSjhmdResult(black,"black");
                if(result){
                    FileTools.MoveFile(file, fileName+"/bak/");
                }else{
                    FileTools.MoveFile(file, fileName+"/err/");
                }
            }catch (Exception e){
                logger.error("读取文件出错"+e.getMessage());
            }

        }

    }

}
