package com.lect.ex1selectAll;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SelectAllMySql {
	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver"; // mysql 8.0
		String url    = "jdbc:mysql://localhost:3306/kimdb?serverTimezone=UTC";
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		String sql = "SELECT * FROM PERSONAL"; // ctrl+shift+x 블럭을 대문자로
		try {
			Class.forName(driver);// (1)
			conn = DriverManager.getConnection(url,"root","mysql"); // (2)
			stmt = conn.createStatement(); // (3)
			rs   = stmt.executeQuery(sql); // (4)executeQuery()함수로 SELECT문 전송 + (5)
			System.out.println("사번\t이름\t직책\t상사사번\t입사일\t급여\t상여\t부서번호");
			while(rs.next()) { // (6)
				int    pno   = rs.getInt("pno");
				String pname = rs.getString("pname");
				String job   = rs.getString("job");
				int   manager= rs.getInt("manager");
				Date  startdate = rs.getDate("startdate");
				int   pay    = rs.getInt("pay");
				int   bonus  = rs.getInt("bonus");
				int   dno    = rs.getInt("dno");
				System.out.printf("%d\t %s\t %s\t %d\t %TF\t %d\t %d\t %d\n",
						pno, pname, job, manager, startdate, pay, bonus, dno);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally { // (7)
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) rs.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}//close()
		}// try-catch-finally
	}//main
}//class












