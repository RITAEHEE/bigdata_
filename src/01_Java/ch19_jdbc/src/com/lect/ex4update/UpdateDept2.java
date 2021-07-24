package com.lect.ex4update;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class UpdateDept2 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("수정할 부서번호는 ? ");
		String deptno = scanner.next();
		// 입력한 해당 부서번호가 있는지 없는지 체크
		String selectSql = "SELECT * FROM DEPT WHERE DEPTNO="+deptno;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"scott","tiger");
			stmt = conn.createStatement();
			rs   = stmt.executeQuery(selectSql);
			if(rs.next()) { // 해당부서번호가 존재하는 경우
				System.out.print("바뀐 부서이름은 ? ");
				String dname = scanner.next();
				System.out.print("바뀐 부서 위치는 ?");
				String loc = scanner.next();
				String updateSql = "UPDATE DEPT SET DNAME='"+dname+"', LOC='"+loc+
							"' WHERE DEPTNO="+deptno;
				int result = stmt.executeUpdate(updateSql); 
				System.out.println(result>0? deptno+"번 부서 수정 성공":"수정 실패");
			}else {
				System.out.println("해당 부서번호가 존재하지 않아 수정 불가합니다");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try { // (7)
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}//try-catch
	}//main
}//class