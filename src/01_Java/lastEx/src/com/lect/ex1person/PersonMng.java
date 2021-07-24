package com.lect.ex1person;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class PersonMng {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Scanner scanner = new Scanner(System.in) ;
		Connection        conn = null;
		PreparedStatement pstmt = null;
		Statement         stmt  = null;
		ResultSet         rs    = null;
		String fn, sql;
		// 1�ܰ���� do~while��
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		do {
			System.out.print("1:�Է� || 2:������ ��� || 3:��ü��� || �׿�:����");
			fn = scanner.next();
			switch(fn) {
			case "1"://1. �̸�, ������, ������ �Է¹޾� insert
				System.out.print("�Է��� �̸��� ?");
				String name = scanner.next();
				System.out.print("��������(���|����|������ ����)");
				String jname = scanner.next();
				System.out.print("����������?");
				int kor = scanner.nextInt();
				System.out.print("����������?");
				int eng = scanner.nextInt();
				System.out.print("����������?");
				int mat = scanner.nextInt();
				sql = "INSERT INTO PERSON VALUES (PERSON_NO_SQ.NEXTVAL, ?," + 
						"        (SELECT JNO FROM JOB WHERE JNAME=?), ?, ?, ?)";
				try {
					conn = DriverManager.getConnection(url,"scott","tiger"); // (2)
					pstmt = conn.prepareStatement(sql); // (3)
					pstmt.setString(1, name);
					pstmt.setString(2, jname);
					pstmt.setInt(3, kor);
					pstmt.setInt(4, eng);
					pstmt.setInt(5, mat);
					int result = pstmt.executeUpdate();
					System.out.println(result==1? name+"�� �Է¼���": "�Է½���");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						if(pstmt!=null) pstmt.close();
						if(conn !=null) conn.close();
					} catch (SQLException e2) {
						// TODO: handle exception
					}
				}
				break;
			case "2"://2. ������ �Է¹޾� ���������� ���
				System.out.print("��ȸ�� �������� (���||����||����)");
				jname = scanner.next();
				sql = "SELECT ROWNUM RANK, S.* " + 
						"  FROM (SELECT NAME||'('||NO||'��)' NAME, JNAME, KOR, ENG, MAT, KOR+ENG+MAT SUM " + 
						"          FROM PERSON P, JOB J " + 
						"          WHERE P.JNO=J.JNO AND JNAME=? " + 
						"          ORDER BY SUM DESC) S";
				try {
					conn = DriverManager.getConnection(url,"scott","tiger");
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, jname);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						System.out.println("rank\t�̸�\t����\t����\t����\t����");
						do {
							int rank = rs.getInt("rank");
							name  = rs.getString("name");
							jname = rs.getString("jname");
							kor   = rs.getInt("kor");
							eng   = rs.getInt("eng");
							mat   = rs.getInt("mat");
							int sum = rs.getInt("sum");
							System.out.println(rank+"\t"+name+"\t"+jname+
									"\t"+kor+"\t"+eng+"\t"+mat+"\t"+sum);
						}while(rs.next());
					}else {
						System.out.println("�ش� �������� ����� �����ϴ�");
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					try {
						if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(conn !=null) conn.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				break;
			case "3"://3. ��ü���
				sql = "SELECT ROWNUM RANK, S.* " + 
						"  FROM (SELECT NAME||'('||NO||'��)' NAME, JNAME, KOR, ENG, MAT, KOR+ENG+MAT SUM " + 
						"          FROM PERSON P, JOB J " + 
						"          WHERE P.JNO = J.JNO " + 
						"          ORDER BY SUM DESC) S";
				try {
					conn = DriverManager.getConnection(url, "scott","tiger");//(2)
					stmt = conn.createStatement(); // (3)
					rs   = stmt.executeQuery(sql);
					if(rs.next()) {
						System.out.println("rank\t�̸�\t����\t����\t����\t����");
						do {
							int rank = rs.getInt("rank");
							name  = rs.getString("name");
							jname = rs.getString("jname");
							kor   = rs.getInt("kor");
							eng   = rs.getInt("eng");
							mat   = rs.getInt("mat");
							int sum = rs.getInt("sum");
							System.out.println(rank+"\t"+name+"\t"+jname+
									"\t"+kor+"\t"+eng+"\t"+mat+"\t"+sum);
						}while(rs.next());
					}else {
						System.out.println("�Էµ� ����� �����ϴ�");
					}					
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}finally {
					try {
						if(rs!=null) rs.close();
						if(pstmt!=null) pstmt.close();
						if(conn !=null) conn.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				break;
			}
		}while(fn.equals("1") || fn.equals("2") || fn.equals("3"));
		scanner.close();
		System.out.println("BYE");
	}
}











