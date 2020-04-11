package Client;

import java.util.Scanner;

import Model.Automobile;

public class SelectCarOption {
	private Automobile auto;
	private Scanner sc = new Scanner(System.in);

	public SelectCarOption(Automobile auto) {
		this.auto = auto;
	}

	public void showMenu() {
		System.out.println("Now you are configuring in " + auto.getModelName() + ", " + auto.getMake());
		System.out.println("[1]set option choice");
		System.out.println("[2]get option choice");
		System.out.println("[3]get option choice price");
		System.out.println("[4]get total price");
		System.out.println("[0]back");
	}

	public String getChoice() {
		System.out.print("Please enter your choice: ");
		return sc.nextLine();
	}

	public void execute(String choice) {
		switch (choice) {
		case "1": {
			System.out.print("Please enter the option set name: ");
			String optionSetName = sc.nextLine();
			System.out.print("Please enter the option name: ");
			String optionName = sc.nextLine();
			auto.setOptionChoice(optionSetName, optionName);
			break;
		}
		case "2": {
			System.out.print("Please enter the option set name: ");
			String optionSetName = sc.nextLine();
			String youChoiceName = auto.getOptionChoiceName(optionSetName);
			System.out.println("The option from " + optionSetName + " you choice is " + youChoiceName);
			break;
		}
		case "3": {
			System.out.print("Please enter the option set name: ");
			String optionSetName = sc.nextLine();
			int youChoicePrice = auto.getOptionChoicePrice(optionSetName);
			System.out.println("The option price from " + optionSetName + " you choice is " + youChoicePrice);
			break;
		}
		case "4": {
			int totalPrice = auto.getTotalPrice();
			System.out.print("The total price you choice is " + totalPrice);
			break;
		}
		}
	}

	public void start() {
		showMenu();
		String choice = null;
		while (!(choice = getChoice()).equals("0")) {
			execute(choice);
			System.out.println();
			showMenu();
		}
	}

}
