package broadcasting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Recive_Thread extends Thread {

	private Server server;
private Socket socket;
	
	public Server_Recive_Thread(Server server,Socket socket) {

		this.server = server;
		this.socket = socket;
	}

	public void run() {

		try {

			DataInputStream in;
			System.out.println("start recive thread");
			while (server.isServerConected()) {

				in = new DataInputStream(socket.getInputStream());
				String mensajeObtenidoCliente = in.readUTF();
				System.out.println(mensajeObtenidoCliente);
				server.nuevoMensaje(mensajeObtenidoCliente);
				server.setSendMulticast(true);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
