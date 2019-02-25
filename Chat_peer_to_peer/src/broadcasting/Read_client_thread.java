package broadcasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Read_client_thread extends Thread {

	private DataInputStream in;

	public Read_client_thread(DataInputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		
		System.out.println("listo para leer");
		try {
			while (true) {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				String mensajeDelServidor = in.readUTF();
				bw.write("la frase encriptada es: " + mensajeDelServidor);
			}
			
			
		} catch (IOException e) {
			
		}
		
		
    }
}
