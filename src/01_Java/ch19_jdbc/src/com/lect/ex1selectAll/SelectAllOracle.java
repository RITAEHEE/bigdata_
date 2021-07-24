package com.lect.ex1selectAll;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
public class SelectAllOracle {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null; // DB 연결 객체 변수
		Statement  stmt = null; // SQL문 전송 객체 변수
		ResultSet  rs   = null; // SELECT 문 결과 받는 객체 변수
		String sql = "SELECT * FROM EMP";
		try {
			Class.forName(driver); // (1) 드라이버로드
			conn = DriverManager.getConnection(url, "scott","tiger"); // (2) DB연결 객체 생성
			stmt = conn.createStatement(); // (3) SQL문 전송 객체 생성
			rs   = stmt.executeQuery(sql); // (4) SQL전송 (5)결과받기
			if(rs.next()) { // 데이터 있음 (6)결과를 받아 원하는 로직 수행 
				System.out.println("사번\t이름\t직책\t상사사번\t입사일\t급여\t상여\t부서번호");
				do {
					int    empno = rs.getInt(1);
					String ename = rs.getString("ename");
					String job   = rs.getString("job");
					int    mgr   = rs.getInt("mgr");
					Date   hiredate = rs.getDate("hiredate");
					Timestamp hiredates = rs.getTimestamp("hiredate");
					int    sal      = rs.getInt("sal");
					int    comm     = rs.getInt("comm");
					int    deptno   = rs.getInt("deptno");
					if(job!=null && job.length()<6) {
						System.out.printf("%d\t %s\t %s\t\t %d\t %TF\t %d\t %d\t %d\n",
							       empno, ename, job, mgr, hiredate, sal, comm, deptno);
					}else {
						System.out.printf("%d\t %s\t %s\t %d\t %TF\t %d\t %d\t %d\n",
							       empno, ename, job, mgr, hiredates, sal, comm, deptno);
					}
				}while(rs.next());
			}else {
				System.out.println("조회한 결과가 없습니다.");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage()+" 드라이버 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try { // (7) 연결 해제
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {  }
		}
		
		
		
		
	}
}







