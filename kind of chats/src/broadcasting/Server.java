package broadcasting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private Server_Recive_Thread hiloEscuchaUser;
	private Server_Send_Thread hiloEnvio;

	private ArrayList<String> mensajes;
	private ArrayList<Socket> sockets;
	

	private static ServerSocket serverSocket;
	private boolean isServerConected;
	private boolean sendMulticast;

	public Server(int port) {

		try {

			System.out.println("::SERVIDOR CHAT ICESI :ON ::");

			isServerConected = true;


			serverSocket = new ServerSocket();

			sockets = new ArrayList<>();

			mensajes = new ArrayList<>();

			hiloEnvio = new Server_Send_Thread(this);
			hiloEnvio.start();

			hiloEscuchaUser = new Server_Recive_Thread(this);
			hiloEscuchaUser.start();

			sendMulticast = false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Server_Recive_Thread getHiloEscuchaUser() {
		return hiloEscuchaUser;
	}

	public void setHiloEscuchaUser(Server_Recive_Thread hiloEscuchaUser) {
		this.hiloEscuchaUser = hiloEscuchaUser;
	}

	public Server_Send_Thread getHiloEnvio() {
		return hiloEnvio;
	}

	public void setHiloEnvio(Server_Send_Thread hiloEnvio) {
		this.hiloEnvio = hiloEnvio;
	}

	public static ServerSocket getServerSocketReceived() {
		return serverSocket;
	}


	public ArrayList<Socket> getSockets() {
		return sockets;
	}

	public void setSockets(ArrayList<Socket> sockets) {
		this.sockets = sockets;
	}

	public boolean isServerConected() {
		return isServerConected;
	}

	public void setServerConected(boolean isServerConected) {
		this.isServerConected = isServerConected;
	}

	public boolean isSendMulticast() {
		return sendMulticast;
	}

	public void setSendMulticast(boolean sendMulticast) {
		this.sendMulticast = sendMulticast;
	}

	public ArrayList<String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ArrayList<String> mensajes) {
		this.mensajes = mensajes;
	}

	public void eraseMessages() {

		mensajes = new ArrayList<>();

	}

	public void nuevoMensaje(String mensajeObtenidoCliente) {

		mensajes.add(mensajeObtenidoCliente);

	}

}
