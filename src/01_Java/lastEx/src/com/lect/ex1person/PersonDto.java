package com.lect.ex1person;
//rank, name, jname, kor, eng, mat, sum
public class PersonDto {
	private int rank;
	private String name;
	private String jname;
	private int kor;
	private int eng;
	private int mat;
	private int sum;
	// insert ¿ë
	public PersonDto(String name, String jname, int kor, int eng, int mat) {
		this.name = name;
		this.jname = jname;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	// select ¿ë
	public PersonDto(int rank, String name, String jname, int kor, int eng, int mat, int sum) {
		this.rank = rank;
		this.name = name;
		this.jname = jname;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sum = sum;
	}
	public int getRank() {return rank;}
	public String getName() {return name;}
	public String getJname() {return jname;}
	public int getKor() {return kor;}
	public int getEng() {return eng;}
	public int getMat() {return mat;}
	public int getSum() {return sum;}
	@Override
	public String toString() {
		return rank + "µî\t" + name + "\t" + jname + "\t" + kor + "\t" + eng
				+ "\t" + mat + "\t" + sum;
	}	
}










