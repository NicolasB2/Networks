package broadcasting;

import java.io.*;
import java.net.Socket;

public class Client {

	private Socket socket;
	private String  Server_Ip;
	private String port;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	public Client(String Server_Ip, int port) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		DataInputStream in;
		DataOutputStream out;
		
		System.out.println("Establishing connection. Please wait ...");

		try {
			socket = new Socket(Server_Ip, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			System.out.println("Connected");
			
			Client_read_thread rct = new Client_read_thread(in);
			Client_write_thread wct = new Client_write_thread(out);
			
			rct.start();
			wct.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		Client c = new Client("localhost", 8000);
	}
}
