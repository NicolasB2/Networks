package broadcasting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Server_Send_Thread extends Thread{
	
	private Server server;
	
	public Server_Send_Thread(Server server) {
		
		this.server = server;
	}

	
	public void run() {
		
		try {
			

			DataInputStream in;
			DataOutputStream out;
			System.out.println("start send thread");
			while(server.isServerConected()) {
				
				if(server.isSendMulticast() && server.getMensajes().size() >0) {
					

					for (int i = 0; i < server.getSockets().size(); i++) {
						Socket actual  = server.getSockets().get(i);
						out = new DataOutputStream(actual.getOutputStream());
						for (int j = 0; j < server.getMensajes().size() ; j++) {
							String mensaje = server.getMensajes().get(j);
							out.writeUTF(mensaje);
						}
					}
					
					server.eraseMessages();
//					server.setSendMulticast(false);
			
				}
		}
	}
	catch (Exception e) {
			e.printStackTrace();
		}
		
				
				
		}
		
		
	}

