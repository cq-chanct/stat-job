package com.chanct.xdInsertOracle.utils;

import com.chanct.insertVoipOracle.entity.VoipUcmdTgj;
import com.chanct.xdInsertOracle.job.XdInsertOracleJob;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelUtil {
    private static Logger logger = Logger.getLogger(ExcelUtil.class);
    public List<VoipUcmdTgj> readFile(InputStream is) throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> uuidList = new ArrayList<String>();
        List<VoipUcmdTgj> hostList = new ArrayList<VoipUcmdTgj>();
        Workbook wb = null;
        try {
            //InputStream is = new FileInputStream(file.getAbsolutePath());
            wb = WorkbookFactory.create(is);
            Sheet sheet1 = wb.getSheetAt(0);// 取得第一个sheet
            String[] eventTitles = new String[]{"主叫号码","被叫号码","通话开始时间","通话结束时间","通话时长"};
            for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
                VoipUcmdTgj VoipUcmdTgj = new VoipUcmdTgj();
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");//用来生成数据库的主键id
                uuidList.add(uuid);

                Row row = sheet1.getRow(i); // 获取行(row)对象

                if (row == null) {
                    sb.append(sheet1.getSheetName() + "Sheet页的第" + (i + 1) + "行为空！\n");
                    continue;
                }
                for (int j = 0; j < eventTitles.length; j++) {

                    Cell cell = row.getCell(j); // 获得单元格(cell)对象
                    String cellStr = "";
                    // 转换接收的单元格
                    cellStr = ConvertCellStr(cell);
                    if ("".equals(cellStr)) {
                        sb.append(sheet1.getSheetName() + "Sheet页的第" + (i + 1) + "行" + eventTitles[j] + "列的值为空！\n");
                    }
                    VoipUcmdTgj = addEventInfo(j, VoipUcmdTgj, cellStr);
                }
                hostList.add(VoipUcmdTgj);
            }
        } catch (Exception e) {
           logger.error(e.getMessage());
        }
        return hostList;
    }

    private String ConvertCellStr(Cell cell) {
        String result = "";
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
                        result = String.valueOf(cell.getNumericCellValue());
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
        return result;
    }

    private VoipUcmdTgj addEventInfo(int j, VoipUcmdTgj vut, String cellStr) {
        switch (j) {
            case 0:
                System.out.println(":"+cellStr);
                vut.setCallingnumber(cellStr.toString().replace("\"",""));
                break;
            case 1:
                vut.setCallednumber(cellStr.toString().replace("\"",""));
                break;
            case 2:
                System.out.println("celll:"+cellStr);
                vut.setStarttalktime(cellStr);
                break;
            case 3:
                vut.setStoptalktime(cellStr);
            case 4:
                vut.setTalktime(cellStr.toString().replace(".0",""));
                break;
        }
        vut.setCityname("杭州");
        return vut;
    }
}
