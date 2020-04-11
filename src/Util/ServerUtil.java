package Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ServerUtil {
	private final static int PORT = 4444;
	private ServerSocket serverSocket = null;

	public ServerUtil() {
		createServer();
	}

	private void createServer() {
		System.out.println("[INFO]Server has opened!");
		try {
			serverSocket = new ServerSocket(PORT);
			while (true) {
				System.out.println("[INFO]Server waiting for connection!");
				Socket clientSocket = serverSocket.accept();// finite loop
				System.out.println("[INFO]One user is connected, his IP is " + clientSocket.getRemoteSocketAddress());
				new Thread(new Runnable() {
					@Override
					public void run() {
						execute(clientSocket);
					}
				}).start();
			}
		} catch (IOException e) {
			System.err.println("[ERROR]Could not listen on port: " + PORT);
			System.exit(1);
		}
	}

	private void execute(Socket socket) {
		System.out.println("[INFO]Server processes the request on thread " + Thread.currentThread().getId());
		try {
			InputStream in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			Object o = ois.readObject();

			MessageSender ms = (MessageSender) o;
			MessageSender msRet = handle(ms);

			OutputStream out = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(msRet);

			ois.close();
			oos.close();
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("[ERROR]An error occurred on the server processing method, " + e.getMessage());
		}
	}

	public abstract MessageSender handle(MessageSender ms);
}
