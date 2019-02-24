package Unicasting;

import java.io.*;
import java.net.*;

public class Client {
	
	private Socket socket = null;

	public Client(String serverName, int serverPort) {
		

		BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		DataOutputStream out = null;
		
		System.out.println("Establishing connection. Please wait ...");
		
		try {
			socket = new Socket(serverName, serverPort);
			out =  new DataOutputStream(socket.getOutputStream());
			System.out.println("Connected");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
		String line = "";
		while (true) {
			try {
				
				line = br.readLine();
				out.writeUTF(line);
				
				if(line.equals("bye")) {
					out.flush();
					br.close();
					socket.close();
					System.out.println("bye");
					break;
				}
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
		
		
	}

	public static void main(String args[]) {
		Client client = client = new Client("localhost",8000);
	}
}