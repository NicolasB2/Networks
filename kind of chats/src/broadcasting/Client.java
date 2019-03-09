package broadcasting;

import java.net.Socket;

public class Client {
	
	
	/*
	 * 
	 * Direccion local de la maquina
	 */
	public static final String LOCAL_HOST = "localhost";
	/**
	 * Puerto por donde se establecera la conexion
	 */
	public static final int PORT_SEND = 8000;
	
//	public static final int PORT_RECEIVE = 8500;
	
	/**
	 * Socket que permitira la conexion con el servidor
	 */
	private static Socket socketSend;
	
//	private static Socket socketReceive;
	
	/**
	 * El nombre con la que el cliente ingresara al chat
	 */
	private String nickName;
	/**
	 * La direccion Ip con la que el cliente ingresara al chat 
	 */
	private String dirIp;
	
	
	private boolean isClientConected;
	
	private Client_Recive_Thread  hiloAtentoServer;
	
	private Client_Sendd_Thread hiloEnvioMensajes;
	
	
	public Client(String nickName) throws Exception{
		
		try {
			socketSend = new Socket(LOCAL_HOST, PORT_SEND);
//			socketReceive = new Socket(LOCAL_HOST, PORT_RECEIVE);
			isClientConected = true;
			hiloAtentoServer= new Client_Recive_Thread(this);
			hiloAtentoServer.start();
			hiloEnvioMensajes = new Client_Sendd_Thread(this);
			hiloEnvioMensajes.start();
			this.nickName = nickName;
			
		} catch (Exception e) {
			
			throw new Exception("Error al crear el socket del cliente");
		}
		
	}


	
	

	public static Socket getSocketSend() {
		return socketSend;
	}





	public static void setSocketSend(Socket socketSend) {
		Client.socketSend = socketSend;
	}





//	public static Socket getSocketReceive() {
//		return socketReceive;
//	}
//
//
//
//
//
//	public static void setSocketReceive(Socket socketReceive) {
//		Client.socketReceive = socketReceive;
//	}





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





	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getDirIp() {
		return dirIp;
	}


	public void setDirIp(String dirIp) {
		this.dirIp = dirIp;
	}


	public boolean isClientConected() {
		return isClientConected;
	}


	public void setClientConected(boolean isClientConected) {
		this.isClientConected = isClientConected;
	}
	
	
	
	
	

}
