package com.lect.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/*
0. ùȭ�鿡 �����̸��� �޺��ڽ��� �߰�(mName) 
	: public Vector<String> getMNamelist()
1. �й��˻� (sNo, sName, mName, score) 
	: public StudentDto sNogetStudent(String sNo)
2. �̸��˻� (sNo, sName, mName, score) 
	: public ArrayList<StudentDto> sNamegetStudent(String sName)
3. �����˻� (rank, sName(sNo����), mName(mNo����), score) 
	: public ArrayList<StudentDto> mNamegetStudent(String mName)
4. �л��Է� 
	: public int insertStudent(StudentSwingDto dto)
5. �л����� 
	: public int updateStudent(StudentSwingDto dto)
6. �л���� (rank, sName(sNo����), mName(mNo����), score) 
	: public ArrayList<StudentDto> getStudents()
7. ���������  (rank, sName(sNo����), mName(mNo����), score) 
	: public ArrayList<StudentDto> getStudentsExpel()
8. ����ó�� : public int sNoExpel(String sNo)
*/
public class StudentDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final int SUCCESS = 1;
	public static final int FAIL    = 0;
	private static StudentDao INSTANCE;
	public static StudentDao getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new StudentDao();
		}
		return INSTANCE;
	}
	public StudentDao() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	// 0. ùȭ�鿡 �����̸��� �޺��ڽ��� �߰��� �� ���
	public Vector<String> getMNamelist(){
		Vector<String> mnames = new Vector<String>();
		mnames.add("");
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		String sql = "SELECT MNAME FROM MAJOR";
		try {
			conn = DriverManager.getConnection(url, "scott","tiger");
			stmt = conn.createStatement();
			rs   = stmt.executeQuery(sql); 
			while(rs.next()) {
				mnames.add(rs.getString("mname"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs  !=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return mnames;
	}
	//1. �й��˻� (sNo, sName, mName, score)
	public StudentDto sNogetStudent(String sNo) {
		StudentDto dto = null;
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		String sql = "SELECT SNO, SNAME, MNAME, SCORE " + 
				"  FROM STUDENT S, MAJOR M " + 
				"  WHERE S.MNO=M.MNO AND SNO=?";
		try {
			conn = DriverManager.getConnection(url, "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sNo);
			rs   = pstmt.executeQuery(); 
			if(rs.next()) {
				String sName = rs.getString("sName");
				String mName = rs.getString("mName");
				int score = rs.getInt("score");
				dto = new StudentDto(sNo, sName, mName, score);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {}
		}
		return dto;
	}
	// 2. �̸��˻� (sNo, sName, mName, score)
	public ArrayList<StudentDto> sNamegetStudent(String sName){
		ArrayList<StudentDto> dtos = new ArrayList<StudentDto>();
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		String sql = "SELECT SNO, SNAME, MNAME, SCORE" + 
				"  FROM STUDENT S, MAJOR M" + 
				"  WHERE S.MNO=M.MNO AND SNAME=?";
		try {
			conn = DriverManager.getConnection(url, "scott","tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			rs   = pstmt.executeQuery(); 
			while(rs.next()) {
				String sNo = rs.getString("sNo");
				//String sName = rs.getString("sName");
				String mName = rs.getString("mName");
				int score = rs.getInt("score");
				dtos.add(new StudentDto(sNo, sName, mName, score));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return dtos;
	}
	//3. �����˻� (rank, sName(sNo����), mName(mNo����), score) - 1 ���켺(2021001) ��������(1) 90
	public ArrayList<StudentDto> mNamegetStudent(String mName){
		ArrayList<StudentDto> dtos = new ArrayList<StudentDto>();
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		String sql = "SELECT ROWNUM RANK, A.*" + 
				"  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE" + 
				"          FROM STUDENT S, MAJOR M" + 
				"          WHERE S.MNO=M.MNO AND MNAME=?" + 
				"          ORDER BY SCORE DESC) A";
		try {
			conn = DriverManager.getConnection(url, "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mName);
			rs   = pstmt.executeQuery(); 
			while(rs.next()) {
				int rank = rs.getInt("rank");
				String sName = rs.getString("sName");
				mName = rs.getString("mName"); // �Ķ���ͷ� �Էµ� mName�� �������� ��� ���⼭�� mName�� ������(1)
				int score = rs.getInt("score");
				dtos.add(new StudentDto(rank, sName, mName, score));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return dtos;
	}
	// 4. �л��Է� (sName, mName, score�� �ִ� dto)
	public int insertStudent(StudentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO STUDENT (sNO, sNAME, mNO, SCORE) VALUES" + 
				"    (TO_CHAR(SYSDATE, 'YYYY')" + 
				"    ||TRIM(TO_CHAR(STUDENT_SEQ.NEXTVAL,'000'))," + 
				"    ?,(SELECT mNO FROM MAJOR WHERE mNAME=?), ?)";
		try {
			conn = DriverManager.getConnection(url, "scott","tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getsName());
			pstmt.setString(2, dto.getmName());
			pstmt.setInt(3, dto.getScore());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 5. �л����� (sNo, sName, mName, score �� �ִ� dto)
	public int updateStudent(StudentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE STUDENT SET sNAME=?," + 
				"            mNO=(SELECT mNO FROM MAJOR WHERE mNAME=?)," + 
				"            SCORE = ?" + 
				"        WHERE sNO=?";
		try {
			conn = DriverManager.getConnection(url, "scott","tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getsName());
			pstmt.setString(2, dto.getmName());
			pstmt.setInt(3, dto.getScore());
			pstmt.setString(4, dto.getsNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 6. �л���� (rank, sName(sNo����), mName(mNo����), score) ��� : 1 ���켺(2021001) ��������(1) 90
	public ArrayList<StudentDto> getStudents(){
		ArrayList<StudentDto> dtos = new ArrayList<StudentDto>();
		Connection conn  = null;
		Statement  stmt = null;
		ResultSet  rs    = null;
		String sql = "SELECT ROWNUM RANK, A.*" + 
				"  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE" + 
				"          FROM STUDENT S, MAJOR M" + 
				"          WHERE S.MNO=M.MNO AND sEXPEL=0" + 
				"          ORDER BY SCORE DESC) A";
		try {
			conn = DriverManager.getConnection(url, "scott", "tiger");
			stmt = conn.createStatement();
			rs   = stmt.executeQuery(sql); 
			while(rs.next()) {
				int rank = rs.getInt("rank");
				String sName = rs.getString("sName");
				String mName = rs.getString("mName"); // �Ķ���ͷ� �Էµ� mName�� �������� ��� ���⼭�� mName�� ������(1)
				int score = rs.getInt("score");
				dtos.add(new StudentDto(rank, sName, mName, score));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(stmt !=null)  stmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return dtos;
	}
	//7. ��������� (rank, sName(sNo����), mName(mNo����), score) ��� : 1 ���켺(2021001) ��������(1) 90
	public ArrayList<StudentDto> getStudentsExpel(){
		ArrayList<StudentDto> dtos = new ArrayList<StudentDto>();
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		String sql = "SELECT ROWNUM RANK, A.*" + 
				"  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE" + 
				"          FROM STUDENT S, MAJOR M" + 
				"          WHERE S.MNO=M.MNO AND sEXPEL=1" + 
				"          ORDER BY SCORE DESC) A";
		try {
			conn = DriverManager.getConnection(url, "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			rs   = pstmt.executeQuery(); 
			while(rs.next()) {
				int rank = rs.getInt("rank");
				String sName = rs.getString("sName");
				String mName = rs.getString("mName"); // �Ķ���ͷ� �Էµ� mName�� �������� ��� ���⼭�� mName�� ������(1)
				int score = rs.getInt("score");
				dtos.add(new StudentDto(rank, sName, mName, score));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn  !=null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return dtos;
	}
	// 8. ����ó��
	public int sNoExpel(String sNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE STUDENT SET sEXPEL=1 WHERE sNO=?";
		try {
			conn = DriverManager.getConnection(url, "scott","tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
