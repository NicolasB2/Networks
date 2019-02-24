package thread_pools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.concurrent.ConcurrentHashMap;

public class WorkedThread implements Runnable {

	private static ConcurrentHashMap<String, Float> map;
	private Socket clientsocket;

	static {
		map = new ConcurrentHashMap<String, Float>();
		map.put("Axle", 238.50f);
		map.put("Gear", 45.55f);
		map.put("Wheel", 86.30f);
		map.put("Rotor", 8.50f);
	}

	public WorkedThread(Socket clienSocket) {
		this.clientsocket = clienSocket;
	}

	@Override
	public void run() {
		System.out.println("Worked thread started");

		System.out.println("Client Thread Strated");
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			PrintStream out = new PrintStream(clientsocket.getOutputStream());

			String partName = bis.readLine();

			float price = map.get(partName);
			out.println(price);
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			System.out.println("Request for " + partName + "and returned a price of" + nf.format(price));
			clientsocket.close();
			System.out.println("CLient conection terminated");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Worker thread terminated");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
