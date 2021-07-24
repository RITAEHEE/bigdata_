package com.lect.ex3insert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class InsertDept {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement  stmt = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("입력할 부서번호는 ? ");
		int deptno = scanner.nextInt();
		System.out.print("입력할 부서명은 ? ");
		String dname = scanner.next();
		System.out.print("입력할 부서 위치는 ? ");
		String loc = scanner.next();
		scanner.close();
		String sql = "INSERT INTO DEPT VALUES ("+deptno+",'"+dname+"','"+loc+"')";
		sql = String.format("INSERT INTO DEPT VALUES (%d,'%s','%s')", deptno, dname, loc);
		try {
			Class.forName(driver);//(1)
			conn = DriverManager.getConnection(url,"scott","tiger");//(3)
			stmt = conn.createStatement(); // (3)
			int result = stmt.executeUpdate(sql); // (4)+(5)
			System.out.println(result==1 ? "부서추가 성공":"부서추가 실패"); // (6)
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage()+"드라이버 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"SQL 오류");
		}finally { // (7)
			try {
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}












