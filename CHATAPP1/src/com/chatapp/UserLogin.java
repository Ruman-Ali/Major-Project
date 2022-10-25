 package com.chatapp;
import java.time.zone.*;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.chatapp.dao.UserDAO;
import com.chatapp.dto.UserDTO;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserLogin extends JFrame {

	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		UserLogin window = new UserLogin();
		window.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public UserLogin() {
		initialize();
	}
	
	private void register() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(textField.getText());
		userDTO.setPassword(passwordField.getPassword());
		userDTO.setPincode(pincode.getText());
		try {
			String result = userDAO.register(userDTO);
			//JOptionPdane.showMessageDialog(this, result);
			int ch = JOptionPane.showConfirmDialog(this, result);
			if(ch==JOptionPane.OK_OPTION) {
				System.out.println("U click on OK");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private UserDAO userDAO = new UserDAO();
	private JTextField pincode;
	private void doLogin() {
		// Get TextBox Values (Userid and Password)
		String userid = textField.getText();
		//String password  = passwordField.getText();
		char[] password =passwordField.getPassword();
		
		System.out.println(userid + " "+password);
		UserDTO userDTO =new UserDTO(userid, password);
		userDTO.setPincode(pincode.getText());
		
		try {
			String result = userDAO.doLogin(userDTO);
			if(result.contains("Welcome")) {
				JOptionPane.showMessageDialog(this, result);
//				DashBoard dashBoard = new DashBoard();
//				dashBoard.setVisible(true);
				//this.setVisible(false);
				
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		login.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		login.setToolTipText("Click to Login");
		login.setBounds(68, 199, 117, 29);
		getContentPane().add(login);
		
		JButton reset = new JButton("Register");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		reset.setBounds(218, 199, 117, 29);
		getContentPane().add(reset);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(172, 37, 89, 29);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(206, 78, 130, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(206, 116, 130, 26);
	
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(68, 121, 61, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Userid");
		lblNewLabel_1_1.setBounds(68, 83, 61, 16);
		getContentPane().add(lblNewLabel_1_1);
		
		pincode = new JTextField();
		pincode.setBounds(205, 154, 130, 26);
		getContentPane().add(pincode);
		pincode.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Pincode");
		lblNewLabel_1_2.setBounds(68, 159, 61, 16);
		getContentPane().add(lblNewLabel_1_2);
	}

}
