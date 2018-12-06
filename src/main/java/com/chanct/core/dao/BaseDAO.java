package com.chanct.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.chanct.core.util.BeanUtil;

/**
 *
 *
 * @author : lihx
 * create date : 2013-7-4
 */

public class BaseDAO  {

	@SuppressWarnings("unused")
	private static Object lock = new Object();
	/**
	 * 执行自定义查询
	 * @param sql 自定义sql语句
	 * @return map的查询结果
	 */
	public <K,V> List<Map<K ,V >> executeSelectSQL(@Param(value="sql")String sql) {
		synchronized (sql) {
			return BeanUtil.getSession().selectList("executeSelectSQL", sql);
		}
	}
	
	/**
	 * 执行自定义的insert sql语句
	 * @param sql 定义的sql语句
	 * @return 执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int executeInsertSQL(@Param(value="sql")String sql) {
		synchronized (sql) {
			return BeanUtil.getSession().insert("executeInsertSQL", sql);
		}
	}
	/**
	 * 执行自定义的update sql语句
	 * @param sql 定义的sql语句
	 * @return 执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int executeUpdateSQL(@Param(value="sql")String sql) {
		synchronized (sql) {
			return BeanUtil.getSession().update("executeUpdateSQL", sql);
		}
	}
	
	/**
	 * 执行自定义的delete sql语句
	 * @param sql 定义的sql语句
	 * @return 执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int executeDeleteSQL(@Param(value="sql")String sql) {
		synchronized (sql) {
			return BeanUtil.getSession().delete("executeDeleteSQL", sql);
		}
	}
	
	/**
	 * 返回指定类型的List集合
	 * @param method 要执行的mapper中的id
	 * @return
	 */
	public <T> List<T> selectList(String method){
		synchronized (method) {
			return BeanUtil.getSession().selectList(method);
		}
	}
	/**
	 * 返回指定类型的List集合
	 * @param method 要执行的mapper中的id
	 * @param entity 指定类型的数据
	 * @return list集合
	 */
	public <T> List<T> selectList(String method,Object entity){
		synchronized (method) {
			return BeanUtil.getSession().selectList(method, entity);
		}
	}
	/**
	 * 返回指定类型的List集合
	 * @param method 要执行的mapper中的id
	 * @param entity 指定类型的数据
	 * @return list集合
	 */
	public <T> List<T> selectList(String method,Object entity,String dbIndex){
		synchronized (method) {
			return BeanUtil.getOtherSession(dbIndex).selectList(method, entity);
		}
	}
	
	/**
	 * 分页查询列表
	 * @param method 要执行的mapper中的id
	 * @param entity 指定类型的数据
	 * @param pageNo 页数
	 * @param pageSize 每页显示数量
	 * @return
	 */
	public <T> List<T> selectList(String method,T entity,int pageNo ,int pageSize){
		synchronized (method) {
			int offset = pageSize*(pageNo-1);//(nfl修改的)
			//	new RowBounds("查询开始的记录数","每页的记录数")			
			List<T> list = BeanUtil.getSession().selectList(method, entity , new RowBounds(offset,pageSize));
			return list;
		}
	}
	
	/**
	 * 分页查询列表
	 * @param method 要执行的mapper中的id
	 * @param entity 指定类型的数据
	 * @param pageNo 页数
	 * @param pageSize 每页显示数量
	 * @return
	 */
	public <T> List<T> selectList(String method,T entity,int pageNo ,int pageSize,String dbIndex){
		synchronized (method) {
			int offset = pageSize*(pageNo-1);//(nfl修改的)
			//	new RowBounds("查询开始的记录数","每页的记录数")			
			List<T> list = BeanUtil.getOtherSession(dbIndex).selectList(method, entity , new RowBounds(offset,pageSize));
			return list;
		}
	}
	
	/**
	 * 分页查询列表
	 * @param method 要执行的mapper中的id
	 * @param pageNo 页数
	 * @param pageSize 每页显示数量
	 * @return
	 */
	public <T> List<T> selectList(String method,int pageNo ,int pageSize){
		synchronized (method) {
			return BeanUtil.getSession().selectList(method , new RowBounds(pageSize*(pageNo-1),pageSize));
		}
	}
	
	/**
	 * 返回指定类型数据
	 * @param method 要执行的mapper中的id
	 * @return 结果
	 */
	public <T> T selectObject(String method) {
		synchronized (method) {
			return BeanUtil.getSession().selectOne(method);
		}
	}
	
	/**
	 * 返回指定类型数据
	 * @param method 要执行的mapper中的id
	 * @param entity 指定类型的数据
	 * @return 结果
	 */
	public <T> T selectObject(String method,Object entity) {
		synchronized (method) {
			return BeanUtil.getSession().selectOne(method,entity);
		}
	}
	
	public <T> T selectObject(String method,Object entity,String dbIndex) {
		synchronized (method) {
			return BeanUtil.getOtherSession(dbIndex).selectOne(method,entity);
		}
	}
	/**
	 * 根据条件查询，返回map键名键值的集合
	 * @param method  要执行的mapper中的id
	 * @return map键名键值的集合
	 */
	public <K,V> List<Map<K ,V >> selectMap(String method) {
		synchronized (method) {
			return BeanUtil.getSession().selectList(method);
		}
	}
	/**
	 * 根据条件查询，返回map键名键值的集合
	 * @param method  要执行的mapper中的id
	 * @param entity 条件对象
	 * @return map键名键值的集合
	 */
	public <K,V> List<Map<K ,V >> selectMap(String method,Object entity) {
		synchronized (method) {
			return BeanUtil.getSession().selectList(method,entity);
		}
	}
	
	/**
	 * 根据条件查询，返回map键名键值的集合
	 * @param method  要执行的mapper中的id
	 * @param entity 条件对象
	 * @param dbIndex 数据库配置号："dbcp"+dbIndex+".properties"
	 * @return map键名键值的集合
	 */
	public <K,V> List<Map<K ,V >> selectMap(String method,Object entity,String dbIndex) {
		synchronized (method) {
			return BeanUtil.getOtherSession(dbIndex).selectList(method,entity);
		}
	}
	/**
	 * 返回指定key的map
	 * @param statement
	 * @param entity
	 * @param mapKey
	 * @return
	 */
	public <K,V> Map<K,V> selectMapKey(String statement, Object entity, String mapKey) {
		return BeanUtil.getSession().selectMap(statement, entity, mapKey);
	}
	/**
	 * 插入数据
	 * @param method 要执行的mapper中的id
	 * @param entity 插入对象，当传入的entity为List时，批量插入
	 * @param dbIndex
	 * @return  执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int insertObject(String method, Object entity,String dbIndex) {
		synchronized (method) {
			int result = BeanUtil.getOtherSession(dbIndex).insert(method, entity);
//		Transaction newTransaction = transactionFactory.newTransaction(BeanUtil.getSession().getConnection());
//		try {
//			newTransaction.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			return result;
		}
	}
	/**
	 * 插入数据
	 * @param method 要执行的mapper中的id
	 * @param entity 插入对象，当传入的entity为List时，批量插入
	 * @return  执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int insertObject(String method, Object entity) {
		synchronized (method) {
			int result = BeanUtil.getSession().insert(method, entity);
//		Transaction newTransaction = transactionFactory.newTransaction(BeanUtil.getSession().getConnection());
//		try {
//			newTransaction.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			return result;
		}
	}
	
	/**
	 * 修改数据
	 * @param method 要执行的mapper中的id
	 * @param entity 修改对象
	 * @param dbIndex
	 * @return  执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int updateObject(String method,Object entity,String dbIndex) {
		synchronized (method) {
			return BeanUtil.getOtherSession(dbIndex).update(method, entity);
		}
	}
	/**
	 * 修改数据
	 * @param method 要执行的mapper中的id
	 * @param entity 修改对象
	 * @return  执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int updateObject(String method,Object entity) {
		synchronized (method) {
			return BeanUtil.getSession().update(method, entity);
		}
	}	
	/**
	 * 删除数据
	 * @param method 要执行的mapper中的id
	 * @param entity 删除对象条件
	 * @return  执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int deleteObject(String method,Object entity) {
		synchronized (method) {
			return BeanUtil.getSession().delete(method, entity);
		}
	}
	/**
	 * 删除数据
	 * @param method 要执行的mapper中的id
	 * @param entity 删除对象条件
	 * @return  执行影响行数 ，为0表示未执行成功 大于0表示成功
	 */
	public int deleteObject(String method,Object entity,String dbIndex) {
		synchronized (method) {
			return BeanUtil.getOtherSession(dbIndex).delete(method, entity);
		}
	}
	
	/**
	 * 返回总数
	 * @param method
	 * @param entity
	 * @return
	 */
	public long selectTotal(String method,Object entity) {
		synchronized (method) {
			return BeanUtil.getSession().selectOne(method, entity);
		}
	}
	
}
