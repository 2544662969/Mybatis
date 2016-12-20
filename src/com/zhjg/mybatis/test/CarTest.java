package com.zhjg.mybatis.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.zhjg.mybatis.condition.CarCondition;
import com.zhjg.mybatis.mapper.CarMapper;
import com.zhjg.mybatis.pojo.Car;
import com.zhjg.mybatis.util.MybatisUtil;
import com.zhjg.mybatis.util.PageInfo_;

public class CarTest {

	private static SqlSession session;
	private static CarMapper mapper;

	static {
		session = MybatisUtil.getSqlSession();
		mapper = session.getMapper(CarMapper.class);
	}

	public static void main(String[] args) {

		List<Car> cars = null;

		Car car = null;

		// System.out.println(selectById(1).getBrand());

		// List<Integer> ids = new ArrayList<Integer>();
		// ids.add(1);
		// ids.add(2);
		// cars = selectByIds(ids);

		// CarCondition condition = new CarCondition();
		// condition.setBrand("b");
		// cars = selectByCondition(condition);

		// car = new Car("daa", "dad", 78912.563);
		// insert(car);

		// car = selectById(1);
		// car.setPrice(200000);
		// modify(car);

		// delete(3);

		// deleteCars(new int[]{3,5,6,7});

		// Car car1 = new Car("asa", "adf", 145);
		// Car car2 = new Car("add", "dfa", 4454);
		// cars = new ArrayList<Car>();
		// cars.add(car1);
		// cars.add(car2);
		// insertCars(cars);
		
		PageInfo_<Car> pageInfo = selectByPage(null, 1, 3);
		dealCars(cars);
		dealPageInfo(pageInfo);
	}

	

	public static Car selectById(int id) {
		return mapper.selectById(id);
	}

	public static List<Car> selectByCondition(CarCondition condition) {
		return mapper.selectByCondition(condition);
	}

	public static List<Car> selectByIds(List<Integer> ids) {
		return mapper.selectByIds(ids);
	}

	public static void insert(Car car) {
		mapper.insert(car);
	}

	// ģ�浼��
	public static void insertCars(List<Car> cars) {
		mapper.insertCars(cars);
	}

	// �޸�
	public static void modify(Car car) {
		mapper.modify(car);
	}

	// ɾ��
	public static void delete(int id) {
		mapper.delete(id);
	}

	// ����ɾ��
	public static void deleteCars(int[] ids) {
		mapper.deleteCars(ids);
	}
	
	//��ҳ��ѯ:ʹ��SqlSession��RowBounds���з�ҳ��ѯ
	@SuppressWarnings("unchecked")
	public static PageInfo_<Car> selectByPage(Object parameter, int curPage, int pageSize){
		
		//��ҳ��Ϣ
		PageInfo_<Car> pageInfo = new PageInfo_<Car>(pageSize, curPage);
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getPageSize());
		
		//statement����sql��id����
		//parameter�������������sql��ͨ��#{}��ȡ
		int total = (int) session.selectOne("getCounts", parameter);
		//ʹ��mapper�������ݿ����ʱ����ͨ��namespace����ӳ���ļ�
		//��ʹ��sqlSession�������ݿ����ʱ��Ϊ�������id��ͬ��sql������ͻ������ʹ��namespace+id��ִ��
		List<Car> cars = session.selectList("com.zhjg.mybatis.mapper.CarMapper.selectByPage", parameter, rowBounds);
		pageInfo.setTotalCount(total);
		pageInfo.setRecords(cars);
		return pageInfo;
		
	}
	
	private static void dealCars(List<Car> cars) {
		if (cars != null && cars.size() > 0) {
			for (Car car : cars) {
				System.out.println(car.getId() + "," + car.getBrand() + ","
						+ car.getType() + "," + car.getPrice());
			}
		} else {
			System.out.println("û�з�������������");
		}
	}
	
	private static void dealPageInfo(PageInfo_<Car> pageInfo) {
		System.out.println("ÿҳ��¼����"+pageInfo.getPageSize());
		System.out.println("��ǰ�ǵ�"+pageInfo.getCurPage()+"ҳ");
		System.out.println("�����ݿ�ĵ�"+pageInfo.getOffset()+"����ʼ");
		System.out.println("�ܼ�¼��Ϊ"+pageInfo.getTotalCount());
		System.out.println("��ҳ��Ϊ"+pageInfo.getTotalPages());
		System.out.println("�������£�");
		dealCars(pageInfo.getRecords());
	}
}
