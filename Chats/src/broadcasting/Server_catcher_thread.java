package broadcasting;

import java.io.*;
import java.net.*;

public class Server_catcher_thread extends Thread {

	private Socket s;
	private Socket[] Sockets;
	private DataOutputStream out;
	private DataInputStream in;

	public Server_catcher_thread(Socket s, Socket[] sockets, DataInputStream in, DataOutputStream out) {
		this.s = s;
		this.Sockets = sockets;
		this.out = out;
		this.in = in;
	}

	@Override
	public void run() {

		
		boolean space = false;
		int x = -1;

		for (int i = 0; i < Sockets.length && !space; i++) {
			if (Sockets[i] == null) {
				Sockets[i] = s;
				space = true;
				x = i;
			}
		}
		
		while (true) {
			try {

				for (int i = 0; i < Sockets.length && space; i++) {
					if (i != x) {
						String text = in.readUTF();
						out = new DataOutputStream(Sockets[i].getOutputStream());
						out.writeUTF(text);
						System.out.println("El mensaje enviado por el cliente fue : " + text);
					}

				}

				if (!space) {
					out.writeUTF("sorry the server its full");
				}

			} catch (Exception e) {

			}
		}
	}
}
