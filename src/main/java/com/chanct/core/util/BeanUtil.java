package com.chanct.core.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.log4j.Logger;

import com.chanct.core.domain.Dbproperty;
import com.chanct.core.exception.AppISMPException;

/**
 * 
 * 
 */
public class BeanUtil {
    private static Logger logger = Logger.getLogger(BeanUtil.class);
	private static SqlSession session;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map<String,SqlSession> otherSession = new HashMap();
	private static int otherSession_num = 0;
	public static <T> T getMapper(Class<T> c) throws AppISMPException {
		if (session == null)
			createSession();
		return (T) session.getMapper(c);
	}

	private static void createSession() {
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			session = sqlSessionFactory.openSession();
			logger.info("createSession ...................................................................................");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		if (session == null)
			createSession();
		return session;
	}
	
	public static Transaction newTransaction() throws SQLException{
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		return transactionFactory.newTransaction(getSession().getConnection());	
	}
	
	public static Transaction newTransaction(String dbIndex) throws SQLException{
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		return transactionFactory.newTransaction(getOtherSession(dbIndex).getConnection());	
	}
	
//	创建new session
	public static SqlSession getOtherSession(String driver,String url,String user,String pwd) {
		try {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(pwd);
			TransactionFactory transactionFactory = new JdbcTransactionFactory();// 定义事务工厂
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration configuration = new Configuration(environment);
			configuration("mybatis-config.xml", configuration);

			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
			otherSession_num ++;
			logger.info("createOtherSession otherSession_num="+otherSession_num+"..................................................................................");
			return sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static SqlSession getOtherSession(String dbIndex) {
		SqlSession sqlSession = null;
		try {
			
			if(otherSession.containsKey(dbIndex)){				
				sqlSession= otherSession.get(dbIndex);
				if(sqlSession==null){
					Dbproperty db = PropertyUtil.getDBConfig(dbIndex);
					if(db!=null){					
						sqlSession = getOtherSession(db.getDriver(),db.getUrl(),db.getUsername(),db.getPassword());
						if(sqlSession!=null){
							otherSession.put(dbIndex, sqlSession);							
						}						
					}
				}
			}
			else{
				Dbproperty db = PropertyUtil.getDBConfig(dbIndex);
				if(db!=null){
					sqlSession = getOtherSession(db.getDriver(),db.getUrl(),db.getUsername(),db.getPassword());
					if(sqlSession!=null){
						otherSession.put(dbIndex, sqlSession);							
					}						
				}
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSession;
			
	}

//	获取自定义配置
	private static void configuration(String resource, Configuration config) throws IOException {

		InputStream inputStream = Resources.getResourceAsStream(resource);
		XPathParser parser = new XPathParser(inputStream, true, null, new XMLMapperEntityResolver());
		typeAliasesElement(parser.evalNode("/configuration").evalNode("typeAliases"), config);
		settingsElement(parser.evalNode("/configuration").evalNode("settings"), config);
		mapperElement(parser.evalNode("/configuration").evalNode("mappers"), config);

	}

	@SuppressWarnings("rawtypes")
	private static void typeAliasesElement(XNode parent, Configuration configuration) throws IOException {
		if (parent != null) {
			for (Iterator i$ = parent.getChildren().iterator(); i$.hasNext();) {
				XNode child = (XNode) i$.next();
				if ("package".equals(child.getName())) {
					String typeAliasPackage = child.getStringAttribute("name");
					configuration.getTypeAliasRegistry().registerAliases(typeAliasPackage);
				} else {
					String alias = child.getStringAttribute("alias");
					String type = child.getStringAttribute("type");
					try {
						Class clazz = Resources.classForName(type);
						if (alias == null)
							configuration.getTypeAliasRegistry().registerAlias(clazz);
						else
							configuration.getTypeAliasRegistry().registerAlias(alias, clazz);
					} catch (ClassNotFoundException e) {
						throw new BuilderException((new StringBuilder()).append("Error registering typeAlias for '")
								.append(alias).append("'. Cause: ").append(e).toString(), e);
					}
				}
			}

		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void settingsElement(XNode context, Configuration configuration) {
		if (context != null) {
			Properties props = context.getChildrenAsProperties();
			MetaClass metaConfig = MetaClass.forClass(Configuration.class);
			for (Iterator i$ = props.keySet().iterator(); i$.hasNext();) {
				Object key = i$.next();
				if (!metaConfig.hasSetter(String.valueOf(key)))
					throw new BuilderException((new StringBuilder()).append("The setting ").append(key)
							.append(" is not known.  Make sure you spelled it correctly (case sensitive).").toString());
			}
			configuration.setAutoMappingBehavior(AutoMappingBehavior.valueOf(stringValueOf(
					props.getProperty("autoMappingBehavior"), "PARTIAL")));
			configuration.setCacheEnabled(booleanValueOf(props.getProperty("cacheEnabled"), Boolean.valueOf(true))
					.booleanValue());
			configuration.setLazyLoadingEnabled(booleanValueOf(props.getProperty("lazyLoadingEnabled"),
					Boolean.valueOf(false)).booleanValue());
			configuration.setAggressiveLazyLoading(booleanValueOf(props.getProperty("aggressiveLazyLoading"),
					Boolean.valueOf(true)).booleanValue());
			configuration.setMultipleResultSetsEnabled(booleanValueOf(props.getProperty("multipleResultSetsEnabled"),
					Boolean.valueOf(true)).booleanValue());
			configuration.setUseColumnLabel(booleanValueOf(props.getProperty("useColumnLabel"), Boolean.valueOf(true))
					.booleanValue());
			configuration.setUseGeneratedKeys(booleanValueOf(props.getProperty("useGeneratedKeys"),
					Boolean.valueOf(false)).booleanValue());
			configuration.setDefaultExecutorType(ExecutorType.valueOf(stringValueOf(
					props.getProperty("defaultExecutorType"), "SIMPLE")));
			configuration
					.setDefaultStatementTimeout(integerValueOf(props.getProperty("defaultStatementTimeout"), null));
			configuration.setMapUnderscoreToCamelCase(booleanValueOf(props.getProperty("mapUnderscoreToCamelCase"),
					Boolean.valueOf(false)).booleanValue());
			configuration.setSafeRowBoundsEnabled(booleanValueOf(props.getProperty("safeRowBoundsEnabled"),
					Boolean.valueOf(false)).booleanValue());
			configuration.setLocalCacheScope(LocalCacheScope.valueOf(stringValueOf(
					props.getProperty("localCacheScope"), "SESSION")));
			configuration.setJdbcTypeForNull(JdbcType.valueOf(stringValueOf(props.getProperty("jdbcTypeForNull"),
					"OTHER")));
			configuration.setLazyLoadTriggerMethods(stringSetValueOf(props.getProperty("lazyLoadTriggerMethods"),
					"equals,clone,hashCode,toString"));
			configuration.setSafeResultHandlerEnabled(booleanValueOf(props.getProperty("safeResultHandlerEnabled"),
					Boolean.valueOf(true)).booleanValue());
		}
	}
	@SuppressWarnings("rawtypes")
	private static void mapperElement(XNode parent, Configuration configuration) throws IOException {
		for (Iterator i$ = parent.getChildren().iterator(); i$.hasNext();) {
			XNode child = (XNode) i$.next();
			String resource = child.getStringAttribute("resource");
			InputStream inputStream = Resources.getResourceAsStream(resource);
			XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource,
					configuration.getSqlFragments());
			mapperParser.parse();
		}
	}
	private static String stringValueOf(String value, String defaultValue) {
		return value != null ? value : defaultValue;
	}

	private static Boolean booleanValueOf(String value, Boolean defaultValue) {
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	private static Integer integerValueOf(String value, Integer defaultValue) {
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Set stringSetValueOf(String value, String defaultValue) {
		value = value != null ? value : defaultValue;
		return new HashSet(Arrays.asList(value.split(",")));
	}
}
