package com.chanct.core.util;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SftpClicentConnect {
	
	/**
	 * sftp文件配置
	 */
	private static Logger logger = Logger.getLogger(SftpClicentConnect.class);
	private Session sshSession = null;
	private Channel channel = null;
	private ChannelSftp sftp = null;
	
	/**
	 * connect server via sftp
	 */
	public void connect(String username, String host, String password,int port) {
		try {
		
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			this.sshSession = jsch.getSession(username, host, port);
			logger.info("Session created.");
			this.sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			this.sshSession.setConfig(sshConfig);
			this.sshSession.connect();
			this.channel = this.sshSession.openChannel("sftp");
			this.channel.connect();
			sftp = (ChannelSftp) channel;
			logger.info("Connected to " + host + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断开服务器连接
	 */
	public void disconnect() {
		logger.info("sftp is reading to closing");
		try{
			if (this.sftp != null) {
				if (this.sftp.isConnected()) {
					this.sftp.disconnect();
					channel.disconnect();
					sshSession.disconnect();
				} else if (this.sftp.isClosed()) {
					logger.info("sftp is closed already");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Session getSshSession() {
		return sshSession;
	}

	public void setSshSession(Session sshSession) {
		this.sshSession = sshSession;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public ChannelSftp getSftp() {
		return sftp;
	}

	public void setSftp(ChannelSftp sftp) {
		this.sftp = sftp;
	}
	

}
