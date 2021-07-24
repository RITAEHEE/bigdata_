package com.lect.ex2_swing;
public class Friend {
	private String name;
	private String tel;
	private int age;
	public Friend() {}
	public Friend(String name, String tel, int age) {
		this.name = name;
		this.tel = tel;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return name + "\t" + tel + "\t\t" + age;
	}
}
