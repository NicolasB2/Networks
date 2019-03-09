package broadcasting;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Client_Recive_Thread extends Thread {

	
	public Client cliente;
	
	public Client_Recive_Thread(Client cliente) {

		this.cliente = cliente;
	}
	
	
	public void run() {
		
		DataInputStream in;
		Socket socket ;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		try {
			
			while(cliente.isClientConected()) {
//				socket = cliente.getSocketReceive();
				socket = cliente.getSocketSend();
				in = new DataInputStream(socket.getInputStream());
				String mensajeDelServidor = in.readUTF();
				bw.write(mensajeDelServidor.replaceAll(";", " : ")+"\n");
				bw.flush();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
