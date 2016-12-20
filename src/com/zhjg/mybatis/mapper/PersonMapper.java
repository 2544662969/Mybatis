package com.zhjg.mybatis.mapper;

import java.util.List;

import com.zhjg.mybatis.pojo.Person;
import com.zhjg.mybatis.util.PageInfo_;


public interface PersonMapper {

	Person selectById(int id);

	List<Person> selectAll();

	List<Person> selectByPage(PageInfo_<Person> pageInfo);

	
}
