package com.lect.ex3insert;
// 1. �μ���ȣ�� �޾� �ش�μ���ȣ�� �ִ��� ��ȸ 
// 2-1. �ش�μ���ȣ�� ���� ��� : �μ���� ��ġ�� �޾� insert�մϴ�
// 2-2. �ش�μ���ȣ�� ���� ��� : �ߺ��� �μ���ȣ�Դϴ�.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
// insert�� ���۽�    : stmt.executeUpdate()�̿�
// insert�� ���۰�� : int Ÿ��
// select�� ���۽�    : stmt.executeQuery() �̿�
// select�� ���۰�� : ResultSet Ÿ��
public class InsertDept2 {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("�Է��� �μ���ȣ�� ? ");
		int deptno = scanner.nextInt();
		String selectSql = "SELECT * FROM DEPT WHERE DEPTNO="+deptno;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott","tiger");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectSql);
			if(rs.next()) {
				System.out.println("�ߺ��� �μ���ȣ�Դϴ�");
			}else {
				System.out.print("�Է��� �μ����� ? ");
				String dname = scanner.next();
//				byte[] d = dname.getBytes();
//				int firstLetterAscii = (int)d[0];
//				if( (firstLetterAscii>=65 && firstLetterAscii<=90) ||
//						(firstLetterAscii>=97 && firstLetterAscii<=122) ) {
//					if(dname.length()>14) {
//						System.out.println("�� ���");
//						return;
//					}
//				}else {
//					if(dname.length()>4) {
//						System.out.println("�� ���");
//						return;
//					}
//				}
				System.out.print("�Է��� �μ� ��ġ�� ? ");
				String loc = scanner.next();
				String insertSql = "INSERT INTO DEPT VALUES ("+
										deptno+",'"+dname+"','"+loc+"')";
				int result = stmt.executeUpdate(insertSql);
				if(result>0) {
					System.out.println("�Է� ����");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally { //(7)
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}//�������� try-catch
		}//try-catch
	}//main
}//class
