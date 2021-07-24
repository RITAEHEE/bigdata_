package com.lec.rect;

public class RectMain {
	public static void main(String[] args) {
		Rect r1 = new Rect();
		Rect r2 = new Rect();
		// 가로 세로 길이 setting
		System.out.printf("r1의 데이터 : %d %d\n", r1.getWidth(), r1.getHeight());
		System.out.printf("r2의 데이터 : %d %d\n", r2.getWidth(), r2.getHeight());

		r1.setWidth(5); r1.setHeight(7);
		System.out.println("변경후");
		System.out.printf("r1의 데이터 : %d %d\n", r1.getWidth(), r1.getHeight());
		System.out.printf("r2의 데이터 : %d %d\n", r2.getWidth(), r2.getHeight());
		
		System.out.println(r1.area());
		System.out.println(r2.area());
	}
}
