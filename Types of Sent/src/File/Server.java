package File;


import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.Severity;
import org.omg.CORBA.portable.InputStream;

public class Server {

	private static ServerSocket serverSocket;
	private DataInputStream in;
	private DataOutputStream out;

	public Server(int port) {

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Inicializando el servidor... [Ok].");

			send_file(serverSocket.accept());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void send_file(Socket socket) throws IOException {

		File file = new File("F:\\ESCRITORIO\\prueba.txt ");

		long length = file.length();
		byte[] bytes = new byte[16 * 1024];
		FileInputStream in = new FileInputStream(file);
		OutputStream out = socket.getOutputStream();

		int count;
		while ((count = in.read(bytes)) > 0) {
			out.write(bytes, 0, count);
		}

	}


	public static void main(String[] args) {
		Server s = new Server(8000);
	}
}
