package com.lect.ex3_quiz;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	private Container contenPane; // �����̳ʸ� �޾ƿ� ����
	private JPanel    jpUp, jpDown;      
	private JTextField jtxtName, jtxtTel, jtxtAge;
	private JButton btnInput, btnOut;
	private ArrayList<Person> person;
	public MyFrame(String title) {
		super(title);
		person = new ArrayList<Person>();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contenPane = getContentPane();
		jpUp = new JPanel(new GridLayout(3, 2));
		jpDown = new JPanel(new FlowLayout());
		jtxtName = new JTextField();
		jtxtTel  = new JTextField();
		jtxtAge  = new JTextField();
		ImageIcon icon1 = new ImageIcon("icon/join.png");
		btnInput = new JButton("�Է�",icon1);
		ImageIcon icon2 = new ImageIcon("icon/output.png");
		btnOut = new JButton("���",icon2);
		jpUp.add(new JLabel("�̸�", (int) CENTER_ALIGNMENT));
		jpUp.add(jtxtName);
		jpUp.add(new JLabel("��ȭ", (int) CENTER_ALIGNMENT));
		jpUp.add(jtxtTel);
		jpUp.add(new JLabel("����", (int) CENTER_ALIGNMENT));
		jpUp.add(jtxtAge);
		jpDown.add(btnInput); 
		jpDown.add(btnOut);
		
		contenPane.add(jpUp, BorderLayout.CENTER);
		contenPane.add(jpDown, BorderLayout.SOUTH);
		setBounds(100, 100, 300, 200);
		setVisible(true);
		btnInput.addActionListener(this);
		btnOut.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnInput) {
			String name, tel; int age;
			name = jtxtName.getText().trim();
			tel  = jtxtTel.getText().trim();
			try {
				age = Integer.parseInt(jtxtAge.getText().trim());
			}catch (Exception ex) {
				age = -1;
			}
			if(!name.equals("") && !tel.equals("") && age !=-1) {
				person.add(new Person(name, tel, age));
				jtxtName.setText("");
				jtxtTel.setText("");
				jtxtAge.setText("");
			}else {
				System.out.println("��ȿ���� �ʴ� ���Դϴ�");
			}
		}else if(e.getSource()==btnOut){
			// �ܼ�â ���
			for(Person p : person) {
				System.out.println(p);
			}
			// 1byte�� ����ϴ� OutputStream�̿�
			OutputStream os =null;
			try {
				os = new FileOutputStream("src/com/lect/ex3_quiz/person1byte.txt", true);
				for(Person p : person) {
					os.write((p.toString()+"\r\n").getBytes());
				}
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
				}catch (Exception e1) { }
			}// try-catch
			// 2byte�� ����ϴ� Writer�̿�
			Writer writer=null;
			try {
				writer = new FileWriter("src/com/lect/ex3_quiz/person2byte.txt",true);
				for(Person p : person)
					writer.write(p.toString()+"\r\n");
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			} finally {
				try {
					if(writer!=null) writer.close();
				}catch (Exception e1) { }
			}// try-catch
			// 3. printWriter�̿��ϴ� ���
			writer =null;
			PrintWriter printWriter = null;
			try {
				writer = new FileWriter("src/com/lect/ex3_quiz/personPrintWriter.txt",true);
				printWriter = new PrintWriter(writer);
				for(Person p : person)
					printWriter.println(p.toString());
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			} finally {
				try {
					if(printWriter!=null) printWriter.close();
					if(writer!=null) writer.close();
				}catch (Exception e1) { }
			}// try-catch
		}//if
	}
	public static void main(String[] args) {
		new MyFrame("GUI ����");
	}
}
