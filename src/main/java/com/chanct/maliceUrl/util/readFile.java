package com.chanct.maliceUrl.util;

import com.chanct.maliceUrl.entity.UrlInterface;
import com.chanct.maliceUrl.entity.maliceUrlInterface;
import com.chanct.xdInsertOracle.utils.FilePathName;
import com.chanct.xdInsertOracle.utils.FileTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class readFile {

    public static List<maliceUrlInterface> readFileByLinesOnMalice(File file) {


        BufferedReader reader = null;
        List<maliceUrlInterface> list = new ArrayList<>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            String[] stringArr = new String[18];
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                stringArr = tempString.split(",");
                maliceUrlInterface objs = new maliceUrlInterface();

                objs = putMalice(stringArr);

                list.add(objs);
                line++;
            }
            reader.close();
            if(list.size() > 0){
                FileTools.MoveFile(file, "/home/filetransfer/bak/"+new FilePathName().getLIST_FILEPATH());
            }else{
                FileTools.MoveFile(file, "/home/filetransfer/err/"+new FilePathName().getLIST_FILEPATH());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return list;
    }
    public static List<UrlInterface> readFileByLinesOnUrl(File file) {


        BufferedReader reader = null;
        List<UrlInterface> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            String[] stringArr = new String[18];
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                stringArr = tempString.split(",");
                UrlInterface objs = new UrlInterface();

                objs = putUlr(stringArr);

                list.add(objs);
                line++;
            }
            reader.close();
            if(list.size() > 0){
                FileTools.MoveFile(file, "/home/filetransfer/bak/"+new FilePathName().getLIST_FILEPATH());
            }else{
                FileTools.MoveFile(file, "/home/filetransfer/err/"+new FilePathName().getLIST_FILEPATH());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return list;
    }
   public static void main(String[] args){
        String fileName = "C:\\Users\\Administrator\\Desktop\\vv\\gjf_201805071435.txt";
        File file = new File(fileName);
        List<UrlInterface> list = readFileByLinesOnUrl(file);
        System.out.println(list.get(0));
    }

    public static maliceUrlInterface  putMalice(String[] arr){
        maliceUrlInterface malice = new maliceUrlInterface();
        malice.setTicketId(arr[0]);
        malice.setName(arr[1]);
        malice.setPhone(arr[2]);
        malice.setBelongPlaceCode(Integer.parseInt(arr[3]));
        malice.setBelongPlace(arr[4]);
        malice.setUrl(arr[5]);
        malice.setIp(arr[6]);
        malice.setImage(arr[7]);
        malice.setDepth(Integer.parseInt(arr[8]));
        malice.setOperator(Integer.parseInt(arr[9]));
        malice.setVisisPV(Integer.parseInt(arr[10]));
        malice.setFirstTime(arr[11]);
        malice.setLastTime(arr[12]);
        malice.setIsStop(Integer.parseInt(arr[13]));
        malice.setStopTime(arr[14]);
        malice.setTypeThree(Integer.parseInt(arr[15]));
        malice.setIsPolice(Integer.parseInt(arr[16]));
        malice.setMd5(arr[17]);
        return malice;
    }

    public static UrlInterface  putUlr(String[] arr){
        UrlInterface malice = new UrlInterface();
        malice.setTicketId(arr[0]);
        malice.setUrl("".equals(arr[1])?"NULL":arr[1]);
        malice.setIp("".equals(arr[2])?"NULL":arr[2]);
        malice.setTp(arr[3]);
        malice.setDepth(Integer.parseInt(arr[4]));
        malice.setOperator(Integer.parseInt(arr[5]));
        malice.setRdTime(arr[6].substring(0,19));
        malice.setTypeThree(Integer.parseInt(arr[7]));
        return malice;
    }

}
