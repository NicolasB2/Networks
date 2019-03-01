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
		
		reciveImage();	
	}

	
	/**
	 * Method that allows the client to receive an image
	 */ 
	public static void reciveImage() {

		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
			System.out.println("Enter the name do you want to save image");
			String name = br.readLine();
			
			
			System.out.println("recive image");
			java.io.InputStream inputStream = socket.getInputStream();
			byte[] imageAr = new byte[62500];
			inputStream.read(imageAr);
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
			System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
			ImageIO.write(image, "jpg", new File("src/sources/"+name+".png"));

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		Client client = new Client("localhost", 8000);
	}
}
