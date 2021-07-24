package com.lect.ex2_swing;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Ex02 extends JFrame implements ActionListener {
	//private JPanel jPanel;         // �����̳ʸ� ������ ���� ����
	private Container contenPane;  // �����̳ʸ� ������ ���� ����
	private ImageIcon icon;
	private JButton jBtn;
	private JTextField jtxtField;
	//private String[] item = {"A","B","C"}; // �ĺ��ڽ��� �� ����Ʈ
	private Vector<String> items;          // �����ڽ��� �� ����Ʈ
	private JComboBox<String> jCombo;
	private JCheckBox         jCheck;
	private JLabel            jlBlank;
	private JButton           jBtnExit;
	public Ex02() {
		setDefaultCloseOperation(EXIT_ON_CLOSE); // xŬ���� ����
		// �����̳� �޾Ƽ� ������Ʈ�� ��ü ������ �� add -> ȭ������(ũ��, ��ġ)
		// swing�� �����̳ʸ� ������ �۾�
		contenPane = getContentPane();
		contenPane.setLayout(new FlowLayout()); // ���̾ƿ� ����
		//jPanel = (JPanel) getContentPane();
		icon = new ImageIcon("icon/write.gif");
		jBtn = new JButton("Button", icon);
		jtxtField = new JTextField(20);
		//jCombo = new JComboBox<String>(item); //1.�ĺ��ڽ� ����Ʈ�� item �迭�� ���
		
		items = new Vector<String>();
		items.add("A"); items.add("B"); items.add("C");
		jCombo = new JComboBox<String>(items);//2.�ĺ��ڽ� ����Ʈ�� items ���ͷ� ���
		
		jCheck = new JCheckBox("CheckBox");
		jlBlank = new JLabel("");
		jBtnExit = new JButton("Exit");
		contenPane.add(new JLabel("Label"));
		contenPane.add(jBtn);
		contenPane.add(jtxtField);
		contenPane.add(jCombo);
		contenPane.add(jCheck);
		contenPane.add(jlBlank);
		contenPane.add(jBtnExit);
		jBtn.setPreferredSize(new Dimension(200, 50));
		jtxtField.setPreferredSize(new Dimension(300, 50));
		jCombo.setPreferredSize(new Dimension(100, 50));
		jCheck.setPreferredSize(new Dimension(100, 50));
		jlBlank.setPreferredSize(new Dimension(200, 50));
		jBtnExit.setPreferredSize(new Dimension(100, 50));
		pack(); // ������Ʈ���� ������ �ּ����� ������� ������
		setVisible(true);
		setLocation(200, 200);
		// �̺�Ʈ �߰�
		jBtn.addActionListener(this);
		jCombo.addActionListener(this);
		jCheck.addActionListener(this);
		jBtnExit.addActionListener(this);
	}
	public Ex02(String title) {
		this();
		setTitle(title);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==jBtn) {
			String temp = jtxtField.getText().trim().toUpperCase();
			if(temp.equals("")) {
				return; // jtxtField�� ��Ʈ���̰ų� space�� ���
			}
			// items.add(temp); // ���Ϳ� �߰��Ͽ��� �ڵ������� �ĺ��ڽ��� �߰���
			jCombo.addItem(temp); // �޺��ڽ��� �߰�
			jlBlank.setText(jtxtField.getText().trim());
			jtxtField.setText("");
			String name = JOptionPane.showInputDialog("�̸���?");
			System.out.println(name+"�� �ݰ����ϴ�");
		}else if(e.getSource() == jCombo) {
			jlBlank.setText(jCombo.getSelectedItem().toString());
//			jCombo.setSelectedIndex(0);
			jCombo.setSelectedItem("A");
		}else if(e.getSource() == jCheck) {
			if(jCheck.isSelected()) { // üũ��
				jlBlank.setText(jCheck.getText());
			}else { // ��üũ��
				jlBlank.setText("");
			}
		}else if(e.getSource() == jBtnExit) {
			setVisible(false);
			dispose();
			System.exit(0);
		}

	}
	public static void main(String[] args) {
		new Ex02();
	}
}
