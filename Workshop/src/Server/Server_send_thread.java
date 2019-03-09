package Server;

import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Server_send_thread extends Thread {

	private Socket socket; // Socket with allowing connection
	private String control; // String that determines what type of service
	private DataOutputStream out; // data otput
	private DataInputStream in; // data input

	/**
	 * Server_send_thread´s constructor
	 * 
	 * @param socket : socket with the conection between client and server
	 */
	public Server_send_thread(Socket socket) {
		this.control = "";
		this.socket = socket;

	}

	/**
	 * Method that runs when the thread it starts
	 */
	@Override
	public void run() {
		System.out.println("run the thread");
		boolean x = true;

		try {
			out = new DataOutputStream(socket.getOutputStream());
			while (x) {

				if (control.equals("text")) {
					x = false;
					out.writeUTF("text");
					send_file();
				}

				if (control.equals("image")) {
					x = false;
					out.writeUTF("image");
					send_image();

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * method that send a file .txt to the client
	 */
	private void send_file() {
		System.out.println("send file");
		try {

			File file = new File("src/sources/prueba.txt");

			long length = file.length();
			byte[] bytes = new byte[16 * 1024];
			FileInputStream in = new FileInputStream(file);
			OutputStream out = socket.getOutputStream();

			int count = in.read(bytes);
			while (count > 0) {
				out.write(bytes, 0, count);
				count = in.read(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * method that send a image to the client
	 */
	private void send_image() {
		System.out.println("send image");
		try {

			OutputStream outputStream = socket.getOutputStream();

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			BufferedImage image = ImageIO.read(new File("src/sources/imagen.png"));
			ImageIO.write(image, "jpg", byteArrayOutputStream);

			byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
			outputStream.write(byteArrayOutputStream.toByteArray());

			outputStream.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * change the control string
	 * @param x: String with the new comand
	 */
	public void setControl(String x) {
		control = x;
	}
}