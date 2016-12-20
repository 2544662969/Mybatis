package com.zhjg.mybatis.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhjg.mybatis.mapper.PetMapper;
import com.zhjg.mybatis.pojo.Pet;
import com.zhjg.mybatis.util.MybatisUtil;

public class PetTest {
	
	private static SqlSession session;
	private static PetMapper mapper;

	static {
		session = MybatisUtil.getSqlSession();
		mapper = session.getMapper(PetMapper.class);
	}

	public static void main(String[] args) {
		
		List<Pet> pets = null;
		//Pet pet = null;
		
		//pet = selectById(1);
		//pet.toString();
		
		dealPets(pets);
		
		PageInfo<?> pageInfo = selectByPage();
		toString(pageInfo);
		
	}
	
	private static PageInfo<?> selectByPage() {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 2);
		List<Pet> pets = mapper.selectByPage();
		PageInfo<?> pageInfo = new PageInfo<>(pets);
		return pageInfo;
	}

	public static Pet selectById(int id){
		return mapper.selectById(id);
	}
	
	private static void dealPets(List<Pet> pets){
		if(pets != null && pets.size() > 0){
			for (Pet pet : pets) {
				pet.toString();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void toString(PageInfo<?> pageInfo){
		System.out.println("×Ü¼ÇÂ¼Êý£º>>>>"+pageInfo.getTotal());
		List<Pet> pets = (List<Pet>)pageInfo.getList();
		dealPets(pets);
	}
}
