package com.chatapp.network;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Client extends JFrame{
	
Socket socket;
PrintWriter out;
InputStream in;
	ClientThread clientThread;
	private JLabel heading=new JLabel("Welcome User!");
	private JTextArea messageArea=new JTextArea();
	private JTextField messageInput=new JTextField();
	private Font font=new Font("Serif", Font.BOLD, 34);
	public Client() throws UnknownHostException, IOException 
	{
		try
		{
		socket = new Socket("localhost",9001);
		System.out.println("Connetion Done..");
		in = socket.getInputStream();
		out = new PrintWriter(socket.getOutputStream());
		createGUI();
		handleEvents();
		clientThread = new ClientThread(in);
		System.out.println("Send Message to server :");
		clientThread.start();
		}
		catch(IOException e)
		{
			System.out.println("Server is not found");
		}
		}
	private void handleEvents() {
		// TODO Auto-generated method stub
		messageInput.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("key released"+e.getKeyCode());
				if(e.getKeyCode()==10) {
				//System.out.println("u have pressed enter button");
			String contentToSend=messageInput.getText();
			messageArea.append("Me :"+contentToSend+"\n");
			out.println(contentToSend);
			out.flush();
			messageInput.setText("");
			messageInput.requestFocus();
				}
			}
			
		});
		
	}
	private void createGUI() {
		// TODO Auto-generated method stub
		this.setTitle("Let's Chat");
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		heading.setFont(font);
		messageArea.setFont(font);
		messageInput.setFont(font);
		heading .setIcon(new ImageIcon(Client.class.getResource("/com/chatapp/network/ChatLogo.png")));
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setVerticalTextPosition(SwingConstants.BOTTOM);
		heading.setHorizontalAlignment(SwingConstants.LEFT);
		heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		messageArea.setEditable(false);
		this.setLayout(new BorderLayout());
		this.add(heading,BorderLayout.NORTH);
		JScrollPane jscrollpane=new JScrollPane(messageArea);
		this.add(jscrollpane,BorderLayout.CENTER);
		this.add(messageInput,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	public void sendMessages(BufferedReader br1) throws IOException
	{
		//System.out.println("Send Message");
		while(true) {
		String content = br1.readLine();
		if(content.equals("exit")) {
			System.out.println("server terminated the chat");
			JOptionPane.showConfirmDialog(this, "server terminated the chat");
			messageInput.setEnabled(false);
			socket.close();
			break;
		}
		messageArea.append("Server: "+content+"\n");
		
		out.println(content);
		out.flush();
	}}
	public void closeConnection() throws IOException
	{
		if(socket!= null)
		{
			socket.close();
		}
	}

	public static void main(String args[]) throws UnknownHostException, IOException
	{
		Client client = new Client();
		while(true)
		{
		
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println(message);
		client.sendMessages(br1);
		//client.closeConnection();
		}
		}
}
