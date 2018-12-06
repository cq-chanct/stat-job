package com.chanct.ThreeOperators.util;

import com.chanct.ThreeOperators.entity.*;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExcelUtil {
    private static Logger logger = Logger.getLogger(ExcelUtil.class);

    //获取excel的sheet集合
    public List<Sheet> readFile(InputStream is) throws Exception {
        List<Sheet> sheets = new ArrayList<Sheet>();
        Sheet sheet = null;
        StringBuffer sb = new StringBuffer();
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(is);
            for(int k =0;k<wb.getNumberOfSheets();k++){
                sheet = wb.getSheetAt(k);// 取得第一个sheet
                sheets.add(sheet);
            }
        } catch (Exception e) {
           logger.error(e.getMessage());
        }
        return sheets;
    }
    //读取sheet的row
    public List<Row>   readSheet(Sheet  sheet1){
        Row row = null;
        List<Row> rows =  new ArrayList<>();
        for (int i = 0; i < sheet1.getLastRowNum()+1; i++) {
             row = sheet1.getRow(i); // 获取行(row)对象
            rows.add(row);
        }
        return rows;
    }
    public String ConvertCellStr(Cell cell) {
        String result = "0";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    // 读取String
                    result = cell.getStringCellValue().toString();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    // 得到Boolean对象的方法
                    result = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    // 先看是否是日期格式(POI的日期格式化工具)
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date d = cell.getDateCellValue();
                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        result = formater.format(d);
                    } else {
                        // 读取数字
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        result = cell.getStringCellValue().toString();
                        //result = String.valueOf(cell.getNumericCellValue()).replace(".0","");
                    }
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    // 读取公式
                    result = cell.getCellFormula().toString();
                    break;
                default:
                    // 读取String
                    result = cell.toString();
                    break;
            }
        }
        if("".equals(result))result ="0";
        return result;
    }

    public String change(String cellStr) {
        if("".equals(cellStr) || cellStr == null){  //为空的话变为0
            cellStr = "0";
        }
        cellStr = cellStr.replaceAll("24:00:00","23:59:59");
        return cellStr;
    }

    //不规则号码统计量
    public IrregularPhone addEntityInfo(int j, IrregularPhone irr, String cellStr,int type) {
        cellStr = change(cellStr);
        switch (j) {
            case 1:
               irr.setStartTime(cellStr);
                break;
            case 2:
                irr.setEndTime(cellStr);
                break;
            case 3:
                irr.setTerritory(Integer.parseInt(cellStr));
                break;
            case 4:
                irr.setTerritoryTop1(cellStr);
                break;
            case 5:
                irr.setTerritoryTop2(cellStr);
                break;
            case 6:
                irr.setTerritoryTop3(cellStr);
                break;
            case 7:
                irr.setAbroad(Integer.parseInt(cellStr));
                break;
            case 8:
                irr.setNumber(Integer.parseInt(cellStr));
                break;
        }
        irr.setType(type);
        return irr;
    }
    //有害呼叫拦截统计量
    public BadcallNumber addEntityInfo(int j, BadcallNumber bad, String cellStr,int type) {
        cellStr = change(cellStr);
        switch (j) {
            case 1:
                bad.setStartTime(cellStr);
                break;
            case 2:
                bad.setEndTime(cellStr);
                break;
            case 3:
                bad.setTerritory(Integer.parseInt(cellStr));
                break;
            case 4:
                bad.setTerritoryTop1(cellStr);
                break;
            case 5:
                bad.setTerritoryTop2(cellStr);
                break;
            case 6:
                bad.setTerritoryTop3(cellStr);
                break;
            case 7:
                bad.setAbroad(Integer.parseInt(cellStr));
                break;
            case 8:
                bad.setNumber1(Integer.parseInt(cellStr));
                break;
            case 9:
                bad.setAnomaly(Integer.parseInt(cellStr));
                break;
            case 10:
                bad.setFraud(Integer.parseInt(cellStr));
                break;
            case 11:
                bad.setAdvertisement(Integer.parseInt(cellStr));
                break;
            case 12:
                bad.setOther(Integer.parseInt(cellStr));
                break;
            case 13:
                bad.setNumber2(Integer.parseInt(cellStr));
                break;
        }
        bad.setType(type);
        return bad;
    }
    public AbroadPhoneNumber addEntityInfo(int j, AbroadPhoneNumber abr, String cellStr,int type) {
        cellStr = change(cellStr);
        switch (j) {
            case 1:
                abr.setStartTime(cellStr);
                break;
            case 2:
                abr.setEndTime(cellStr);
                break;
            case 3:
                abr.setFlashSignal(Integer.parseInt(cellStr));
                break;
            case 4:
                abr.setMessage(Integer.parseInt(cellStr));
                break;
            case 5:
                abr.setNumber(abr.getFlashSignal()+abr.getMessage());
                break;
        }
        abr.setType(type);
        return abr;
    }
    //企业内部管理
    public EnterpriseManagement addEnterpriseManagement(int j, EnterpriseManagement ent, String cellStr, int type) {
        cellStr = change(cellStr);
        switch (j) {
            case 1:
                ent.setStartTime(cellStr);
                break;
            case 2:
                ent.setEndTime(cellStr);
                break;
            case 3:
                ent.setTraining(Integer.parseInt(cellStr));
                break;
            case 4:
                ent.setExamination(Integer.parseInt(cellStr));
                break;
            case 5:
                ent.setViolation(Integer.parseInt(cellStr));
                break;
        }
        ent.setType(type);
        return ent;
    }

    //14.重点电信业务市场经营秩序清理整顿
    public TelecomRectify addTelecomRectify(int j, TelecomRectify tel, String cellStr, int type) {
        cellStr = change(cellStr);
        switch (j) {
            case 1:
                tel.setStartTime(cellStr);
                break;
            case 2:
                tel.setEndTime(cellStr);
                break;
            case 3:
                tel.setVoice(Integer.parseInt(cellStr));
                tel.setRectifyVoice(Integer.parseInt(cellStr));
                break;
            case 4:
                tel.setRectifyVoice(Integer.parseInt(cellStr));
                break;
            case 5:
                tel.setShutDownVoice(Integer.parseInt(cellStr));
                break;
            case 6:
                tel.setRectify_business(Integer.parseInt(cellStr));
                break;
            case 7:
                tel.setShutDown_business(Integer.parseInt(cellStr));
                break;
            case 8:
                tel.setOne_connect(Integer.parseInt(cellStr));
                break;
            case 9:
                tel.setBusiness(Integer.parseInt(cellStr));
                break;
        }
        tel.setType(type);
        return tel;
    }

    //黑卡号码反馈
    public BlackCardVo addEntityInfo(int j, BlackCardVo irr, String cellStr,int type) {
        cellStr = change(cellStr);
        switch (j) {
            case 1:
                irr.setFilename(cellStr);
                break;
            case 2:
                irr.setPhoneNum(cellStr);
                break;
            case 3:
                irr.setSource(cellStr);
                break;
            case 4:
                irr.setSourcePhone(cellStr);
                break;
            case 5:
                irr.setDisposeTime(cellStr);
                break;
            case 6:
                irr.setResult(cellStr);
                break;
            case 7:
                irr.setCloseTime(cellStr);
                break;

        }
        irr.setType(type);
        return irr;
    }
    String fileName;
    String blackPhone;
    String manageTime;
    String manageResult;
    String interceptTime;
    String timeLimit;
    int type;
    //黑名单反馈情况
    public BlackphoneResult addEntityInfo(int j, BlackphoneResult irr, String cellStr,int type) {
        cellStr = change(cellStr);
        switch (j) {
            case 1:
                irr.setFileName(cellStr);
                break;
            case 2:
                irr.setBlackPhone(cellStr);
                break;
            case 4:
                irr.setManageTime(cellStr);
                break;
            case 5:
                irr.setManageResult(cellStr);
                break;
            case 6:
                irr.setInterceptTime(cellStr);
                break;
            case 7:
                irr.setTimeLimit(cellStr);
                break;
        }
        irr.setType(type);
        return irr;
    }
    public FraudClass addEntityInfo(int j, FraudClass fraudClass, String cellStr,int type) {
        cellStr = change(cellStr);
        switch (j) {
            case 1:
                fraudClass.setStartTime(cellStr);
                break;
            case 2:
                fraudClass.setEndTime(cellStr);
                break;
            case 3:
                fraudClass.setGwelfareMessage(Integer.parseInt(cellStr));
                break;
        }
        fraudClass.setType(type);
        return fraudClass;
    }
    public static void main(String[] args){
        String j = "2018-7-31 24:00:00";
        j = j.replace("24:00:00","23:59:59");
        System.out.println(j);
    }
}
