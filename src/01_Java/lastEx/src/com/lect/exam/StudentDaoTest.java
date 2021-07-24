package com.lect.exam;

import java.util.ArrayList;
import java.util.Vector;

public class StudentDaoTest {
	public static void main(String[] args) {
		StudentDao dao = StudentDao.getInstance();
		System.out.print("0. �����̸���");
		Vector<String> mnames = dao.getMNamelist();
		for(String mname : mnames) {
			System.out.println(mname);
		}
		System.out.println("1. �й� �˻�");
		StudentDto student = dao.sNogetStudent("2021001");
		System.out.println(student);
		System.out.println("2. �̸��˻�");
		ArrayList<StudentDto> students = dao.sNamegetStudent("���켺");
		System.out.println(students);
		System.out.println("3. �����˻�");
		students = dao.mNamegetStudent("��������");
		System.out.println(students);
		System.out.println("4. �л��Է�");
		student = new StudentDto("�й��������ǳ����Է¾���", "�ű浿", "��������", 96);
		int result = dao.insertStudent(student);
		System.out.println(result==StudentDao.SUCCESS? "�Է¼���":"�Է½���");
		System.out.println("5. �л�����");
		student = new StudentDto("2021005","�ű��","����Ʈ����",91);
		result = dao.updateStudent(student);
		System.out.println("6. �л����");
		students = dao.getStudents();
		for(StudentDto s:students) {
			System.out.println(s);
		}
		System.out.println("8. ����ó��");
		result = dao.sNoExpel("2021005");
		System.out.println(result==StudentDao.SUCCESS? "����ó������":"����ó������");
		System.out.println("7. ���������");
		students = dao.getStudentsExpel();
		for(StudentDto s:students) {
			System.out.println(s);
		}
	}
}
