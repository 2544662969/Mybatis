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
	 * mybatis����CRUD
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
		List<SysUser> users = queryByConditions("tom", 20, "�Ϻ�");
		if(users != null && users.size() > 0)
			  for (SysUser sysUser : users) {
				  System.out.println(sysUser.getId()+":"+sysUser.getName()+":"+sysUser.getAge()+":"+sysUser.getAddress()); 
			  }
		
	}

	public static void delete() {
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// ��session�л�ȡmapper����
		mapper = session.getMapper(SysUserMapper.class);
		// ִ�в���
		mapper.deleteByPrimaryKey(1);
		// �ر�session
		MybatisUtil.closeSqlSession(session);
	}

	public static void add() {
		// ģ��ǰ̨����
		SysUser user = new SysUser();
		user.setId(2);
		user.setName("jack");
		user.setAge(20);
		user.setAddress("����");
		// �����Զ��ύ����
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// ��session�л�ȡmapper����
		mapper = session.getMapper(SysUserMapper.class);
		// ִ�в���
		mapper.insert(user);
		// �ر�session
		MybatisUtil.closeSqlSession(session);
	}

	public static void modify() {
		// ģ��ǰ̨����
		SysUser user = new SysUser();
		user.setId(1);
		user.setName("tom");
		user.setAge(20);
		user.setAddress("�Ϻ�");
		// �����Զ��ύ����
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// ��session�л�ȡmapper����
		mapper = session.getMapper(SysUserMapper.class);
		// ִ�в���
		mapper.updateByPrimaryKey(user);
		// �ر�session
		MybatisUtil.closeSqlSession(session);
	}

	public static List<SysUser> query() {
		// �����Զ��ύ����
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// ��session�л�ȡmapper����
		mapper = session.getMapper(SysUserMapper.class);
		// ��ѯ���м�¼
		SysUserExample example = new SysUserExample();
		List<SysUser> users = mapper.selectByExample(example);
		// �ر�session
		MybatisUtil.closeSqlSession(session);
		return users;
	}

	public static SysUser queryById(int id) {
		// �����Զ��ύ����
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// ��session�л�ȡmapper����
		mapper = session.getMapper(SysUserMapper.class);
		// ��ѯ���м�¼
		SysUser user = mapper.selectByPrimaryKey(id);
		// �ر�session
		MybatisUtil.closeSqlSession(session);
		return user;
	}

	public static List<SysUser> queryByConditions(String name, int age,
			String address) {

		// �����Զ��ύ����
		SqlSession session = MybatisUtil.getSqlSession();
		SysUserMapper mapper = null;
		// ��session�л�ȡmapper����
		mapper = session.getMapper(SysUserMapper.class);
		// ��ѯ���м�¼
		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andAgeEqualTo(age);
		criteria.andAddressEqualTo(address);
		List<SysUser> users = mapper.selectByExample(example);
		// �ر�session
		MybatisUtil.closeSqlSession(session);
		return users;
	}
}
