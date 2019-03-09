package Client;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import java.awt.Panel;
import java.awt.image.BufferedImage;

public class Client {

	public String serverIp;// server's IP address
	public int port;// Free port to establish connection
	private static Socket socket;// Socket which allowing connection

	
	/**
	 * Client´s constructor 
	 * @param serverIp : server's IP address
	 * @param port : Free port to establish connection
	 */
	public Client(String serverIp, int port) {

		this.port = port;
		this.serverIp = serverIp;

		try {
			socket = new Socket(serverIp, port);

		} catch (Exception e) {
			e.printStackTrace();
		}

		DataInputStream in;
		DataOutputStream out;

		try {

			// initialize data Input/Output Stream
			in = new DataInputStream(socket.getInputStream());

			String text = in.readUTF();

			if (text.equals("text")) {
				recivefile();
			}
			if (text.equals("image")) {
				reciveImage();
			}

		} catch (Exception e) {

		}

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

	/**
	 * Main method
	 * @param args
	 */ 
	public static void main(String[] args) {
		Client client = new Client("localhost", 8000);
	}
}
