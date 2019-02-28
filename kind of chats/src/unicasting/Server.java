package unicasting;

import java.net.*;
import java.io.*;

public class Server {

	public final static int PORT = 8000;
	private Socket socket = null;
	private ServerSocket server = null;

	public Server(int port) {
		try {

			server = new ServerSocket(port);
			System.out.println("Waiting for a client ...");
			socket = server.accept();
			System.out.println("Client accepted");

			while (true) {

				DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				String line = in.readUTF();
				System.out.println(line);

			}
			
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public static void main(String args[]) {
		Server s = new Server(8000);
	}
}
