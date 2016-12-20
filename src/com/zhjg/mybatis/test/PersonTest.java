package com.zhjg.mybatis.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zhjg.mybatis.mapper.PersonMapper;
import com.zhjg.mybatis.pojo.Person;
import com.zhjg.mybatis.util.MybatisUtil;
import com.zhjg.mybatis.util.PageInfo_;

public class PersonTest {
	
	private static SqlSession session;
	private static PersonMapper mapper;

	static {
		session = MybatisUtil.getSqlSession();
		mapper = session.getMapper(PersonMapper.class);
	}

	public static void main(String[] args) {
		List<Person> persons = null;
		List<Person> persons1 = null;//���Ի���
		
		/*Person person = null;
		
		person = selectById(1);
		person.toString();
		
		persons = selectAll();*/
		toString(persons);
		
		
		//ִ��������ͬ�Ĳ�ѯ������mybatisִֻ��һ�����ݿ��ѯ������˵��������Ч
		int pageSize = 1;
		int curPage = 1;
		PageInfo_<Person> pageInfo = new PageInfo_<Person>(pageSize, curPage);
		persons = selectByPage(pageInfo);
		pageInfo.setRecords(persons);
		dealPageInfo(pageInfo);
		persons1 = selectByPage(pageInfo);
		pageInfo.setRecords(persons1);
		dealPageInfo(pageInfo);
		
	}
	
	public static Person selectById(int id){
		return mapper.selectById(id);
	}
	
	public static List<Person> selectAll(){
		return mapper.selectAll();
	}
	
	public static List<Person> selectByPage(PageInfo_<Person> pageInfo){
		return mapper.selectByPage(pageInfo);
	}
	
	private static void toString(List<Person> persons){
		if(persons != null && persons.size() > 0){
			for (Person person : persons) {
				person.toString();
			}
		}
	}
	
	private static void dealPageInfo(PageInfo_<Person> pageInfo) {
		System.out.println("ÿҳ��¼����"+pageInfo.getPageSize());
		System.out.println("��ǰ�ǵ�"+pageInfo.getCurPage()+"ҳ");
		System.out.println("�����ݿ�ĵ�"+pageInfo.getOffset()+"����ʼ");
		System.out.println("�ܼ�¼��Ϊ"+pageInfo.getTotalCount());
		System.out.println("��ҳ��Ϊ"+pageInfo.getTotalPages());
		System.out.println("�������£�");
		toString(pageInfo.getRecords());
	}
}
