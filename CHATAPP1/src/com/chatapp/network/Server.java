package com.chatapp.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
public class Server {
	ServerSocket serverSocket;
	Socket socket;
	InputStream in;
	OutputStream out;
	//ServerThread serverTh;
	ArrayList<ServerThread> sockets = new ArrayList<>();
	public Server() throws IOException
	{
		serverSocket = new ServerSocket(9001);
	//	in = socket.getInputStream();
	//	out = socket.getOutputStream();
		System.out.println("Server is Waiting");
		while(true)
		{	
			socket = serverSocket.accept();
			ServerThread serverThread = new ServerThread(this,socket);
			sockets.add(serverThread);
			serverThread.start();
		    System.out.println("Client Join");
	}}
	/*public void readMessage() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String inputLine = "";
		while(true) {
		inputLine = br.readLine();
		System.out.println("Message"+inputLine);
	}}
	public void sendMessages(String messages) throws IOException
	{
		byte b[] = messages.getBytes();
		out.write(b);
	}
	public void closeConnection() throws IOException
	{
		if(socket!= null)
		{
			socket.close();
		}
	}*/

	public static void main(String args[]) throws UnknownHostException, IOException
	{
		try {
		Server server= new Server();
		}
		catch(Exception e)
		{
			//server.readMessage();
		}
		//server.sendMessages("Hi i am Server");
	//	server.closeConnection();
		
	}}
 
