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
 * 分页插件：通过拦截Executor接口实现
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
		
		System.out.println("当前分页拦截器是：>>>>>>>"+this.getClass().getName());
		
		// 获取Executor中MappedStatement类型的参数
		Object[] args = invocation.getArgs();
		MappedStatement statement = (MappedStatement) args[0];

		Object parameter = (Object) args[1];
		PageInfo_<?> pageInfo = (PageInfo_<?>) parameter;

		// 获取sql
		BoundSql boundSql = statement.getBoundSql((Object) args[1]);
		String sql = boundSql.getSql();

		// 创建查询总数的sql
		String countSql = "select count(*) cnt "
				+ sql.substring(sql.indexOf("from"));
		// 从MappedStatement中获取连接，通过jdbc的查询方式获取totalCount
		Connection connection = statement.getConfiguration().getEnvironment()
				.getDataSource().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(countSql);
		ResultSet resultSet = preparedStatement.executeQuery();
		int totalCount = 0;
		if (resultSet.next()) {
			totalCount = resultSet.getInt("cnt");
		}
		// 省略异常处理
		resultSet.close();
		preparedStatement.close();
		connection.close();
		pageInfo.setTotalCount(totalCount);

		// 创建分页sql
		String pageSql = sql + " limit " + pageInfo.getOffset() + " , "
				+ pageInfo.getPageSize();
		// 用修改后的sql替换statement中原来的sql
		SqlSource sqlSource = new StaticSqlSource(statement.getConfiguration(),
				pageSql);
		SystemMetaObject.forObject(statement).setValue("sqlSource", sqlSource);
		// 完成替换后executor继续执行
		Object result = invocation.proceed();
		return result;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			// 创建Executor代理对象,织入插件的增强功能（分页）
			return Plugin.wrap(target, this);
		}
		// 调用原来的executor的query方法（mapper中定义的sql）
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		System.out.println("key>>>>>>>>>>>>>>>>>"
				+ properties.getProperty("key"));
	}

}
