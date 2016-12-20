package com.zhjg.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.zhjg.mybatis.util.PageInfo_;

/**
 * <p>
 * ��ҳ�����ͨ������Executor�ӿ�ʵ��
 * </p>
 * @author 327084
 * 
 */

@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }), })
public class PageExecutorInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		System.out.println("��ǰ��ҳ�������ǣ�>>>>>>>"+this.getClass().getName());
		
		// ��ȡExecutor��MappedStatement���͵Ĳ���
		Object[] args = invocation.getArgs();
		MappedStatement statement = (MappedStatement) args[0];

		Object parameter = (Object) args[1];
		PageInfo_<?> pageInfo = (PageInfo_<?>) parameter;

		// ��ȡsql
		BoundSql boundSql = statement.getBoundSql((Object) args[1]);
		String sql = boundSql.getSql();

		// ������ѯ������sql
		String countSql = "select count(*) cnt "
				+ sql.substring(sql.indexOf("from"));
		// ��MappedStatement�л�ȡ���ӣ�ͨ��jdbc�Ĳ�ѯ��ʽ��ȡtotalCount
		Connection connection = statement.getConfiguration().getEnvironment()
				.getDataSource().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(countSql);
		ResultSet resultSet = preparedStatement.executeQuery();
		int totalCount = 0;
		if (resultSet.next()) {
			totalCount = resultSet.getInt("cnt");
		}
		// ʡ���쳣����
		resultSet.close();
		preparedStatement.close();
		connection.close();
		pageInfo.setTotalCount(totalCount);

		// ������ҳsql
		String pageSql = sql + " limit " + pageInfo.getOffset() + " , "
				+ pageInfo.getPageSize();
		// ���޸ĺ��sql�滻statement��ԭ����sql
		SqlSource sqlSource = new StaticSqlSource(statement.getConfiguration(),
				pageSql);
		SystemMetaObject.forObject(statement).setValue("sqlSource", sqlSource);
		// ����滻��executor����ִ��
		Object result = invocation.proceed();
		return result;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			// ����Executor�������,֯��������ǿ���ܣ���ҳ��
			return Plugin.wrap(target, this);
		}
		// ����ԭ����executor��query������mapper�ж����sql��
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		System.out.println("key>>>>>>>>>>>>>>>>>"
				+ properties.getProperty("key"));
	}

}
