package com.lect.ex2_swing;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Ex03_GUI extends JFrame implements ActionListener{
	private Container contenPane;
	private JPanel jp;
	private JTextField jtxtName, jtxtTel, jtxtAge;
	private ImageIcon icon;
	private JButton btnOut;
	private JTextArea jta;
	private JScrollPane scrollpane;
	private ArrayList<Friend> friends;
	public Ex03_GUI(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 컨테이너 얻어와서, 컴포넌트 객체 생성 후 add -> 화면조정
		contenPane = getContentPane();
		// contenPane.setLayout(new BorderLayout()); 기본값
		jp = new JPanel(new GridLayout(3, 2)); // 패널은 기본레이아웃이 FlowLayout
		jtxtName = new JTextField();
		jtxtTel  = new JTextField();
		jtxtAge  = new JTextField();
		icon = new ImageIcon("icon/output.png");
		btnOut = new JButton("화면출력", icon);
		jta = new JTextArea(5,30); // 5행 30열
		scrollpane = new JScrollPane(jta);
		friends = new ArrayList<Friend>();
		jp.add(new JLabel("이 름", (int)CENTER_ALIGNMENT));
		jp.add(jtxtName);
		jp.add(new JLabel("전 화", (int)CENTER_ALIGNMENT));
		jp.add(jtxtTel);
		jp.add(new JLabel("나 이", (int)CENTER_ALIGNMENT));
		jp.add(jtxtAge);
		contenPane.add(jp, BorderLayout.NORTH);
		contenPane.add(btnOut, BorderLayout.CENTER);
		contenPane.add(scrollpane, BorderLayout.SOUTH);
		jta.setText("이름\t전화\t\t나이\n"); // title
		setVisible(true);
		setBounds(100, 100, 400, 300);
//		setSize(new Dimension(400, 300));
//		setLocation(100, 100);
		// 이벤트 추가
		btnOut.addActionListener(this);
	}
	public Ex03_GUI() {
		this("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOut) { 
			// jtxtName, jtxtTel, jtxtAge값을 Friend 객체로 받아 friends(ArrayList)추가하고 jta 출력
			String name = jtxtName.getText().trim();
			String tel  = jtxtTel.getText().trim();
			if(name.equals("") || tel.equals("")) {
				System.out.println("이름과 전화번호는 필수 입력 사항입니다");
				return;
			}
			if(tel.indexOf("-") == tel.lastIndexOf("-") ||
					tel.indexOf("-")<2 || tel.lastIndexOf("-")<5 ||
					tel.length()<10) { // 0-9-9(X) 0-716-1110(X) 027161003(X)
				System.out.println("전화번호 포맷이 맞지 않습니다.");
				return;
			}
			int age = 0;
			try {
				age = Integer.parseInt(jtxtAge.getText());
				if(age<0 || age>=150) {
					System.out.println("유효하지 않는 나이는 0살로");
					age = 0;
				}
			}catch (NumberFormatException e1) {
				System.out.println("유효하지 않는 나이는 0살로");
			}
			Friend friend = new Friend(name, tel, age);
			jta.append(friend.toString()+"\n");
			friends.add(friend);
			jtxtName.setText("");
			jtxtTel.setText("");
			jtxtAge.setText("");
			System.out.println(friends.size()+"명 입력됨");
		}
	}
	public static void main(String[] args) {
		new Ex03_GUI("GUI 예제");
	}
}









