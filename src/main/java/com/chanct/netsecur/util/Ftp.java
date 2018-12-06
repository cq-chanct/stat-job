package com.chanct.netsecur.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * 
 * @Title:Ftp.java
 * 
 */
public class Ftp {

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
	private static Logger logger = Logger.getLogger(Ftp.class);
	public static boolean uploadFile(String url, int port, String username,
			String password, String path, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器

			ftp.setControlEncoding("GBK");

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);

			conf.setServerLanguageCode("zh");

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
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

	/**
	 * Description: 从FTP服务器下载文件
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
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static boolean downFile(String url, int port, String username,
			String password, String remotePath, String fileName,
			OutputStream is) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);

			ftp.setControlEncoding("GBK");

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);

			conf.setServerLanguageCode("zh");

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			int count = 0;
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					count++;
//					File localFile = new File(localPath + "/" + ff.getName());
//					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
//			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.logout();
			if(count == 0){
				success = false;
			}else{
				success = true;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
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
	
	/**
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param remotePath
	 * @param localPath
	 * @return
	 */
	public static boolean downFile1(String url, int port, String username,
			String password, String remotePath,String  localPath) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			ftp.setBufferSize(1024); 
            //设置文件类型（二进制） 
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE); 
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			ftp.enterLocalPassiveMode();
			reply = ftp.getReplyCode();		
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			int count = 0;
			for (FTPFile ff : fs) {
					count++;
					File localFile = new File(localPath + "/" + ff.getName());
					OutputStream  is = new FileOutputStream(localFile);
					ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
					//设置字节格式
					ftp.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
					ftp.setControlEncoding("UTF-8");
					ftp.enterLocalPassiveMode();
					
					ftp.retrieveFile(ff.getName(), is);
					is.close();
			}
			ftp.logout();
			if(count == 0){
				success = false;
			}else{
				success = true;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}

/*	public static void main(String agrs[]) {
		try {
			boolean flag = downFile1("172.30.30.231", 21, "pictureftp",
					"pictureftp", "/html",  "/file/feel/file");
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	
	/**
	 * 移动文件
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param remotePathFrom
	 * @param remotePathTo
	 * @return
	 */
	public static boolean moveFile1(String url, int port, String username,
			String password, String remotePathFrom, String remotePathTo) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);

			ftp.setControlEncoding("GBK");

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);

			conf.setServerLanguageCode("zh");

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			ftp.enterLocalPassiveMode();
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePathFrom);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			int count = 0;
			for (FTPFile ff : fs) {
					count++;
//					File localFile = new File(localPath + "/" + ff.getName());
//					OutputStream  is = new FileOutputStream(localFile);
//					ftp.retrieveFile(ff.getName(), is);
//					is.close();
					ftp.rename(remotePathFrom + "/" + ff.getName(), remotePathTo + "/" + ff.getName());
			}
//			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.logout();
			if(count == 0){
				success = false;
			}else{
				success = true;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
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