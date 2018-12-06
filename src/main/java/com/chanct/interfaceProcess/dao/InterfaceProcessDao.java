package com.chanct.interfaceProcess.dao;


import com.chanct.interfaceProcess.entity.InterfaceProcess;

import java.util.List;
import java.util.Map;

public interface InterfaceProcessDao {

	List<Map<String,String>> queryLastTime();

	public List<InterfaceProcess>  query();

	public int insertProcess(List<InterfaceProcess> list);

	int updateProcess();

	public int deleteProcess();

}
