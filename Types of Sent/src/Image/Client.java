package Image;

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
		
		reviceImage();	
	}

	
	public static void reviceImage() {

		try {

			java.io.InputStream inputStream = socket.getInputStream();
			System.out.println(inputStream);
			byte[] imageAr = new byte[62500];
			inputStream.read(imageAr);
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
			System.out.println(
					"Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
			ImageIO.write(image, "jpg", new File("C:\\Users\\erazo\\Desktop\\imagen2.png"));

		} catch (UnknownHostException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	}


	public static void main(String[] args) {
		Client client = new Client("localhost", 8000);
	}
}
