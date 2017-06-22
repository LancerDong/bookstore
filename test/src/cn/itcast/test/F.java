package cn.itcast.test;

import java.util.Arrays;
import java.util.List;


public class F {
	public static void main(String[] args) {
		String[] str1 = {"1","2","3","4","5"};
		List list = Arrays.asList(str1);
		System.out.println(list);
		
		int[] str2 = {11,22,33,44,55};
		List<int[]> list1 = Arrays.asList(str2);
		System.out.println(list1);
	}
}
