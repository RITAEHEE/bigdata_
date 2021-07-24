package com.lect.ex6preparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
// �μ���ȣ�� �޾� ����
public class DeleteDept {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Scanner sc = new Scanner(System.in);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM DEPT WHERE DEPTNO=?";
		System.out.print("������ �μ���ȣ�� ?");
		int deptno = sc.nextInt();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott","tiger");
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);// ����ǥ ä���
			// int result = stmt.executeUpdate(sql);
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println(deptno+"�� �μ� ���� �Ϸ�");
			}else {
				System.out.println(deptno+"�� �μ��� �������� �ʽ��ϴ�");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}










