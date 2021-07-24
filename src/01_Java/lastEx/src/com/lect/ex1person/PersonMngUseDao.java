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
		// do~while문
		do {
			System.out.print("1:입력 || 2:직업별 출력 || 3:전체출력 || 그외:종료");
			fn = scanner.next();
			switch(fn) {
			case "1"://1. 이름, 직업명, 국영수 입력받아 insert
				System.out.print("입력할 이름은 ?");
				String name = scanner.next();
				System.out.print("직업명은(배우|가수|엠씨만 가능)");
				String jname = scanner.next();
				System.out.print("국어점수는?");
				int kor = scanner.nextInt();
				System.out.print("영어점수는?");
				int eng = scanner.nextInt();
				System.out.print("수학점수는?");
				int mat = scanner.nextInt();
				PersonDto newPerson = new PersonDto(name, jname, kor, eng, mat);
				int result = dao.insertPerson(newPerson);
				System.out.println(result == PersonDao.SUCCESS? "입력성공":"입력실패");
				break;
			case "2"://2. 직업명 입력받아 총점순으로 출력
				System.out.print("조회할 직업명은 (배우||가수||엠씨)");
				jname = scanner.next();
				person = dao.selectJname(jname);
				if(person.isEmpty()) {
					System.out.println("해당 직업명의 사람이 없습니다.");
				}else {
					System.out.println("rank\t이름\t\t직업\t국어\t영어\t수학\t총점");
					for(PersonDto p : person) {
						System.out.println(p);
					}
				}
				break;
			case "3"://3. 전체출력
				person = dao.selectAll();
				if(person.size()==0) {
					System.out.println("입력된 사람이 없습니다");
				}else {
					System.out.println("rank\t이름\t\t직업\t국어\t영어\t수학\t총점");
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











