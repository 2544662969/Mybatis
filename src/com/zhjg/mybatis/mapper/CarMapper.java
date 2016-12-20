package com.zhjg.mybatis.mapper;

import java.util.List;

import com.zhjg.mybatis.condition.CarCondition;
import com.zhjg.mybatis.pojo.Car;

public interface CarMapper {

	Car selectById(int id);

	List<Car> selectByCondition(CarCondition condition);

	List<Car> selectByIds(List<Integer> ids);

	void insert(Car car);

	void modify(Car car);

	void delete(int id);

	void deleteCars(int[] ids);

	void insertCars(List<Car> cars);

	List<Car> selectByPage(int offset, int limit);
}
