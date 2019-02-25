package broadcasting;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.net.Socket;

public class Write_client_thread extends Thread {

	private DataOutputStream out;

	public Write_client_thread(DataOutputStream out) {
//		this.socket = socket;
		this.out = out;
	}

	public void run() {
		
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String text = br.readLine();
			out.writeUTF(text);
			
		} catch (IOException e) {
			
		}
		
		
    }
}