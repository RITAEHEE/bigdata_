package com.lect.ex1person;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
public class PersonMngUseDao {
	public static void main(String[] args) {
		PersonDao dao = PersonDao.getInstance();
		ArrayList<PersonDto> person;
		Scanner scanner = new Scanner(System.in) ;
		String fn;
		// do~while��
		do {
			System.out.print("1:�Է� || 2:������ ��� || 3:��ü��� || �׿�:����");
			fn = scanner.next();
			switch(fn) {
			case "1"://1. �̸�, ������, ������ �Է¹޾� insert
				System.out.print("�Է��� �̸��� ?");
				String name = scanner.next();
				System.out.print("��������(���|����|������ ����)");
				String jname = scanner.next();
				System.out.print("����������?");
				int kor = scanner.nextInt();
				System.out.print("����������?");
				int eng = scanner.nextInt();
				System.out.print("����������?");
				int mat = scanner.nextInt();
				PersonDto newPerson = new PersonDto(name, jname, kor, eng, mat);
				int result = dao.insertPerson(newPerson);
				System.out.println(result == PersonDao.SUCCESS? "�Է¼���":"�Է½���");
				break;
			case "2"://2. ������ �Է¹޾� ���������� ���
				System.out.print("��ȸ�� �������� (���||����||����)");
				jname = scanner.next();
				person = dao.selectJname(jname);
				if(person.isEmpty()) {
					System.out.println("�ش� �������� ����� �����ϴ�.");
				}else {
					System.out.println("rank\t�̸�\t\t����\t����\t����\t����\t����");
					for(PersonDto p : person) {
						System.out.println(p);
					}
				}
				break;
			case "3"://3. ��ü���
				person = dao.selectAll();
				if(person.size()==0) {
					System.out.println("�Էµ� ����� �����ϴ�");
				}else {
					System.out.println("rank\t�̸�\t\t����\t����\t����\t����\t����");
					for(PersonDto p : person) {
						System.out.println(p);
					}
				}
				break;
			}
		}while(fn.equals("1") || fn.equals("2") || fn.equals("3"));
		scanner.close();
		System.out.println("BYE");
	}
}











