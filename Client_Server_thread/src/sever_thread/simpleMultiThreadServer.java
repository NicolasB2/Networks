package sever_thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.concurrent.ConcurrentHashMap;

public class simpleMultiThreadServer implements Runnable{

	private static ConcurrentHashMap<String, Float> map;
	private Socket clientsocket;
	
	static {
		map = new ConcurrentHashMap<String, Float>();
		map.put("Axle", 238.50f);
		map.put("Gear", 45.55f);
		map.put("Wheel", 86.30f);
		map.put("Rotor", 8.50f);
	}
	
	public simpleMultiThreadServer(Socket socket) {
		this.clientsocket = socket;
	}
	public static void main(String[] args) {
		System.out.println("Multi-Treaded started");
		
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while(true) {
				System.out.println("Listening for a client connection");
				Socket socket = serverSocket.accept();
				System.out.println("Connected to a client");
				new Thread(new simpleMultiThreadServer(socket)).start();
			}
			
		} catch (Exception e) {
		}
		
		System.out.println("Multi-Thread Server Terminated");
	}

	@Override
	public void run() {
		System.out.println("Client Thread Strated");
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			PrintStream out = new PrintStream(clientsocket.getOutputStream());
			
			String partName;
			while(true) {
				partName = bis.readLine();
				
				if(partName.equalsIgnoreCase("quit")) {
					System.out.println("break");
					break;
				}
				
				float price = map.get(partName);
				out.println(price);
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				System.out.println("Request for " + partName+  
						"and returned a price of" + nf.format(price));
			}
		
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Client Thread Terminated");	
	}

}
