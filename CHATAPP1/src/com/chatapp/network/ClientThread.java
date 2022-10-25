package com.chatapp.network;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
import java.io.PrintWriter;

	// message read
	// Runnable Interface , Thread class
	public class ClientThread extends Thread {
		private InputStream in;
		public ClientThread(InputStream in) {
			this.in = in;
			
		}
		@Override
		public void run() {
			try {
				readMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public String readMessage() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while(true) { // Infinite Loop (Main Thread Busy)
				String inputLine = "";
				inputLine = br.readLine();
				System.out.println("Message Sent "+inputLine);
				
			}
			 
		}

}
