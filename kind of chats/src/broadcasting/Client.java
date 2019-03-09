package broadcasting;

import java.net.Socket;
import java.util.Scanner;

public class Client {

	public String serverIp;// server's IP address
	public int port;// Free port to establish connection
	private static Socket socket;// Socket which allowing connection

	private boolean isClientConected;

	private Client_Recive_Thread hiloAtentoServer;

	private Client_Sendd_Thread hiloEnvioMensajes;

	public Client(String serverIp, int port) {

		this.port = port;
		this.serverIp = serverIp;

		try {
			System.out.println("Welcome to the nicolás chat");
			socket = new Socket(serverIp, port);
			isClientConected = true;
			hiloAtentoServer = new Client_Recive_Thread(this);
			hiloAtentoServer.start();
			hiloEnvioMensajes = new Client_Sendd_Thread(this);
			hiloEnvioMensajes.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Socket getSocketSend() {
		return socket;
	}

	public static void setSocketSend(Socket socketSend) {
		Client.socket = socketSend;
	}

	public Client_Recive_Thread getHiloAtentoServer() {
		return hiloAtentoServer;
	}

	public void setHiloAtentoServer(Client_Recive_Thread hiloAtentoServer) {
		this.hiloAtentoServer = hiloAtentoServer;
	}

	public Client_Sendd_Thread getHiloEnvioMensajes() {
		return hiloEnvioMensajes;
	}

	public void setHiloEnvioMensajes(Client_Sendd_Thread hiloEnvioMensajes) {
		this.hiloEnvioMensajes = hiloEnvioMensajes;
	}

	public boolean isClientConected() {
		return isClientConected;
	}

	public void setClientConected(boolean isClientConected) {
		this.isClientConected = isClientConected;
	}

	public static void main(String[] args) {

		Client c = new Client("localhost", 8000);

	}

}
