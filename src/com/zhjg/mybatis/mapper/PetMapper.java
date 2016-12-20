package com.zhjg.mybatis.mapper;

import java.util.List;

import com.zhjg.mybatis.pojo.Pet;



public interface PetMapper {

	Pet selectById(int id);

	List<Pet> selectByPage();


	
}
