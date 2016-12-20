package com.zhjg.mybatis.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zhjg.mybatis.mapper.RoleMenuMapper;
import com.zhjg.mybatis.mapper.SysMenuMapper;
import com.zhjg.mybatis.mapper.UserRoleMapper;
import com.zhjg.mybatis.pojo.RoleMenu;
import com.zhjg.mybatis.pojo.RoleMenuExample;
import com.zhjg.mybatis.pojo.SysMenu;
import com.zhjg.mybatis.pojo.SysMenuExample;
import com.zhjg.mybatis.pojo.SysUser;
import com.zhjg.mybatis.pojo.UserRole;
import com.zhjg.mybatis.util.MybatisUtil;

public class ShowMenu {

	public static void main(String[] args) {
		SysUser user = Main.queryById(3);
		List<SysMenu> menus = getMenusByUser(user);
		if(menus != null && menus.size() > 0){
			for (SysMenu sysMenu : menus) {
				System.out.println(sysMenu.getName());
			}
		}
			
	}
	
	public static List<SysMenu> getMenusByUser(SysUser user){
		//通过用户获取角色Id
		List<Integer> roleIds = getRoleIdsByUser(user);
		
		//通过角色Id获取菜单Id
		List<Integer> menuIds = getMenuIdsByRoleIds(roleIds);
		
		//通过菜单Id获取菜单
		SqlSession session = MybatisUtil.getSqlSession();
		SysMenuMapper mapper = session.getMapper(SysMenuMapper.class);
		SysMenuExample example = new SysMenuExample();
		com.zhjg.mybatis.pojo.SysMenuExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(menuIds);
		List<SysMenu> menus = mapper.selectByExample(example);
		
		return menus;
	}

	private static List<Integer> getMenuIdsByRoleIds(List<Integer> roleIds) {
		SqlSession session = MybatisUtil.getSqlSession();
		RoleMenuMapper mapper = session.getMapper(RoleMenuMapper.class);
		RoleMenuExample example = new RoleMenuExample();
		com.zhjg.mybatis.pojo.RoleMenuExample.Criteria criteria = example.createCriteria();
		criteria.andRoleidIn(roleIds);
		List<RoleMenu> rms = mapper.selectByExample(example);
		List<Integer> menuIds = null;
		if(rms != null && rms.size() > 0){
			menuIds = new ArrayList<Integer>();
			for(RoleMenu rm : rms){
				menuIds.add(rm.getMenuid());
			}
			return menuIds;
		}
		return null;
	}

	private static List<Integer> getRoleIdsByUser(SysUser user) {
		SqlSession session = MybatisUtil.getSqlSession();
		UserRoleMapper mapper = session.getMapper(UserRoleMapper.class);
		/*UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(user.getId());
		List<UserRole> urs = mapper.selectByExample(example);*/
		List<UserRole> urs = mapper.selectUserRoleByUserId(user.getId());
		List<Integer> roleIds = null;
		if(urs != null && urs.size() > 0){
			roleIds = new ArrayList<Integer>();
			for(UserRole ur : urs){
				roleIds.add(ur.getRoleid());
			}
			return roleIds;
		}
		return null;
	}

	
}
