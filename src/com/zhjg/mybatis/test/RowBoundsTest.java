package com.zhjg.mybatis.test;

import org.apache.ibatis.session.RowBounds;

public class RowBoundsTest {

	public static void main(String[] args) {
		
		//��ҳ��
		RowBounds rowBounds = new RowBounds();
		//32λ���������,intΪ4�ֽ�(32λ),Ĭ�������������
		//System.out.println(rowBounds.getLimit());
		//Ĭ��Ϊ0(��ʾ��һҳ)
		//System.out.println(rowBounds.getOffset());
		
		int totalcount = 7;
		int pageSize = 3;
		int totalPage = totalcount/pageSize + ((totalcount%pageSize>0)?1:0);
		
		
		System.out.println(totalPage);
	}
}
