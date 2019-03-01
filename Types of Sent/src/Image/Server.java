package Image;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

import javax.*;
import javax.imageio.ImageIO;
import org.omg.CORBA.portable.InputStream;

public class Server {

	private static ServerSocket serverSocket;
	private DataInputStream in;
	private DataOutputStream out;

	public Server(int port) {

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Inicializando el servidor... [Ok].");

			send_image(serverSocket.accept());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void send_image(Socket socket) throws IOException {

		OutputStream outputStream = socket.getOutputStream();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedImage image = ImageIO.read(new File("C:\\Users\\erazo\\Desktop\\imagen.png"));
		ImageIO.write(image, "jpg", byteArrayOutputStream);

		byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
		System.out.println(byteArrayOutputStream.size());
		outputStream.write(byteArrayOutputStream.toByteArray());

		outputStream.close();
		socket.close();
	}

	public static void main(String[] args) {
		Server s = new Server(8000);
	}
}