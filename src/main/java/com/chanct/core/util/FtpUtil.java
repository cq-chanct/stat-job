package com.chanct.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FtpUtil {


//	private static String host = Constant.CNCERT_FTP_HOST; // 服务器IP地址
//	private static String username = Constant.CNCERT_FTP_USERNAME; // 用户名
//	private static String password = Constant.CNCERT_FTP_PASSWORD; // 密码
//	private static int port = Integer.parseInt(Constant.CNCERT_FTP_PORT);// 国家中心ftp端口号
//	private static String ftpResultCncertFilePath = Constant.FTP_RESULT_CNCERT_FILE_PATH; // 国家中心下发数据的存放目录
//	private static IspCncertService ispCncertService = new IspCncertServiceImpl();
//	private static SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 
	
	public static final int ASCII_FILE_TYPE = FTP.ASCII_FILE_TYPE;
	private static Logger logger = Logger.getLogger(FtpUtil.class);
	public static String encoding = "UTF-8";
    
	/**
	 * connect server via sftp
	 */
	public static FTPClient connect(String host,int port,String username,String password,String ftpResultCncertFilePath) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(host, port);

			logger.info("连接到:" + host + ",端口:" + port);
			int replyCode = ftpClient.getReplyCode();
			logger.info("ftp服务器应答:" + replyCode);
			boolean loginFlag = ftpClient.login(username, password);
			replyCode = ftpClient.getReplyCode();
			if (!loginFlag) {
				// 登录错误
				logger.error("登录错误:" + replyCode);
			}
			ftpClient.setFileType(ASCII_FILE_TYPE);
			ftpClient.setControlEncoding(MBaoUtil.strencoding);
			// ftpClient.enterLocalPassiveMode();
			ftpClient.enterLocalActiveMode();
			ftpClient.getRemoteAddress();
			// 切换到指定目录
			if (StringUtils.isNotBlank(ftpResultCncertFilePath)) {
				ftpClient.changeWorkingDirectory(ftpResultCncertFilePath);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftpClient;
	}
    
	/**
	 * 返回指定目录下的所有目录名称
	 * 
	 * @param path 相对路径
	 * @exception Exception  ftp异常
	 * @return 目录名称
	 */
	public static List<String> getDirectoryList(String path,FTPClient ftpClient){
		List<String> retList = new ArrayList<String>();
		try {
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(path);
			FTPFile[] ftpFiles = ftpClient.listFiles(path);
			if (ftpFiles != null && ftpFiles.length > 0) {
				for (FTPFile ftpFile : ftpFiles) {
					if (ftpFile.isDirectory()) {
						retList.add(ftpFile.getName());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(path);
			e.printStackTrace();
			logger.error("取得目录文件列表发生错误,原因:" + e.getMessage());
		}
		return retList;
	}
    
	/**
	 * 返回指定目录下的所有文件名称
	 * 
	 * @param path 相对路径
	 * @exception Exception  ftp异常
	 * @return 目录名称
	 */
	public static List<FTPFile> getFileList(String path,FTPClient ftpClient){
		List<FTPFile> retList = new ArrayList<FTPFile>();
		try {
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(path);
			FTPFile[] ftpFiles = ftpClient.listFiles(path);
			if (ftpFiles != null && ftpFiles.length > 0) {
				for (FTPFile ftpFile : ftpFiles) {
					if (ftpFile.isFile()) {
						retList.add(ftpFile);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(path);
			e.printStackTrace();
			logger.error("取得目录文件列表发生错误,原因:" + e.getMessage());
		}
		return retList;
	}
    
	/**
	 * 下载文件
	 * 
	 * @param filePath 远程文件路径
	 * @return 操作是否成功
	 */
	public static InputStream getFileInputStream(String filePath,FTPClient ftpClient){
		InputStream is = null;
		try {
			System.out.println(filePath);
			is = ftpClient.retrieveFileStream(filePath);
			ftpClient.completePendingCommand();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("下载文件发生错误,原因:" + e.getMessage());
		}
		return is;
	}

	/**
	 * 返回指定目录下的所有文件名称和大小的map
	 * 
	 * @param path 相对路径
	 * @exception Exception  ftp异常
	 * @return 目录名称
	 */
	public static Map<String,Long> getFileNameSizeMap(String path,FTPClient ftpClient){
		Map<String,Long> retMap = new HashMap<String, Long>();  
		try {
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(path);
			FTPFile[] ftpFiles = ftpClient.listFiles(path);
			if (ftpFiles != null && ftpFiles.length > 0) {
				for (FTPFile ftpFile : ftpFiles) {
					if (ftpFile.isFile()) {
						retMap.put(ftpFile.getName(), ftpFile.getSize());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(path);
			e.printStackTrace();
			logger.error("取得目录文件列表发生错误,原因:" + e.getMessage());
		}
		return retMap;
	}
	/**
	 * 移动文件
	 * 
	 * @param oldpath
	 * @param newpath
	 */
	public static void moveFile(String oldpath, String newpath,FTPClient ftpClient) {
		try {
			ftpClient.rename(oldpath, newpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建目录
	 * @param directory
	 */
	public static void makeDir(String directory,String eventDirName,FTPClient ftpClient){
		if(!existDirectory(directory,ftpClient)){
			createDirectory(directory,ftpClient);
		}
		if(!existDirectory(directory + eventDirName + "/",ftpClient)){
			createDirectory(directory + eventDirName + "/",ftpClient);
		}
	}
	
	/**
	 * 判断目录是否存在
	 * 
	 * @param path
	 *            相对路径
	 * @exception Exception
	 *                ftp异常
	 * @return 目录是否存在
	 */
	public static boolean existDirectory(String path,FTPClient ftpClient){
		boolean flag = false;
		try {
			ftpClient.changeWorkingDirectory(path);
			FTPFile[] ftpFileArr = ftpClient.listFiles(path);
			for (FTPFile ftpFile : ftpFileArr) {
				if (ftpFile.isDirectory() && ftpFile.getName().equalsIgnoreCase(path)) {
					flag = true;
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("判断目录是否存在发生错误,原因:" + e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 创建目录
	 * 
	 * @param pathName
	 *            相对路径
	 * @exception Exception
	 *                ftp异常
	 * @return 操作是否成功
	 */
	public static void createDirectory(String pathName,FTPClient ftpClient){
		try {
			ftpClient.makeDirectory(pathName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建ftp目录发生错误,原因:" + e.getMessage());
		}
	}
    
	/**
	 * 断开服务器连接
	 */
	public static void disconnect(FTPClient ftpClient) {
		logger.info("ftp is reading to closing");
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
	
	
	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @Version1.0
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(FTPClient ftp, String path, String filename, InputStream input) {
		boolean success = false;
		try {
			ftp.changeWorkingDirectory(path);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.storeFile(filename, input);
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
}
