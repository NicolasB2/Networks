package broadcasting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private Server_Recive_Thread serverRecive;
	private Server_Send_Thread serverSend;

	private ArrayList<String> mensajes;
	private ArrayList<Socket> sockets;

	private static ServerSocket serverSocket;
	private boolean isServerConected;
	private boolean sendMulticast;

	public Server(int port,int number_clients) {

		try {

			System.out.println("Server on line");
			serverSocket = new ServerSocket(port);
			
			isServerConected = true;
			sendMulticast = false;
			
			sockets = new ArrayList<>();
			mensajes = new ArrayList<>();
			
			for (int i = 0; i <number_clients; i++) {
				sockets.add(serverSocket.accept());
				System.out.println("Client was connected");		
			}

			serverSend = new Server_Send_Thread(this);
			serverSend.start();

			serverRecive = new Server_Recive_Thread(this);
//			serverRecive.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Server_Recive_Thread getServer_Recive_Thread() {
		return serverRecive;
	}

	public void setHiloEscuchaUser(Server_Recive_Thread hiloEscuchaUser) {
		this.serverRecive = hiloEscuchaUser;
	}

	public Server_Send_Thread getHiloEnvio() {
		return serverSend;
	}

	public void setHiloEnvio(Server_Send_Thread hiloEnvio) {
		this.serverSend = hiloEnvio;
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

	public static void main(String[] args) {
		
		Server s = new Server(8000,2);

	}
}
