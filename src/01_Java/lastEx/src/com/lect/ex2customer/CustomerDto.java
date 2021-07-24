package com.lect.ex2customer;
public class CustomerDto {
	private int id;
	private String phone;
	private String name;
	private int point;
	// insert���� select���� �ϳ��� �����ڸ� ������ insert���� id�� �ƹ����̳�
	public CustomerDto(int id, String phone, String name, int point) {
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.point = point;
	}
	@Override
	public String toString() {
		return name+"("+id+"��."+phone+")�� ����Ʈ�� "+point;
	}
	public int getId() {return id;}
	public String getPhone() {return phone;}
	public String getName() {return name;}
	public int getPoint() {return point;}
}