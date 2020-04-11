package Adapter;

public interface ChooseAuto {

	String getOptionChoice(String makeName, String ModelName, String setName);

	int getOptionChoicePrice(String makeName, String ModelName, String setName);

	void setOptionChoice(String makeName, String ModelName, String setName, String optionName);

	int getTotalPrice(String makeName, String ModelName);
}
