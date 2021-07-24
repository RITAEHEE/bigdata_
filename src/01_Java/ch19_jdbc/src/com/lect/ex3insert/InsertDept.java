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
		System.out.print("�Է��� �μ���ȣ�� ? ");
		int deptno = scanner.nextInt();
		System.out.print("�Է��� �μ����� ? ");
		String dname = scanner.next();
		System.out.print("�Է��� �μ� ��ġ�� ? ");
		String loc = scanner.next();
		scanner.close();
		String sql = "INSERT INTO DEPT VALUES ("+deptno+",'"+dname+"','"+loc+"')";
		sql = String.format("INSERT INTO DEPT VALUES (%d,'%s','%s')", deptno, dname, loc);
		try {
			Class.forName(driver);//(1)
			conn = DriverManager.getConnection(url,"scott","tiger");//(3)
			stmt = conn.createStatement(); // (3)
			int result = stmt.executeUpdate(sql); // (4)+(5)
			System.out.println(result==1 ? "�μ��߰� ����":"�μ��߰� ����"); // (6)
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage()+"����̹� ����");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"SQL ����");
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












