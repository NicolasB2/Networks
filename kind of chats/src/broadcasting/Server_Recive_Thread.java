package broadcasting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Recive_Thread extends Thread {

	private Server server;

	public Server_Recive_Thread(Server server) {

		this.server = server;
	}

	public void run() {

		try {

			DataInputStream in;

			while (server.isServerConected()) {

				if (server.isSendMulticast()) {
					Socket socketReceived = server.getServerSocketReceived().accept();
					in = new DataInputStream(socketReceived.getInputStream());
					String mensajeObtenidoCliente = in.readUTF();
					server.nuevoMensaje(mensajeObtenidoCliente);
					Thread.sleep(1);
					server.setSendMulticast(true);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
