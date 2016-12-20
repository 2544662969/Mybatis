package com.zhjg.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	private static SqlSessionFactory factory;
	
	
	/**
	 * ��������SqlSessionFactory
	 */
	private static void createSqlSessionFactory(){
		if(factory == null){
			synchronized (MybatisUtil.class) {
				if(factory == null){
					InputStream is = null;
					try {
						is = Resources.getResourceAsStream("mybatis-config.xml");
					} catch (IOException e) {
						e.printStackTrace();
					}
					SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
					factory = builder.build(is);
				}
			}
		}
	}
	
	/**
	 * ����SqlSession
	 * @return
	 */
	public static SqlSession getSqlSession(){
		if(factory == null)
			createSqlSessionFactory();
		SqlSession session = factory.openSession(true);
		return session;
		
	}
	
	/**
	 * �ر�SqlSession
	 */
	public static void closeSqlSession(SqlSession session){
		try{}
		finally{
			if(session != null)
				session.close();
		}
		
	}
}
