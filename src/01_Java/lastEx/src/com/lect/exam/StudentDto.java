package com.lect.exam;
public class StudentDto {
	private int rank;     // ���
	private String sNo;   // �й�
	private String sName; // �л��̸�
	private String mName; // �а��̸�
	private int score;    // ����
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
			return rank+"��\t"+sName+"\t"+mName + "\t"+score; // �����˻�, �л����, ��������½�
		}else {
			return sNo+"\t"+sName+"\t"+mName + "\t"+score; // rank�� ���� �������� �̸��˻���
		}
	}
	public int getRank() {return rank;}
	public String getsNo() {return sNo;}
	public String getsName() {return sName;}
	public String getmName() {return mName;}
	public int getScore() {return score;}
}
