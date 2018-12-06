package com.chanct.xdInsertOracle.utils;

import com.chanct.core.util.FtpUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: FileUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author tangkl
 * @date 2017年6月12日 下午5:32:59
 */
public class FileTools {
	
	private static Logger logger = Logger.getLogger(FileTools.class);
	/**
	 * 
	 * @Title: ListToFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param path
	 * @param filename
	 * @param list
	 * @param fields
	 * @param encoding
	 * @param split
	 * @param tab    
	 * @throws
	 */
	public static <T> void ListToFile(String path, String filename, List<T> list, List<String> fields, String encoding, String split, String tab){
		if(!list.isEmpty()){
			String pathName=path+filename;
			File pathDir=new File(path);
			if(!pathDir.isDirectory()){
				pathDir.mkdirs();
			}
			FileOutputStream fos=null;
			OutputStreamWriter osw=null;
			BufferedWriter bw=null;
			try {
				File file=new File(pathName);
				fos=new FileOutputStream(file);
				osw=new OutputStreamWriter(fos,encoding);
	            bw=new BufferedWriter(osw);
				for(int i=0;i<list.size();i++){
					Class cls=list.get(i).getClass();
					StringBuffer line=new StringBuffer();
					for(int j=0;j<fields.size();j++){
						Field field=cls.getDeclaredField(fields.get(j));
						field.setAccessible(true);
						if(j!=fields.size()-1){	
							line.append(field.get(list.get(i))).append(split);
							}else{
							line.append(field.get(list.get(i))).append(tab);	
							}
						}
					bw.write(line.toString());
					//fw.write(line.toString());
					}
				  bw.flush();
	              bw.close();
	              fos.close();
	              osw.close();
	              logger.info("生成文件成功："+pathName);
			} catch (Exception e) {
				logger.error("生成文件报错："+e.getMessage());
			}finally{
				try {
					if(fos!=null){
						fos.close();
					}
					if(bw!=null){
						bw.close();
					}
					if(osw!=null){
						osw.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

		}
		
		
	}
	/**
	 * 
	 * @Title: FileToList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param path
	 * @param filename
	 * @param obj
	 * @param fields
	 * @param encoding
	 * @param split
	 * @param tab
	 * @param index
	 * @return    
	 * @throws
	 */
	public static  <T> List<T> FileToList(String path, String filename, Class<T> obj, List<String> fields, String encoding, String split, String tab, int index){
		 String reg="^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$";
		 SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Pattern p = Pattern.compile(reg);
		List<T> list=new ArrayList<T>();
			String pathName=path+filename;
			System.out.println(pathName);
			File file=new File(pathName);
			if(!file.exists()){
				logger.error("文件不存在！");
			}else{
				FileInputStream fis=null;
				BufferedReader br=null;
				InputStreamReader isr=null;
				try {
					fis=new FileInputStream(file);
					isr=new InputStreamReader(fis,encoding);
		            br=new BufferedReader(isr);
	    			String line="";
					Class cls=obj;
                   int rowNum=0;
	    			while ((line = br.readLine()) != null) {
	    				if(rowNum<index){
	    					rowNum++;
	    				}else{
	    				String str[]=line.split(split);
						T t=(T) cls.newInstance();
						for(int i=0;i<fields.size()&&i<str.length;i++){
							if((null!=fields.get(i))){
								Field field=cls.getDeclaredField(fields.get(i));
								field.setAccessible(true);
								String value=(str[i]==null||str[i].equals("null"))?null:str[i];
								if(field.getType()==int.class||field.getType()==Integer.class){
									field.set(t, Integer.valueOf(value));
								}else if(field.getType()==String.class){
									field.set(t,value);
								}else if(field.getType()==Date.class){
									Matcher matcher=p.matcher(value);
									if(matcher.matches()){
										field.set(t,sdf1.parse(value));
									}else{
										field.set(t,sdf2.parse(value));
									}
								}else if(field.getType()==Double.class||field.getType()==double.class){
									field.set(t, Double.valueOf(value));
								}else if(field.getType()==Long.class||field.getType()==long.class){
									field.set(t, Long.valueOf(value));
								}else if(field.getType()==Boolean.class||field.getType()==boolean.class){
									field.set(t, Boolean.valueOf(value));
								}	
							}

							
	    			  }
						list.add(t);
	    			}
	    			}
	    			fis.close();
	    			isr.close();
	    			br.close();
		              logger.info("读取文件成功："+pathName);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("读取文件报错："+e.getMessage());
				}finally{
					try {
						if(fis!=null){
							fis.close();
						}
						if(isr!=null){
							isr.close();
						}
						if(br!=null){
							br.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}
			}
			
			return list;
		
		
	}
	
	public static void createDir(String... paths){
		for(int i=0;i<paths.length;i++){
			if(paths[i]!=null){
				File file=new File(paths[i]);
				if(!file.isDirectory()){
					file.mkdirs();
				}
			}
			
		}
	
	}

	public static boolean uploadFile(FTPClient client, String filename, String remotePath) {
		boolean flag=false;
		FileInputStream input=null;
		File file=new File(filename);
		try {
			input=new FileInputStream(new File(filename));
			flag= FtpUtil.uploadFile(client, remotePath, file.getName(), input);
		} catch (Exception e) {
			logger.error("上传文件失败："+e.getMessage());
		}
        return flag;
	}
	public static void MoveFile(String FileName, String srcPath, String destPath){
		try {
			createDir(srcPath,destPath);
			File srcFile=new File(srcPath+FileName);
			File destFile=new File(destPath+FileName);
			FileUtils.moveFile(srcFile, destFile);
		} catch (Exception e) {
			logger.error("移动文件报错："+e.getMessage());
		}

	}
	public static void MoveFile(String FilePathName, String destPath){
		try {
			createDir(destPath);
			File srcFile=new File(FilePathName);
			File destFile=new File(destPath+srcFile.getName());
			FileUtils.moveFile(srcFile, destFile);
		} catch (Exception e) {
			logger.error("移动文件["+FilePathName+"]报错："+destPath);
			e.printStackTrace();
		}

	}
	public static void MoveFile(File srcFile, String destPath){
		try {
			createDir(destPath);
			File destFile=new File(destPath+srcFile.getName());
			FileUtils.moveFile(srcFile, destFile);
		} catch (Exception e) {
			logger.error("移动文件["+srcFile.getAbsolutePath()+"]报错："+e.getMessage());
		}

	}
	public static  File[] getFileList(String path, String regex){
		File[] files=new File[]{};
		try {

		File file=new File(path);
		FileNameFilter filter=new FileNameFilter(regex);
		if(!file.exists()){
			logger.error("文件路径不存在："+path);
		}else{
			files=file.listFiles(filter);
		}
		} catch (Exception e) {
			logger.error("获取文件列表异常:"+e.getMessage());
		}
		return files;
	}
    

	
	public static void main(String[] args){
//		List<BlackPhoneLlistSVO> list=new ArrayList<BlackPhoneLlistSVO>();
//		for(int i=0;i<10;i++){
//			BlackPhoneLlistSVO vo=new BlackPhoneLlistSVO();
//			vo.setPhoneNum("86-"+String.valueOf(i));
//			vo.setPhoneArea("湖南"+String.valueOf(i));
//			list.add(vo);
//		}
//		List<String> fields=new ArrayList<String>();
//		fields.add("phoneNum");
//		fields.add("phoneArea");//{"phoneNum","phoneArea"};
//		FileTools.ListToCsv("e:\\test", "test.csv", list, fields,"gbk");
//		CopyFile("test.csv", "e:\\test\\", "e:\\test\\bak\\");
//		String[] files=new String[]{"1","2","3"};
//		ResettableIterator ite= IteratorUtils.arrayIterator(files);
//		while(ite.hasNext()){
//			System.out.println(ite.next());
//		}
//		String path="e:\\test";
//		String path1="e:\\test/";
//		System.out.println(path1.endsWith("\\"));
		File[] files=FileTools.getFileList("e:/home/file/ecdr/err/","^ecdr_\\d{14}\\.csv$");
//		
		
		for(File f:files){
			System.out.println(f.getName());
		}
	}

}
