package com.zhjg.mybatis.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zhjg.mybatis.mapper.SysUserMapper;
import com.zhjg.mybatis.pojo.SysUser;
import com.zhjg.mybatis.pojo.SysUserExample;
import com.zhjg.mybatis.pojo.SysUserExample.Criteria;
import com.zhjg.mybatis.util.MybatisUtil;

public class Main {

	/**
	 * mybatis基本CRUD
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// delete();
		// add();
		// modify();
		  /*List<SysUser> users = query(); 
		  if(users != null && users.size() > 0)
			  for (SysUser sysUser : users) {
				  System.out.println(sysUser.getId()+":"+sysUser.getName()+":"+sysUser.getAge()+":"+sysUser.getAddress()); 
			  }*/
		/*SysUser sysUser = queryById(1);
		System.out.println(sysUser.getId() + ":" + sysUser.getName() + ":" + sysUser.getAge() + ":" + sysUser.getAddress());*/
		List<SysUser> users = queryByConditions("tom", 20, "上海");
		if(users != null && users.size() > 0)
			  for (SysUser sysUser : users) {
				  System.out.println(sysUser.getId()+":"+sysUser.getName()+":"+sysUser.getAge()+":"+sysUser.getAddress()); 
			  }
		
	}

	public static void delete() {
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// 从session中获取mapper代理
		mapper = session.getMapper(SysUserMapper.class);
		// 执行操作
		mapper.deleteByPrimaryKey(1);
		// 关闭session
		MybatisUtil.closeSqlSession(session);
	}

	public static void add() {
		// 模拟前台参数
		SysUser user = new SysUser();
		user.setId(2);
		user.setName("jack");
		user.setAge(20);
		user.setAddress("北京");
		// 设置自动提交事务
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// 从session中获取mapper代理
		mapper = session.getMapper(SysUserMapper.class);
		// 执行操作
		mapper.insert(user);
		// 关闭session
		MybatisUtil.closeSqlSession(session);
	}

	public static void modify() {
		// 模拟前台参数
		SysUser user = new SysUser();
		user.setId(1);
		user.setName("tom");
		user.setAge(20);
		user.setAddress("上海");
		// 设置自动提交事务
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// 从session中获取mapper代理
		mapper = session.getMapper(SysUserMapper.class);
		// 执行操作
		mapper.updateByPrimaryKey(user);
		// 关闭session
		MybatisUtil.closeSqlSession(session);
	}

	public static List<SysUser> query() {
		// 设置自动提交事务
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// 从session中获取mapper代理
		mapper = session.getMapper(SysUserMapper.class);
		// 查询所有记录
		SysUserExample example = new SysUserExample();
		List<SysUser> users = mapper.selectByExample(example);
		// 关闭session
		MybatisUtil.closeSqlSession(session);
		return users;
	}

	public static SysUser queryById(int id) {
		// 设置自动提交事务
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// 从session中获取mapper代理
		mapper = session.getMapper(SysUserMapper.class);
		// 查询所有记录
		SysUser user = mapper.selectByPrimaryKey(id);
		// 关闭session
		MybatisUtil.closeSqlSession(session);
		return user;
	}

	public static List<SysUser> queryByConditions(String name, int age,
			String address) {

		// 设置自动提交事务
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// 从session中获取mapper代理
		mapper = session.getMapper(SysUserMapper.class);
		// 查询所有记录
		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andAgeEqualTo(age);
		criteria.andAddressEqualTo(address);
		List<SysUser> users = mapper.selectByExample(example);
		// 关闭session
		MybatisUtil.closeSqlSession(session);
		return users;
	}
}
