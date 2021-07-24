package com.lect.ex1person;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class PersonMngGUI extends JFrame implements ActionListener{
	// ȭ�鿡 �߰��� ������Ʈ ����
	private Container contenPane;
	private JPanel    jpup, jpbtn;
	private JTextField txtName, txtKor, txtEng, txtMat;
	private Vector<String> jnames; // �޺��ڽ� �ȿ� �� item
	private JComboBox<String> comJob;
	private JButton    btnInput, btnSearch, btnOutput, btnExit;
	private JTextArea  txtPool;
	private JScrollPane scrollpane;
	// db ������ ���� ����
	private PersonDao dao;
	private ArrayList<PersonDto> person;
	public PersonMngGUI(String title) {
		super(title);
		dao = PersonDao.getInstance();
		setDefaultCloseOperation(EXIT_ON_CLOSE); // X Ŭ���� ����
		contenPane = getContentPane(); // ��ü ȭ�� get
		contenPane.setLayout(new FlowLayout()); // ��ü ȭ���� ���̾ƿ� ����
		jpup = new JPanel(new GridLayout(5, 2));
		jpbtn = new JPanel(new FlowLayout());
		txtName = new JTextField(20);
		jnames = dao.jnamelist();
		comJob = new JComboBox<String>(jnames);
		txtKor = new JTextField(20);
		txtEng = new JTextField(20);
		txtMat = new JTextField(20);
		ImageIcon icon1 = new ImageIcon("src/icon/join.png");
		btnInput = new JButton("�Է�", icon1);
		ImageIcon icon2 = new ImageIcon("src/icon/search.png");
		btnSearch = new JButton("������ȸ", icon2);
		ImageIcon icon3 = new ImageIcon("src/icon/output.png");
		btnOutput = new JButton("��ü���", icon3);
		ImageIcon icon4 = new ImageIcon("src/icon/exit.png");
		btnExit   = new JButton("����", icon4);
		btnInput.setPreferredSize(new Dimension(150, 50));
		btnSearch.setPreferredSize(new Dimension(150, 50));
		btnOutput.setPreferredSize(new Dimension(150, 50));
		btnExit.setPreferredSize(new Dimension(150, 50));
		txtPool = new JTextArea(10, 60);
		scrollpane = new JScrollPane(txtPool);
		jpup.add(new JLabel("�̸�", (int) CENTER_ALIGNMENT));
		jpup.add(txtName);
		jpup.add(new JLabel("����", (int) CENTER_ALIGNMENT));
		jpup.add(comJob);
		jpup.add(new JLabel("����", (int) CENTER_ALIGNMENT));
		jpup.add(txtKor);
		jpup.add(new JLabel("����", (int) CENTER_ALIGNMENT));
		jpup.add(txtEng);
		jpup.add(new JLabel("����", (int) CENTER_ALIGNMENT));
		jpup.add(txtMat);
		jpbtn.add(btnInput);
		jpbtn.add(btnSearch);
		jpbtn.add(btnOutput);
		jpbtn.add(btnExit);
		contenPane.add(jpup);
		contenPane.add(jpbtn);
		contenPane.add(scrollpane);
		setVisible(true);
		setSize(new Dimension(700, 450));
		setLocation(200, 150);
		btnInput.addActionListener(this);
		btnSearch.addActionListener(this);
		btnOutput.addActionListener(this);
		btnExit.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInput) {
			// txtName, comJob, txtKor, txtEng, txtMat�� ���� dao���� insert
			String name = txtName.getText().trim();
			String jname = comJob.getSelectedItem().toString();
			String korStr = txtKor.getText().trim();
			String engStr = txtEng.getText().trim();
			String matStr = txtMat.getText().trim();
			if(name.equals("") || jname.equals("") || korStr.equals("") || 
					engStr.equals("") || matStr.equals("")) {
				txtPool.setText("�̸�, ����, ��, ��, �� ��� �Է��ϼž� �Է� ����");
				return;
			}
			int kor = Integer.parseInt(korStr); 
			int eng = Integer.parseInt(engStr);
			int mat = Integer.parseInt(matStr);
			int result = dao.insertPerson(new PersonDto(name, jname, kor, eng, mat));
			if(result == PersonDao.SUCCESS) {
				txtPool.setText(name+"�� �Է¼���");
				txtName.setText("");
				comJob.setSelectedIndex(0); // �޺��ڽ� 0��° ����
				txtKor.setText("");
				txtEng.setText("");
				txtMat.setText("");
			}
		}else if(e.getSource() == btnSearch) {
			// comJob�� ���õ� ������ ��ȸ ����� txtPool�� ���
			String jname = comJob.getSelectedItem().toString();
			if(jname.equals("")) {
				txtPool.setText("������ ���� ��, ������ȸ ����");
				return;
			}
			person = dao.selectJname(jname);
			if(person.isEmpty()) {
				txtPool.setText("�ش� ������ ����� �����ϴ�");
			}else {
				txtPool.setText("rank\t�̸�\t����\t����\t����\t����\t����\n");
				for(PersonDto p : person) {
					txtPool.append(p.toString()+"\n");
					//txtPool.setText(txtPool.getText()+p.toString()+"\n");
				}
			}
		}else if(e.getSource() == btnOutput) {
			// ��ü ����� txtPool��
			person = dao.selectAll();
			if(person.size() == 0) {
				txtPool.setText("�Էµ� ����� �����ϴ�.");
			}else {
				txtPool.setText("rank\t�̸�\t����\t����\t����\t����\t����\n");
				for(PersonDto p : person) {
					//txtPool.append(p.toString()+"\n");
					txtPool.setText(txtPool.getText()+p.toString()+"\n");
				}
			}
		}else if(e.getSource() == btnExit) {
			setVisible(false);
			dispose();
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		new PersonMngGUI("������ ��������");
	}
}










