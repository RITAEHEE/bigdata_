package com.lect.ex0conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnOracle {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null; // DB���� ����
		try {
			// 1. ����̹� �ε�
			Class.forName(driver);
			System.out.println("����̹� �ε� ����");
			// 2. DB�� ���� ��ü ����
			conn = DriverManager.getConnection(url,"scott","tiger");
			System.out.println("����Ŭ ���� ����");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage()+" : ����̹� ����");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+" SQL Exception ����");
		} finally {
			try {
				// 7. ���� ����
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"�ݱ� ����");
			}
		}
	}
}
