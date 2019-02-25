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
	private BufferedWriter bw;

	public Read_client_thread(DataInputStream in) {
		this.in = in;
		this.bw = bw;
	}

	@Override
	public void run() {
		
		System.out.println("listo para leer");
		try {
			while (true) {
				String text = in.readUTF();
				System.out.println(text);
			}
			
			
		} catch (IOException e) {
			
		}
		
		
    }
}
