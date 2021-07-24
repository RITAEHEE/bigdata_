package com.lect.ex6preparedStatement;
import java.sql.*;
import java.util.Scanner;
// �μ����� �Է¹޾� �ش�μ����� ������ �μ����� ��� �ش� �μ����� ���, �̸�, �޿�, ����̸� ����
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
			System.out.print("�˻��ϰ��� �ϴ� �μ����� ?");
			String dname = scanner.next();
			pstmt.setString(1, dname);
			rs = pstmt.executeQuery();
			if(rs.next()) { //�ش�μ����� ����
				System.out.println("��û�Ͻ� "+dname+" �μ� ����");
				System.out.println("�μ� ��ȣ : "+rs.getInt("deptno"));
				System.out.println("�μ� ��ġ : "+rs.getString("loc"));
				rs.close(); pstmt.close();
				pstmt = conn.prepareStatement(empQuery);
				pstmt.setString(1, dname);
				rs = pstmt.executeQuery();
				if(rs.next()) {//�������
					do{
						int    empno = rs.getInt("empno");
						String ename = rs.getString("ename");
						int    sal   = rs.getInt("sal");
						String manager = rs.getString("manager");
						System.out.println(empno + "\t" +ename +"\t"+sal+"\t"+manager);
					}while(rs.next());
				}else {
					System.out.println(dname+" �μ� ����� �������� �ʽ��ϴ�.");
				}
			}else {
				System.out.println("�ش� �μ����� �������� �ʽ��ϴ�");
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







