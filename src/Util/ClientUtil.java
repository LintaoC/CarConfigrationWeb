package Util;

import java.net.*;
import java.io.*;

public abstract class ClientUtil {
	private final static String IP = "127.0.0.1";
	private final static int PORT = 4444;

	public  void post(String code, Object data) {
		try {
			Socket socket = new Socket(IP, PORT);

			OutputStream out = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(new MessageSender(code, data));
			socket.shutdownOutput();

			InputStream in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			Object o = ois.readObject();
			MessageSender ms = (MessageSender) o;
			handle(ms);

			oos.close();
			ois.close();
			in.close();
			out.close();
			socket.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("[ERROR]Connection failed!");
			System.exit(1);
		}
	}

	public abstract void handle(MessageSender ms) ;
}
