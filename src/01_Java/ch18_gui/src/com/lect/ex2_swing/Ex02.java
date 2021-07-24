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
	//private JPanel jPanel;         // 컨테이너를 얻어오기 위한 변수
	private Container contenPane;  // 컨테이너를 얻어오기 위한 변수
	private ImageIcon icon;
	private JButton jBtn;
	private JTextField jtxtField;
	//private String[] item = {"A","B","C"}; // 컴보박스에 들어갈 리스트
	private Vector<String> items;          // 컴포박스에 들어갈 리스트
	private JComboBox<String> jCombo;
	private JCheckBox         jCheck;
	private JLabel            jlBlank;
	private JButton           jBtnExit;
	public Ex02() {
		setDefaultCloseOperation(EXIT_ON_CLOSE); // x클릭시 종료
		// 컨테이너 받아서 컴포넌트들 객체 생성한 후 add -> 화면조정(크기, 위치)
		// swing은 컨테이너를 얻어오는 작업
		contenPane = getContentPane();
		contenPane.setLayout(new FlowLayout()); // 레이아웃 지정
		//jPanel = (JPanel) getContentPane();
		icon = new ImageIcon("icon/write.gif");
		jBtn = new JButton("Button", icon);
		jtxtField = new JTextField(20);
		//jCombo = new JComboBox<String>(item); //1.컴보박스 리스트를 item 배열로 사용
		
		items = new Vector<String>();
		items.add("A"); items.add("B"); items.add("C");
		jCombo = new JComboBox<String>(items);//2.컴보박스 리스트를 items 벡터로 사용
		
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
		pack(); // 컴포넌트들을 포함한 최소한의 사이즈로 조정됨
		setVisible(true);
		setLocation(200, 200);
		// 이벤트 추가
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
				return; // jtxtField이 빈스트링이거나 space일 경우
			}
			// items.add(temp); // 벡터에 추가하여도 자동적으로 컴보박스에 추가됨
			jCombo.addItem(temp); // 콤보박스에 추가
			jlBlank.setText(jtxtField.getText().trim());
			jtxtField.setText("");
			String name = JOptionPane.showInputDialog("이름은?");
			System.out.println(name+"님 반갑습니다");
		}else if(e.getSource() == jCombo) {
			jlBlank.setText(jCombo.getSelectedItem().toString());
//			jCombo.setSelectedIndex(0);
			jCombo.setSelectedItem("A");
		}else if(e.getSource() == jCheck) {
			if(jCheck.isSelected()) { // 체크됨
				jlBlank.setText(jCheck.getText());
			}else { // 언체크됨
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
