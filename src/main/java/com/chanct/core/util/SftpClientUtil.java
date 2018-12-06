package com.chanct.core.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;

public class SftpClientUtil {
	
	/**
	 * sftp文件配置
	 */
	private static Logger logger = Logger.getLogger(SftpClientUtil.class);
	/**
	 * 列出目录名
	 * @param directory
	 * @return
	 */
	public static List<String> listDirNames(String directory,ChannelSftp sftp) {
		List<String> result = new ArrayList<String>();
		try {
			@SuppressWarnings("unchecked")
			List<LsEntry> listEntity = sftp.ls(directory);
			if(listEntity != null && listEntity.size() >= 0){
				for(LsEntry entry : listEntity){
					if(entry.getAttrs().isDir() && !".".equals(entry.getFilename()) && !"..".equals(entry.getFilename())){
						result.add(entry.getFilename());
					}
				}
			}
		} catch (SftpException e) {
			e.printStackTrace();
			logger.error("listDirNames : " + directory, e);
		}

		return result;
	}
	
	/**
	 * 列出文件名
	 * @param directory
	 * @return
	 */
	public static List<String> listFileNames(String directory,ChannelSftp sftp){
		List<String> result = new ArrayList<String>();
		try {
			@SuppressWarnings("unchecked")
			List<LsEntry> listEntity = sftp.ls(directory);
			if(listEntity != null && listEntity.size() >= 0){
				for(LsEntry entry : listEntity){
					if(!entry.getAttrs().isDir()){
						result.add(entry.getFilename());
					}
				}
			}
		} catch (SftpException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	/**
	 * 移动文件
	 * @param oldpath
	 * @param newpath
	 */
	public static void moveFile(String oldpath,String newpath,ChannelSftp sftp){
		try {
			sftp.rename(oldpath, newpath);
		} catch (SftpException e) {
			logger.error("move file from " + oldpath + " to " + newpath + "", e);
		}
	}

	/**
	 * 创建目录
	 * @param directory
	 */
	public static void makeDir(String directory,ChannelSftp sftp){
		try {
			sftp.cd(directory);
		} catch (SftpException e) {
			mkDir(directory,sftp);
		}
	}
	
	/**
	 * 创建指定文件夹
	 * 
	 * @param dirName dirName
	 */
	public static void mkDir(String dirName, ChannelSftp sftp) {
		String[] dirs = dirName.split("/");
		try {
			sftp.cd("/" + dirs[1] + "/");
			String now = sftp.pwd();
			for (int i = 2; i < dirs.length; i++) {
				if (!openDir(sftp.pwd() + "/" + dirs[i],sftp)) {
					sftp.mkdir(dirs[i]);
					sftp.cd(dirs[i]);
				}
			}
			sftp.cd(now);
		} catch (SftpException e) {
			e.printStackTrace();
			logger.error("mkDir Exception : " + e);
		}
	}
	
	/**
	 * 打开指定目录
	 * 
	 * @param directory
	 *            directory
	 * @return 是否打开目录
	 */
	public static boolean openDir(String directory, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			return true;
		} catch (SftpException e) {
			logger.error("openDir Exception : " + e);
			return false;
		}
	}
	public static InputStream getFileInputStream(String filePath,ChannelSftp sftp){
		InputStream ins = null;
		try {
			ins = sftp.get(filePath);
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return ins;
	}
	
	

}
