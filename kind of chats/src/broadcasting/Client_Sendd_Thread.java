package broadcasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client_Sendd_Thread extends Thread {

	public Client client;

	public Client_Sendd_Thread(Client cliente) {

		this.client = cliente;

	}

	public void run() {

		try {

			DataOutputStream out;

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String mensaje = "";
			Socket socket;

			while (client.isClientConected()) {

				socket = client.getSocketSend();
				out = new DataOutputStream(socket.getOutputStream());
				mensaje += br.readLine();
				out.writeUTF(mensaje);

			}
		} catch (Exception e) {

		}

	}

}
