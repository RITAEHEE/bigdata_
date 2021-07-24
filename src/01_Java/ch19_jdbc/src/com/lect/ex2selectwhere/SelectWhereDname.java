package com.lect.ex2selectwhere;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class SelectWhereDname {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		Scanner    scanner = new Scanner(System.in);
		System.out.print("���ϴ� �μ� �̸���? ");
		String dname = scanner.next().toUpperCase();
		String sql = "SELECT * FROM DEPT WHERE DNAME='"+dname+"'";
		sql = String.format("SELECT * FROM DEPT WHERE DNAME='%s'", dname);
		try {
			Class.forName(driver); // (1)
			conn = DriverManager.getConnection(url, "scott","tiger"); // (2)
			stmt = conn.createStatement(); //(3)
			rs   = stmt.executeQuery(sql); // (4)+(5)
			if(rs.next()) { // �ش� �μ���ȣ�� ���� (6)
				int    deptno= rs.getInt("deptno");   
				String loc   = rs.getString("loc");
				System.out.println(dname+" �μ� ������ ������ �����ϴ�.");
				System.out.println("�μ� ��ȣ : "+deptno);
				System.out.println("�μ� ��ġ : "+loc);
			}else { // �ش�μ���ȣ�� ����
				System.out.println("�ش� �μ��� �������� �ʽ��ϴ�");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try { // (7) ���� ����
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {  }
		}
	}
}









