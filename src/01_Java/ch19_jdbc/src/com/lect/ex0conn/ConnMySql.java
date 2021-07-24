package com.lect.ex0conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnMySql {
	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver"; // mysql 8.0
		String url    = "jdbc:mysql://localhost:3306/kimdb?serverTimezone=UTC";
		Connection conn = null;
		try {
			// 1. ����̹� �ε�
			Class.forName(driver);
			System.out.println("����̹� �ε� ����");
			// 2. DB ���ᰴü ����
			conn = DriverManager.getConnection(url,"root","mysql");
			System.out.println("mysql ���Ἲ��");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage()+"����̹� ����");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"SQL ����");
		}finally {
			try {//7.��������
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
