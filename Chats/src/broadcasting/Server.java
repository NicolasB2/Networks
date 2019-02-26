package broadcasting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ServerSocket serverSocket;
	private static Socket[] sockets;
	private DataInputStream in;
	private DataOutputStream out;

	public Server(int port) {

		sockets = new Socket[10];
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Inicializando el servidor... [Ok].");
			

			while (true) {
			
				Socket socket = serverSocket.accept();
				System.out.println("Nueva conexion entrante");
				
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				Server_catcher_thread sct = new Server_catcher_thread(socket, sockets, in, out);
				sct.start();

//    			String client_Request = in.readUTF();
//    			System.out.println("El mensaje enviado por el cliente fue : " + client_Request);
//    			
//    			out.writeUTF("okay");
			}

		} catch (IOException e) {
			System.out.println("error");
		}

	}

	public static void main(String[] args) {
		Server s = new Server(8000);
	}
}
