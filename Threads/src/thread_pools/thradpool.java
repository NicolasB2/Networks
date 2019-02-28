package thread_pools;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import sever_thread.simpleMultiThreadServer;

public class thradpool {

	public static void main(String[] args) {
		System.out.println("Thread pool seerver started");
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while(true) {
				System.out.println("Listening for a client connection");
				Socket socket = serverSocket.accept();
				System.out.println("Connected to a client");
				WorkedThread task = new WorkedThread(socket);
				System.out.println("Task created: "+ task);
				executor.execute(task);
			}
			
		} catch (Exception e) {
		}
		
		executor.shutdown();
		System.out.println("Thread pool server terminated");
		
	}

}
