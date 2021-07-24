package com.lect.ex6preparedStatement;
import java.sql.*;
import java.util.Scanner;
// 부서명을 입력받아 해당부서명이 있으면 부서정보 출력 해당 부서명의 사번, 이름, 급여, 상사이름 정보
public class SelectWhereDname {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		Scanner    scanner = new Scanner(System.in);
		String deptQuery = "SELECT * FROM DEPT WHERE DNAME=?";
		String empQuery = "SELECT W.EMPNO, W.ENAME, W.SAL, M.ENAME MANAGER " + 
				"  FROM EMP W, EMP M, DEPT D " + 
				"  WHERE DNAME=? AND W.MGR=M.EMPNO AND D.DEPTNO=W.DEPTNO"; 
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"scott","tiger");
			pstmt = conn.prepareStatement(deptQuery);
			System.out.print("검색하고자 하는 부서명은 ?");
			String dname = scanner.next();
			pstmt.setString(1, dname);
			rs = pstmt.executeQuery();
			if(rs.next()) { //해당부서명이 존재
				System.out.println("요청하신 "+dname+" 부서 정보");
				System.out.println("부서 번호 : "+rs.getInt("deptno"));
				System.out.println("부서 위치 : "+rs.getString("loc"));
				rs.close(); pstmt.close();
				pstmt = conn.prepareStatement(empQuery);
				pstmt.setString(1, dname);
				rs = pstmt.executeQuery();
				if(rs.next()) {//사원존재
					do{
						int    empno = rs.getInt("empno");
						String ename = rs.getString("ename");
						int    sal   = rs.getInt("sal");
						String manager = rs.getString("manager");
						System.out.println(empno + "\t" +ename +"\t"+sal+"\t"+manager);
					}while(rs.next());
				}else {
					System.out.println(dname+" 부서 사원은 존재하지 않습니다.");
				}
			}else {
				System.out.println("해당 부서명이 존재하지 않습니다");
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







