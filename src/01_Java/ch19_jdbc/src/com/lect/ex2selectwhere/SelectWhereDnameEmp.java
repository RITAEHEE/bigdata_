package com.lect.ex2selectwhere;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class SelectWhereDnameEmp {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		Scanner    scanner = new Scanner(System.in);
		System.out.print("���ϴ� �μ� �̸���? ");
		String dname = scanner.next().toUpperCase();
		String sql1 = "SELECT * FROM DEPT WHERE DNAME='"+dname+"'";
		sql1 = String.format("SELECT * FROM DEPT WHERE DNAME='%s'", dname);
		String sql2 = "SELECT W.EMPNO, W.ENAME, W.SAL, M.ENAME MANAGER" + 
				"  FROM EMP W, EMP M, DEPT D" + 
				"  WHERE W.MGR=M.EMPNO AND W.DEPTNO=D.DEPTNO AND DNAME='"+dname+"'";
		sql2 = String.format("SELECT W.EMPNO, W.ENAME, W.SAL, M.ENAME MANAGER" + 
				"  FROM EMP W, EMP M, DEPT D" + 
				"  WHERE W.MGR=M.EMPNO AND W.DEPTNO=D.DEPTNO AND DNAME='%s'", dname);
		try {
			Class.forName(driver);// (1)
			conn = DriverManager.getConnection(url, "scott","tiger"); //(2)
			stmt = conn.createStatement(); // (3)
			rs   = stmt.executeQuery(sql1); // (4)+(5)
			if(rs.next()) { // �ش�μ����� ����
				int deptno = rs.getInt("deptno");
				String loc = rs.getString("loc");
				System.out.println("�μ� ��ȣ : "+deptno);
				System.out.println("�μ� �̸� : "+dname);
				System.out.println("�μ� ��ġ : "+loc);
				rs.close(); // rs��ü ����
				rs = stmt.executeQuery(sql2);
				if(rs.next()) { // �ش�μ� ����� ���� (6)
					System.out.println("���\t�̸�\t�޿�\t����");
					do {
						int    empno = rs.getInt("empno");
						String ename = rs.getString("ename");
						int    sal   = rs.getInt("sal");
						String manager=rs.getString("manager");
						System.out.printf("%d\t%s\t%d\t%s\n", empno, ename, sal, manager);
					}while(rs.next());
				}else { // �ش�μ� ����� ����
					System.out.println("�ش� �μ� ����� �����ϴ�.");
				}
			}else {//�ش�μ����� ����
				System.out.println("�ش� �μ����� �������� �ʽ��ϴ�.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try { // (7)
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}













