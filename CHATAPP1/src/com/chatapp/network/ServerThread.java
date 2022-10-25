package com.chatapp.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket;
	private InputStream in;
	//private OutputStream out;
	private Server server;
	private PrintWriter out1;
	public ServerThread(Server server,Socket socket) throws IOException
	{
		
		this.socket = socket;
		this.in = this.socket.getInputStream();
	//	this.out = this.socket.getOutputStream();
		this.server = server;		
		
	}
	@Override
	public void run()
	{
		try {
			readMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void readMessage() throws IOException {
		try
		{
			 
		while(true) { // Infinite Loop (Main Thread Busy)
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String inputLine;
			inputLine = br.readLine();
			if(inputLine=="exit") {
			System.out.println("Client wants to terminate the chat");
			break;
			}
			System.out.println("Client: "+inputLine);
			//byte b[] = inputLine.getBytes();
			//System.out.println(inputLine);
			for(ServerThread thread : server.sockets) {
				out1 = new PrintWriter(thread.socket.getOutputStream());
				out1.println(inputLine);
				out1.flush();
				//thread.socket.getOutputStream().write(inputLine.getBytes());
			}
			// write to all
			//System.out.println("Message Rec "+inputLine);
		}}
		catch(Exception e)
		{
			
		}
	}}
