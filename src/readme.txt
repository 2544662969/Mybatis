权限相关是mybatis逆向工程自动生成
car手写基本数据库操作的映射文件,使用SqlSession和RowBounds进行分页查询(执行carTest分页时应先注销掉分页插件，否则会因为PageInfo造成空指针异常)
DEBUG [java.sql.PreparedStatement] - ==>  Executing: select * from car; 本身的分页并不是真正的分页;
person,pet双向一对多关联查询映射文件,person使用自定义拦截器实现分页(两种方式),pet使用三方分页插件实现分页
一对一双向用两个association来映射成员
多对多双向用两个collection来映射成员

mybatis核心接口的生命周期：
SqlSessionFactoryBuilder:创建出SqlSessionFactory对象后立即销毁
SqlSessionFactory:构建SqlSession的工厂,以单例形式一直存在
SqlSession:一次Sql会话，Sql执行完之后应该销毁
Mapper:同SqlSession

mybatis的缓存:
一级缓存:SqlSession范围,同一个SqlSession执行相同的查询操作会使用一级缓存,以Sql语句为Key将结果缓存起来。
二级缓存:Global Cache,所有的SqlSession共享的缓存
与Hibernate的缓存是一样的。