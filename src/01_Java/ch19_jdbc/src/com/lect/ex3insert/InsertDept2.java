package com.lect.ex3insert;
// 1. 부서번호를 받아 해당부서번호가 있는지 조회 
// 2-1. 해당부서번호가 없을 경우 : 부서명과 위치를 받아 insert합니다
// 2-2. 해당부서번호가 있을 경우 : 중복된 부서번호입니다.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
// insert문 전송시    : stmt.executeUpdate()이용
// insert문 전송결과 : int 타입
// select문 전송시    : stmt.executeQuery() 이용
// select문 전송결과 : ResultSet 타입
public class InsertDept2 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("입력할 부서번호는 ? ");
		int deptno = scanner.nextInt();
		String selectSql = "SELECT * FROM DEPT WHERE DEPTNO="+deptno;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott","tiger");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectSql);
			if(rs.next()) {
				System.out.println("중복된 부서번호입니다");
			}else {
				System.out.print("입력할 부서명은 ? ");
				String dname = scanner.next();
//				byte[] d = dname.getBytes();
//				int firstLetterAscii = (int)d[0];
//				if( (firstLetterAscii>=65 && firstLetterAscii<=90) ||
//						(firstLetterAscii>=97 && firstLetterAscii<=122) ) {
//					if(dname.length()>14) {
//						System.out.println("넘 길어");
//						return;
//					}
//				}else {
//					if(dname.length()>4) {
//						System.out.println("넘 길어");
//						return;
//					}
//				}
				System.out.print("입력할 부서 위치는 ? ");
				String loc = scanner.next();
				String insertSql = "INSERT INTO DEPT VALUES ("+
										deptno+",'"+dname+"','"+loc+"')";
				int result = stmt.executeUpdate(insertSql);
				if(result>0) {
					System.out.println("입력 성공");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally { //(7)
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}//연결해제 try-catch
		}//try-catch
	}//main
}//class
