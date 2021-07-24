package com.lect.ex2customer;
public class CustomerDto {
	private int id;
	private String phone;
	private String name;
	private int point;
	// insert에도 select에도 하나의 생성자를 쓰려면 insert에는 id에 아무값이나
	public CustomerDto(int id, String phone, String name, int point) {
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.point = point;
	}
	@Override
	public String toString() {
		return name+"("+id+"번."+phone+")님 포인트는 "+point;
	}
	public int getId() {return id;}
	public String getPhone() {return phone;}
	public String getName() {return name;}
	public int getPoint() {return point;}
}
