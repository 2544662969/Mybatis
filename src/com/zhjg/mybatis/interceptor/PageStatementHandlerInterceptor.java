package com.zhjg.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.zhjg.mybatis.util.PageInfo_;

@Intercepts(
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
)
public class PageStatementHandlerInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		System.out.println("当前分页拦截器是：>>>>>>>"+this.getClass().getName());
		
		//获取原本要执行的sql语句
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		String sql = boundSql.getSql();
		//创建查询记录数Sql
		String countSql = "select count(*) cnt "+sql.substring(sql.indexOf("from"));
		System.out.println(countSql);
		//查询总记录数
		Connection connection = (Connection)invocation.getArgs()[0];
		PreparedStatement statement = connection.prepareStatement(countSql);
		ResultSet resultSet = statement.executeQuery();
		int totalCount = 0;
		if(resultSet.next()){
			totalCount = resultSet.getInt("cnt");
		}
		//关闭资源，省略异常处理;
		//StatementHandler的执行是以Connection作为参数的，所以这里不能关闭Connection
		resultSet.close();
		statement.close();
		//获取参数得到分页信息
		Object parameter = statementHandler.getParameterHandler().getParameterObject();
		PageInfo_<?> pageInfo = null;
		if(parameter instanceof PageInfo_<?>){
			pageInfo = (PageInfo_<?>)parameter;
		}
		//设置总记录数
		pageInfo.setTotalCount(totalCount);
		//创建分页Sql
		String pageSql = sql + " limit "+pageInfo.getOffset()+" , "+pageInfo.getPageSize();
		System.out.println(pageSql);
		//用分页Sql替换原来的Sql(关键操作)
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);  
		metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
		//StatementHandler继续执行
		Object result = invocation.proceed();
		return result;
	}

	@Override
	public Object plugin(Object target) {
		if(target instanceof StatementHandler){
			return Plugin.wrap(target, this);
		}
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

	
}
