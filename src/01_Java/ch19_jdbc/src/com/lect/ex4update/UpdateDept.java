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
		System.out.print("������ �μ���ȣ�� ? ");
		String deptno = scanner.next();
		System.out.print("�ٲ� �μ��̸��� ? ");
		String dname = scanner.next();
		System.out.print("�ٲ� �μ� ��ġ�� ?");
		String loc = scanner.next();
		String sql = "UPDATE DEPT SET DNAME='"+dname+"', LOC='"+loc+"' WHERE DEPTNO="+deptno;
		try {
			Class.forName(driver);//(1)
			conn = DriverManager.getConnection(url,"scott","tiger");// (2)
			stmt = conn.createStatement(); // (3)
			int result = stmt.executeUpdate(sql); // (4) + (5)
			System.out.println(result>0? deptno+"�� �μ� ���� ����":"���� ����"); // (6)
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













