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
		// 1단계부터 do~while문
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		do {
			System.out.print("1:입력 || 2:직업별 출력 || 3:전체출력 || 그외:종료");
			fn = scanner.next();
			switch(fn) {
			case "1"://1. 이름, 직업명, 국영수 입력받아 insert
				System.out.print("입력할 이름은 ?");
				String name = scanner.next();
				System.out.print("직업명은(배우|가수|엠씨만 가능)");
				String jname = scanner.next();
				System.out.print("국어점수는?");
				int kor = scanner.nextInt();
				System.out.print("영어점수는?");
				int eng = scanner.nextInt();
				System.out.print("수학점수는?");
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
					System.out.println(result==1? name+"님 입력성공": "입력실패");
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
			case "2"://2. 직업명 입력받아 총점순으로 출력
				System.out.print("조회할 직업명은 (배우||가수||엠씨)");
				jname = scanner.next();
				sql = "SELECT ROWNUM RANK, S.* " + 
						"  FROM (SELECT NAME||'('||NO||'번)' NAME, JNAME, KOR, ENG, MAT, KOR+ENG+MAT SUM " + 
						"          FROM PERSON P, JOB J " + 
						"          WHERE P.JNO=J.JNO AND JNAME=? " + 
						"          ORDER BY SUM DESC) S";
				try {
					conn = DriverManager.getConnection(url,"scott","tiger");
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, jname);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						System.out.println("rank\t이름\t직업\t국어\t영어\t수학");
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
						System.out.println("해당 직업군의 사람이 없습니다");
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
			case "3"://3. 전체출력
				sql = "SELECT ROWNUM RANK, S.* " + 
						"  FROM (SELECT NAME||'('||NO||'번)' NAME, JNAME, KOR, ENG, MAT, KOR+ENG+MAT SUM " + 
						"          FROM PERSON P, JOB J " + 
						"          WHERE P.JNO = J.JNO " + 
						"          ORDER BY SUM DESC) S";
				try {
					conn = DriverManager.getConnection(url, "scott","tiger");//(2)
					stmt = conn.createStatement(); // (3)
					rs   = stmt.executeQuery(sql);
					if(rs.next()) {
						System.out.println("rank\t이름\t직업\t국어\t영어\t수학");
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
						System.out.println("입력된 사람이 없습니다");
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











