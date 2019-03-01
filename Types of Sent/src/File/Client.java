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

	/**
	 * Method that allows the client to receive an txt file
	 */ 	
	public static void recivefile() {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
			
			
			System.out.println("recive file");
			InputStream in = null;
			OutputStream out = null;
			
			System.out.println("Enter the name do you want to save .txt file");
			String name = br.readLine();
			
			
			in = socket.getInputStream();
			out = new FileOutputStream("src/sources/"+name+".txt");

			byte[] bytes = new byte[16 * 1024];

			int count;
			while ((count = in.read(bytes)) > 0) {
				out.write(bytes, 0, count);
			}

		} catch (IOException ex) {
			System.out.println();
		}
	}


	public static void main(String[] args) {
		Client client = new Client("localhost", 8000);
	}
}

