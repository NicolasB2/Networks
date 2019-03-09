package broadcasting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class Server_Send_Thread extends Thread {

	private Socket socket; // Socket with allowing connection
	private DataOutputStream out; // data otput
	private DataInputStream in; // data input
	private String data;

	/**
	 * Server_send_thread´s constructor
	 * 
	 * @param socket : socket with the conection between client and server
	 * @param data: data with the last 
	 */
	public Server_Send_Thread(Socket socket, String data) {
		this.socket = socket;
		this.data = data;
	}

	/**
	 * Method that runs when the thread it starts
	 */
	@Override
	public void run() {
		System.out.println("run the thread");
		boolean x = true;

		try {
			out = new DataOutputStream(socket.getOutputStream());
			while (x) {

				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
