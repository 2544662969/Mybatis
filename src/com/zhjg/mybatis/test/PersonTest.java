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
		List<Person> persons1 = null;//测试缓存
		
		/*Person person = null;
		
		person = selectById(1);
		person.toString();
		
		persons = selectAll();*/
		toString(persons);
		
		
		//执行两次相同的查询操作，mybatis只执行一遍数据库查询操作，说明缓存生效
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
		System.out.println("每页记录数："+pageInfo.getPageSize());
		System.out.println("当前是第"+pageInfo.getCurPage()+"页");
		System.out.println("从数据库的第"+pageInfo.getOffset()+"条开始");
		System.out.println("总记录数为"+pageInfo.getTotalCount());
		System.out.println("总页数为"+pageInfo.getTotalPages());
		System.out.println("数据如下：");
		toString(pageInfo.getRecords());
	}
}
