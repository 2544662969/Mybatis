package com.zhjg.mybatis.test;

import org.apache.ibatis.session.RowBounds;

public class RowBoundsTest {

	public static void main(String[] args) {
		
		//分页类
		RowBounds rowBounds = new RowBounds();
		//32位最大正整数,int为4字节(32位),默认是最大正整数
		//System.out.println(rowBounds.getLimit());
		//默认为0(显示第一页)
		//System.out.println(rowBounds.getOffset());
		
		int totalcount = 7;
		int pageSize = 3;
		int totalPage = totalcount/pageSize + ((totalcount%pageSize>0)?1:0);
		
		
		System.out.println(totalPage);
	}
}
