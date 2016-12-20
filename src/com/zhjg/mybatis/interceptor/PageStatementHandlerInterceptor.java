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
		
		System.out.println("��ǰ��ҳ�������ǣ�>>>>>>>"+this.getClass().getName());
		
		//��ȡԭ��Ҫִ�е�sql���
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		String sql = boundSql.getSql();
		//������ѯ��¼��Sql
		String countSql = "select count(*) cnt "+sql.substring(sql.indexOf("from"));
		System.out.println(countSql);
		//��ѯ�ܼ�¼��
		Connection connection = (Connection)invocation.getArgs()[0];
		PreparedStatement statement = connection.prepareStatement(countSql);
		ResultSet resultSet = statement.executeQuery();
		int totalCount = 0;
		if(resultSet.next()){
			totalCount = resultSet.getInt("cnt");
		}
		//�ر���Դ��ʡ���쳣����;
		//StatementHandler��ִ������Connection��Ϊ�����ģ��������ﲻ�ܹر�Connection
		resultSet.close();
		statement.close();
		//��ȡ�����õ���ҳ��Ϣ
		Object parameter = statementHandler.getParameterHandler().getParameterObject();
		PageInfo_<?> pageInfo = null;
		if(parameter instanceof PageInfo_<?>){
			pageInfo = (PageInfo_<?>)parameter;
		}
		//�����ܼ�¼��
		pageInfo.setTotalCount(totalCount);
		//������ҳSql
		String pageSql = sql + " limit "+pageInfo.getOffset()+" , "+pageInfo.getPageSize();
		System.out.println(pageSql);
		//�÷�ҳSql�滻ԭ����Sql(�ؼ�����)
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);  
		metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
		//StatementHandler����ִ��
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
