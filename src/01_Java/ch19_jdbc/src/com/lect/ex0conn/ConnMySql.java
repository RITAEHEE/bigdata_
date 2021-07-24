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
			// 1. 드라이버 로드
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
			// 2. DB 연결객체 생성
			conn = DriverManager.getConnection(url,"root","mysql");
			System.out.println("mysql 연결성공");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage()+"드라이버 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"SQL 오류");
		}finally {
			try {//7.연결해제
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
