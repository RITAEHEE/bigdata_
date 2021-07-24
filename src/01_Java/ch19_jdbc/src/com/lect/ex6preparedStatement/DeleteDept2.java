package com.lect.ex6preparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
// �μ���ȣ�� �޾� emp���̺� �����ϴ� �μ���ȣ���� ���� �� dept�� ����
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
		System.out.print("������ �μ���ȣ�� ?");
		int deptno = sc.nextInt();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott","tiger");
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			if(rs.next()) { // �ش�μ���ȣ ����� ����
				System.out.println("�ش�μ���ȣ�� ����� �����Ͽ� �μ��� ������ �� �����ϴ�");
			}else {// �ش�μ���ȣ ��� �������
				rs.close();
				pstmt.close();
				pstmt = conn.prepareStatement(deleteSql);
				pstmt.setInt(1, deptno);
				int result = pstmt.executeUpdate();
				System.out.println(result==1? "���� ����":"�ش� �μ���ȣ�� �������� �ʽ��ϴ�");
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










