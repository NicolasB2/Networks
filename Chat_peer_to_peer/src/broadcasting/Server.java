package broadcasting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static  ServerSocket serverSocket;
	private static Socket[] sockets;
	
	Server(int port){
		
		sockets = new Socket[10];
		 try {
			serverSocket = new ServerSocket(port);
			System.out.println("Inicializando el servidor... [Ok].");
			Socket socket = serverSocket.accept();
            System.out.println("Nueva conexion entrante");
            
		} catch (IOException e) {
		}
         
	}
}
