package broadcasting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ServerSocket serverSocket;// Socket which allowing connection whit clients
	private static Server_Send_Thread[] threads;// Array with the threads
	public String data;// String whit last messege

	/**
	 * Server's constructor
	 * @param port : number of free port to establish connection
	 */
	public Server(int port) {
		threads = new Server_Send_Thread[10];

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Inicializando el servidor... [Ok].");
			System.out.println("waiting for clients.......");



		}catch(

	Exception e)
	{
		e.printStackTrace();
	}

	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Server s = new Server(8000);
	}

}
