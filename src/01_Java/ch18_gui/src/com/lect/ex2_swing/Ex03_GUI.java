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
		// �����̳� ���ͼ�, ������Ʈ ��ü ���� �� add -> ȭ������
		contenPane = getContentPane();
		// contenPane.setLayout(new BorderLayout()); �⺻��
		jp = new JPanel(new GridLayout(3, 2)); // �г��� �⺻���̾ƿ��� FlowLayout
		jtxtName = new JTextField();
		jtxtTel  = new JTextField();
		jtxtAge  = new JTextField();
		icon = new ImageIcon("icon/output.png");
		btnOut = new JButton("ȭ�����", icon);
		jta = new JTextArea(5,30); // 5�� 30��
		scrollpane = new JScrollPane(jta);
		friends = new ArrayList<Friend>();
		jp.add(new JLabel("�� ��", (int)CENTER_ALIGNMENT));
		jp.add(jtxtName);
		jp.add(new JLabel("�� ȭ", (int)CENTER_ALIGNMENT));
		jp.add(jtxtTel);
		jp.add(new JLabel("�� ��", (int)CENTER_ALIGNMENT));
		jp.add(jtxtAge);
		contenPane.add(jp, BorderLayout.NORTH);
		contenPane.add(btnOut, BorderLayout.CENTER);
		contenPane.add(scrollpane, BorderLayout.SOUTH);
		jta.setText("�̸�\t��ȭ\t\t����\n"); // title
		setVisible(true);
		setBounds(100, 100, 400, 300);
//		setSize(new Dimension(400, 300));
//		setLocation(100, 100);
		// �̺�Ʈ �߰�
		btnOut.addActionListener(this);
	}
	public Ex03_GUI() {
		this("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOut) { 
			// jtxtName, jtxtTel, jtxtAge���� Friend ��ü�� �޾� friends(ArrayList)�߰��ϰ� jta ���
			String name = jtxtName.getText().trim();
			String tel  = jtxtTel.getText().trim();
			if(name.equals("") || tel.equals("")) {
				System.out.println("�̸��� ��ȭ��ȣ�� �ʼ� �Է� �����Դϴ�");
				return;
			}
			if(tel.indexOf("-") == tel.lastIndexOf("-") ||
					tel.indexOf("-")<2 || tel.lastIndexOf("-")<5 ||
					tel.length()<10) { // 0-9-9(X) 0-716-1110(X) 027161003(X)
				System.out.println("��ȭ��ȣ ������ ���� �ʽ��ϴ�.");
				return;
			}
			int age = 0;
			try {
				age = Integer.parseInt(jtxtAge.getText());
				if(age<0 || age>=150) {
					System.out.println("��ȿ���� �ʴ� ���̴� 0���");
					age = 0;
				}
			}catch (NumberFormatException e1) {
				System.out.println("��ȿ���� �ʴ� ���̴� 0���");
			}
			Friend friend = new Friend(name, tel, age);
			jta.append(friend.toString()+"\n");
			friends.add(friend);
			jtxtName.setText("");
			jtxtTel.setText("");
			jtxtAge.setText("");
			System.out.println(friends.size()+"�� �Էµ�");
		}
	}
	public static void main(String[] args) {
		new Ex03_GUI("GUI ����");
	}
}









