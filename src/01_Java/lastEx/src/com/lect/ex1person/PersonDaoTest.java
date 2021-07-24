package com.lect.ex1person;
import java.util.ArrayList;
import java.util.Vector;
public class PersonDaoTest {
	public static void main(String[] args) {
		PersonDao dao = PersonDao.getInstance();
		// 1.�Է�
		PersonDto newPerson = new PersonDto("�ڱ浿", "���", 91, 80, 99);
		int result = dao.insertPerson(newPerson);
		System.out.println(result==PersonDao.SUCCESS? "�Է¼���":"�Է½���");
		// 2.������ ��ȸ
		ArrayList<PersonDto> person = dao.selectJname("���");
		System.out.println("��� ���� ��ȸ���");
		for(PersonDto p : person) {
			System.out.println(p);
		}
		// 3. ��ü��ȸ
		person = dao.selectAll();
		System.out.println("��ü");
		for(int idx=0 ; idx<person.size() ; idx++) {
			System.out.println(person.get(idx));
		}
		// 4. ��������Ʈ
		Vector<String> jnamelist = dao.jnamelist();
		for(int idx=0 ; idx < jnamelist.size() ; idx++) {
			System.out.println(idx+"��° :" + jnamelist.get(idx));
		}
	}
}









