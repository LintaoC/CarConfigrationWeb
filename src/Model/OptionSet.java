package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {// Serializable need to be implement and import

	private static final long serialVersionUID = 1L;

	public class Option implements Serializable {

		private static final long serialVersionUID = 1L;

		private String optionName;
		private double price;

		public Option() {

		}

		public Option(String name, double price) {
			this.optionName = name;
			this.price = price;
		}

		protected double getPrice() {
			return price;
		}

		protected String getOptionName() {
			return optionName;
		}

		protected void setOptionName(String name) {
			optionName = name;
		}

		protected String getOptionSetName() {
			return opsName;
		}

		public String toString() {
			StringBuffer setString = new StringBuffer();
			setString.append("Option Name is ");
			setString.append(getOptionName()).append(" and Price is ").append(getPrice());
			String b = setString.toString();// convert back to String
			return b;
		}
	}

	private ArrayList<Option> opArray;
	private String opsName;
	private int opSize;
	private Option choice;

	public OptionSet() {

	}

	public OptionSet(String name) {
		opsName = name;
	}

	public OptionSet(int size, String name) {
		opSize = size;
		opArray = new ArrayList<Option>(size);
		opsName = name;
	}

	protected String getOptionSetName() {
		return opsName;
	}

	protected int getOptionSize() {
		opSize=opArray.size();
		return opSize;
	}

	protected ArrayList<Option> getOptionsArray() {
		return opArray;
	}

	protected Option getOption(int index) {
		return opArray.get(index);
	}

	protected Option getOptionChoice() {
		return choice;
	}

	protected void setName(String name) {
		opsName = name;
	}

	protected void setOptionSize(int size) {
		opSize = size;
	}

	protected void setOptionsArray(ArrayList<Option> option) {
		opArray = option;
		opSize = option.size();
	}

	protected void setOption(int index, Option option) {
		opArray.add(index, option);
	}

	protected void setOption(int index, double price, String optionName) {
		opArray.add(index, new Option(optionName, price));
	}

	protected void setOptionChoice(String name) {
		choice = findOption(name);
	}

	protected Option findOption(String name) {
		for (Option option : opArray) {
			if (option.getOptionName().equals(name)) {
				return option;
			}
		}
		return null;
	}

	public String toString() {
		StringBuffer setString = new StringBuffer();
		setString.append("OptionSet Name is ").append(getOptionSetName());
		setString.append(" and Option size is ").append(getOptionSize());
		String d = setString.toString();// convert back to String
		return d;
	}
}
