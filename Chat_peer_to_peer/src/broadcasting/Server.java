package broadcasting;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static  ServerSocket serverSocket;
	private static Socket[] sockets;
	
	Server(int port){
		
		sockets = new Socket[10];
		 serverSocket = new ServerSocket(Integer.valueOf(puerto));
         System.out.println("Inicializando el servidor... [Ok].");
	}
}
