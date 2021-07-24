package com.lect.ex6preparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
// 부서번호를 받아 emp테이블에 존재하는 부서번호인지 학인 후 dept에 삭제
public class DeleteDept2 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Scanner sc = new Scanner(System.in);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String selectSql = "SELECT * FROM EMP WHERE DEPTNO=?";
		String deleteSql = "DELETE FROM DEPT WHERE DEPTNO=?";
		System.out.print("삭제할 부서번호는 ?");
		int deptno = sc.nextInt();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott","tiger");
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			if(rs.next()) { // 해당부서번호 사원이 존재
				System.out.println("해당부서번호의 사원이 존재하여 부서를 삭제할 수 없습니다");
			}else {// 해당부서번호 사원 존재안함
				rs.close();
				pstmt.close();
				pstmt = conn.prepareStatement(deleteSql);
				pstmt.setInt(1, deptno);
				int result = pstmt.executeUpdate();
				System.out.println(result==1? "삭제 성공":"해당 부서번호가 존재하지 않습니다");
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}










