package com.lect.ex2customer;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class CustomerMng extends JFrame implements ActionListener{
	private Container contenPane;
	private JPanel jpup, jpdown;
	private JTextField txtPhone, txtName, txtPoint;
	private JTextArea jta;
	private JScrollPane scrollbar;
	private JButton btnJoin, btnSearch, btnOutput, btnExit;
	private ArrayList<CustomerDto> customers;
	private CustomerDao dao;
	public CustomerMng(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dao = CustomerDao.getInstance();
		// 컨테이너 얻어오기
		contenPane = getContentPane();
		// 컨테이너 레이아웃 셋팅
		contenPane.setLayout(new FlowLayout());
		// 컴포넌트 생성
		jpup = new JPanel();
		jpup.setLayout(new GridLayout(3, 2));
		jpdown = new JPanel();
		jpdown.setLayout(new FlowLayout());
		txtPhone = new JTextField(15);
		txtName = new JTextField(15);
		txtPoint = new JTextField("1000", 15);
		jta = new JTextArea(15,30);
		scrollbar = new JScrollPane(jta);
		btnJoin = new JButton("가입");
		btnSearch = new JButton("폰조회");
		btnOutput = new JButton("출력");
		btnExit = new JButton("종료");
		// jpup에 add
		jpup.add(new JLabel("폰번호",(int)CENTER_ALIGNMENT));
		jpup.add(txtPhone);
		jpup.add(new JLabel("이  름",(int)CENTER_ALIGNMENT));
		jpup.add(txtName);
		jpup.add(new JLabel("포인트",(int)CENTER_ALIGNMENT));
		jpup.add(txtPoint);
		// jpdown에 add (버튼들 4)
		jpdown.add(btnJoin);
		jpdown.add(btnSearch);
		jpdown.add(btnOutput);
		jpdown.add(btnExit);
		// contenPane에 add
		contenPane.add(jpup);
		contenPane.add(jpdown);
		contenPane.add(scrollbar);
		
		setBounds(300, 300, 400, 450);
		setResizable(false);
		setVisible(true);
		// 이벤트 리스너 추가
		btnJoin.addActionListener(this);
		btnSearch.addActionListener(this);
		btnOutput.addActionListener(this);
		btnExit.addActionListener(this);
	}
	public CustomerMng() {
		this("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnJoin) {	// 가입
			String phone, name; int point=1000;
			phone = txtPhone.getText().trim();
			name = txtName.getText().trim();
			try {
				point = Integer.parseInt(txtPhone.getText().trim());
			}catch (Exception e1) { }
			int preIdx = phone.indexOf("-");
			int postIdx = phone.lastIndexOf("-");
			if(!phone.equals("") && !name.equals("") &&
					preIdx<postIdx) { // 전화번호는 xxx-xx-xx타입으로, 이름은 반드시 입력 
				//가입
				CustomerDto newCustomer = new CustomerDto(0, phone, name, point);
				int result = dao.insertCustomer(newCustomer);
				jta.setText(result==CustomerDao.SUCCESS? name+"님 가입성공":"가입실패");
				txtPhone.setText("");
				txtName.setText("");
				txtPoint.setText("1000");
			}else {
				jta.setText("유효한 값을 입력하세요");
			}
		}else if(e.getSource() == btnSearch) {
			// 동명일인이면 textField에 뿌리고, 동명이인이면 jta에 뿌리고, 없는 번호면 없는 번호라고 뿌리기
			String searchPhone = txtPhone.getText().trim();
			if(searchPhone.equals("")) {
				jta.setText("조회하고자 하는 전화번호 뒷자리나 전화번호 전체를 입력하세요");
				return;
			}
			customers = dao.selectSearchPhone(searchPhone);
			if(customers.size()==1) {
				String phone = customers.get(0).getPhone();
				String name  = customers.get(0).getName();
				int    point = customers.get(0).getPoint();
				txtPhone.setText(phone);
				txtName.setText(name);
				txtPoint.setText(String.valueOf(point));//int를 문자열로 바꿔서 setText함
			}else if(customers.size()>1) {
				jta.setText("");
				for(CustomerDto customer : customers) {
					jta.append(customer.toString()+"\n");
				}
			}else {
				txtName.setText("검색되지 않는 전화번호입니다");
			}
		}else if(e.getSource() == btnOutput) {
			customers = dao.selectAll();
			if(customers.size()==0) {
				jta.append("회원이 없습니다");
			}else {
				for(CustomerDto customer : customers) {
					jta.append(customer.toString()+"\n");
				}
			}
		}else if(e.getSource()==btnExit) {
			setVisible(false);
			dispose();
			System.exit(0);
		}//if
	}
	public static void main(String[] args) {
		new CustomerMng("회원관리");
	}
}