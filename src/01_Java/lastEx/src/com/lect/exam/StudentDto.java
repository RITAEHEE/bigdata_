package com.lect.exam;
public class StudentDto {
	private int rank;     // 등수
	private String sNo;   // 학번
	private String sName; // 학생이름
	private String mName; // 학과이름
	private int score;    // 점수
	// 
	public StudentDto(String sNo, String sName, String mName, int score) {
		this.sNo = sNo;
		this.sName = sName;
		this.mName = mName;
		this.score = score;
	}
	public StudentDto(int rank, String sName, String mName, int score) {
		this.rank = rank;
		this.sName = sName;
		this.mName = mName;
		this.score = score;
	}
	@Override
	public String toString() {
		if(rank!=0) {
			return rank+"등\t"+sName+"\t"+mName + "\t"+score; // 전공검색, 학생출력, 제적자출력시
		}else {
			return sNo+"\t"+sName+"\t"+mName + "\t"+score; // rank가 없는 동명이인 이름검색시
		}
	}
	public int getRank() {return rank;}
	public String getsNo() {return sNo;}
	public String getsName() {return sName;}
	public String getmName() {return mName;}
	public int getScore() {return score;}
}
