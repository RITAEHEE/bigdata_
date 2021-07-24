package com.lect.exam;

import java.util.ArrayList;
import java.util.Vector;

public class StudentDaoTest {
	public static void main(String[] args) {
		StudentDao dao = StudentDao.getInstance();
		System.out.print("0. 전공이름들");
		Vector<String> mnames = dao.getMNamelist();
		for(String mname : mnames) {
			System.out.println(mname);
		}
		System.out.println("1. 학번 검색");
		StudentDto student = dao.sNogetStudent("2021001");
		System.out.println(student);
		System.out.println("2. 이름검색");
		ArrayList<StudentDto> students = dao.sNamegetStudent("정우성");
		System.out.println(students);
		System.out.println("3. 전공검색");
		students = dao.mNamegetStudent("빅데이터학");
		System.out.println(students);
		System.out.println("4. 학생입력");
		student = new StudentDto("학번은어차피내가입력안해", "신길동", "빅데이터학", 96);
		int result = dao.insertStudent(student);
		System.out.println(result==StudentDao.SUCCESS? "입력성공":"입력실패");
		System.out.println("5. 학생수정");
		student = new StudentDto("2021005","신길똥","소프트웨어",91);
		result = dao.updateStudent(student);
		System.out.println("6. 학생출력");
		students = dao.getStudents();
		for(StudentDto s:students) {
			System.out.println(s);
		}
		System.out.println("8. 제적처리");
		result = dao.sNoExpel("2021005");
		System.out.println(result==StudentDao.SUCCESS? "제적처리성공":"제적처리실패");
		System.out.println("7. 제적자출력");
		students = dao.getStudentsExpel();
		for(StudentDto s:students) {
			System.out.println(s);
		}
	}
}
