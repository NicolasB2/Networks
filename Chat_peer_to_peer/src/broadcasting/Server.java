package broadcasting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static  ServerSocket serverSocket;
	private static Socket[] sockets;
	
	public Server(int port){
		
		DataInputStream in;
		DataOutputStream out;
		sockets = new Socket[10];
		 try {
			serverSocket = new ServerSocket(port);
			System.out.println("Inicializando el servidor... [Ok].");
			Socket socket = serverSocket.accept();
            System.out.println("Nueva conexion entrante");
            
            socket = serverSocket.accept();
            
            while(true) {
            	in = new DataInputStream(socket.getInputStream());
            	out = new DataOutputStream(socket.getOutputStream());
                
    			String client_Request = in.readUTF();
    			System.out.println("El mensaje enviado por el cliente fue : " + client_Request);
    			
    			out.writeUTF("okay");
            }
			
			
		} catch (IOException e) {
		}
         
	}
	
	public static void main(String[] args) {
		Server s = new Server(8000);
	}
}
