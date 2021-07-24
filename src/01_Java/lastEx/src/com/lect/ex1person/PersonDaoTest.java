package com.lect.ex1person;
import java.util.ArrayList;
import java.util.Vector;
public class PersonDaoTest {
	public static void main(String[] args) {
		PersonDao dao = PersonDao.getInstance();
		// 1.입력
		PersonDto newPerson = new PersonDto("박길동", "배우", 91, 80, 99);
		int result = dao.insertPerson(newPerson);
		System.out.println(result==PersonDao.SUCCESS? "입력성공":"입력실패");
		// 2.직업별 조회
		ArrayList<PersonDto> person = dao.selectJname("배우");
		System.out.println("배우 직업 조회결과");
		for(PersonDto p : person) {
			System.out.println(p);
		}
		// 3. 전체조회
		person = dao.selectAll();
		System.out.println("전체");
		for(int idx=0 ; idx<person.size() ; idx++) {
			System.out.println(person.get(idx));
		}
		// 4. 직업리스트
		Vector<String> jnamelist = dao.jnamelist();
		for(int idx=0 ; idx < jnamelist.size() ; idx++) {
			System.out.println(idx+"번째 :" + jnamelist.get(idx));
		}
	}
}









