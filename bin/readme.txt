Ȩ�������mybatis���򹤳��Զ�����
car��д�������ݿ������ӳ���ļ�,ʹ��SqlSession��RowBounds���з�ҳ��ѯ(ִ��carTest��ҳʱӦ��ע������ҳ������������ΪPageInfo��ɿ�ָ���쳣)
DEBUG [java.sql.PreparedStatement] - ==>  Executing: select * from car; ����ķ�ҳ�����������ķ�ҳ;
person,pet˫��һ�Զ������ѯӳ���ļ�,personʹ���Զ���������ʵ�ַ�ҳ(���ַ�ʽ),petʹ��������ҳ���ʵ�ַ�ҳ
һ��һ˫��������association��ӳ���Ա
��Զ�˫��������collection��ӳ���Ա

mybatis���Ľӿڵ��������ڣ�
SqlSessionFactoryBuilder:������SqlSessionFactory�������������
SqlSessionFactory:����SqlSession�Ĺ���,�Ե�����ʽһֱ����
SqlSession:һ��Sql�Ự��Sqlִ����֮��Ӧ������
Mapper:ͬSqlSession

mybatis�Ļ���:
һ������:SqlSession��Χ,ͬһ��SqlSessionִ����ͬ�Ĳ�ѯ������ʹ��һ������,��Sql���ΪKey���������������
��������:Global Cache,���е�SqlSession����Ļ���
��Hibernate�Ļ�����һ���ġ�