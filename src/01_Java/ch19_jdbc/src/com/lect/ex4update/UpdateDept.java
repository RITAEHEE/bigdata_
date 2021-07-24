package com.lect.ex4update;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
public class UpdateDept {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement  stmt = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("수정할 부서번호는 ? ");
		String deptno = scanner.next();
		System.out.print("바뀐 부서이름은 ? ");
		String dname = scanner.next();
		System.out.print("바뀐 부서 위치는 ?");
		String loc = scanner.next();
		String sql = "UPDATE DEPT SET DNAME='"+dname+"', LOC='"+loc+"' WHERE DEPTNO="+deptno;
		try {
			Class.forName(driver);//(1)
			conn = DriverManager.getConnection(url,"scott","tiger");// (2)
			stmt = conn.createStatement(); // (3)
			int result = stmt.executeUpdate(sql); // (4) + (5)
			System.out.println(result>0? deptno+"번 부서 수정 성공":"수정 실패"); // (6)
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try { // (7)
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}













