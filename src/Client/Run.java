package Client;

import java.util.Properties;
import java.util.Scanner;

import Model.Automobile;
import Util.ClientUtil;
import Util.MessageSender;

public class Run {
	private ClientUtil client;
	private Scanner sc = new Scanner(System.in);

	public void showMenu() {
		System.out.println("Welcome to Car Configuration System");
		System.out.println("[1]upload properties file");
		System.out.println("[2]get available automobiles list");
		System.out.println("[3]configure a car");
		System.out.println("[0]exit");
	}

	public String getChoice() {
		System.out.print("Please enter your choice: ");
		return sc.nextLine();
	}

	public void execute(String choice) {
		switch (choice) {
		case "1": {
			CarModelOptionsIO carModelOptionsIO = new CarModelOptionsIO();
			System.out.println("Please enter the properties name: ");
			String filename = sc.nextLine();
			Properties prop = carModelOptionsIO.uploadPropertiesFile(filename);
			client.post("buildModel", prop);
			break;
		}
		case "2": {
			client.post("getModelList", "all");
			break;
		}
		case "3": {
			System.out.print("Please enter the make name: ");
			String makeName = sc.nextLine();
			System.out.print("Please enter the model name: ");
			String modelName = sc.nextLine();
			client.post("getModel", makeName + "-" + modelName);
			break;
		}
		default:
			System.out.println("This function was not found!");
			break;
		}
	}

	public void handle(MessageSender ms) {
		String code = ms.getPostType();
		Object object = ms.getContent();
		switch (code) {
		case "buildModel":
			System.out.println(object);
			break;
		case "getModelList":
			System.out.print(object);
			break;
		case "getModel":
			if (object == null) {
				break;
			}
			Automobile am = (Automobile) object;
			am.print();
			System.out.println();
			SelectCarOption sco = new SelectCarOption(am);
			sco.start();
			break;
		default:
			break;
		}
	}

	public ClientUtil getClient() {
		return client;
	}

	public void setClient(ClientUtil client) {
		this.client = client;
	}
	public void closeScanner(){
		sc.close();
}
	// main
	/*public static void main(String[] args) {
		Run r = new Run();
		r.setClient(new ClientUtil() {
			@Override
			public void handle(MessageSender ms) {
				r.handle(ms);
			}
		});
		r.showMenu();
		String choice = null;
		while (!(choice = r.getChoice()).equals("0")) {
			r.execute(choice);
			System.out.println();
			r.showMenu();
		}
		r.sc.close();
		System.out.println("Goodbye!");
	}*/
}
