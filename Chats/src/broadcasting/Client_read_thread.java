package broadcasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Client_read_thread extends Thread {

	private DataInputStream in;


	public Client_read_thread(DataInputStream in) {
		this.in = in;
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
