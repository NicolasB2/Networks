package File;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Client {

	public static final String LOCAL_HOST = "localhost";
	public String serverIp;
	public int port;
	private static Socket socket;

	public Client(String serverIp, int port) {
		
		this.port = port;
		this.serverIp = serverIp;
		
		try {
			System.out.println("**Cliente disponible para ser atendido**");
			socket = new Socket(serverIp, port);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		recivefile();
	}

	public static void recivefile() {
		try {
			InputStream in = null;
			OutputStream out = null;

			in = socket.getInputStream();
			out = new FileOutputStream("F:\\ESCRITORIO\\prueba8.txt ");
			
			byte[] bytes = new byte[16 * 1024];

			
			int count =in.read(bytes);
			while (count  > 0) {
				out.write(bytes, 0, count);
				count =in.read(bytes);
			}

			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	public static void main(String[] args) {
		Client client = new Client("localhost", 8000);
	}
}

