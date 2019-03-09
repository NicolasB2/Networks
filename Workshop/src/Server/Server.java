package Server;

import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Severity;
import javax.swing.JFileChooser;

import org.omg.CORBA.portable.InputStream;

public class Server {

	private static ServerSocket serverSocket;// Socket which allowing connection whit clients
	private static Server_send_thread[] threads;// Array with the threads

	/**
	 * Server's constructor
	 * @param port : number of free port to establish connection
	 */
	public Server(int port) {
		threads = new Server_send_thread[10];

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Inicializando el servidor... [Ok].");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("________________________________________________");
			System.out.println("Please enter :\n\tmulti if you want send a file with multicasting"
					+ "\n\tbroad if you want send a image with broadcasting");
			System.out.println("________________________________________________");
			String comand = br.readLine();
			System.out.println("waiting for clients.......");
			System.out.println("________________________________________________");

			for (int i = 0; i < threads.length; i++) {
				Socket s = serverSocket.accept();
				Server_send_thread sst = new Server_send_thread(s);
				threads[i] = sst;
				System.out.println("new client online");
			}

			if (comand.equals("broad")) {
				broadCasting();
			}
			if (comand.equals("multi")) {
				multicasting();
			} else {
				System.out.println("You enter an invalied comand");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method that runs Broadcasting functionality
	 */
	public void broadCasting() {

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
			threads[i].setControl("image");
		}
	}

	/**
	 * Method that runs Multicasting functionality
	 */
	public void multicasting() {
		for (int i = 0; i < threads.length / 2; i++) {
			threads[i].start();
			threads[i].setControl("text");
		}
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		Server s = new Server(8000);
	}

}