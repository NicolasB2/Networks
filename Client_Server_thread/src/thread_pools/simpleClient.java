package thread_pools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class simpleClient {

	public static void main(String[] args) {
System.out.println("Client started");
		
		try {
			Socket socket = new Socket("localhost",5000);
			System.out.println("connected to server");
			PrintStream out = new PrintStream(socket.getOutputStream());
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			String partName = "Axle";
			out.println(partName);
			System.out.println(partName + " request sent");
			System.out.println("Response: "+ br.readLine());
			socket.close();
			
		} catch (Exception e) {
			System.out.println("problem");
		}

		System.out.println("client Terminated");
	}
}
