package Adapter;

public interface UpdateAuto {

	public void updateOptionSetName(String makeName, String ModelName, String OptionSetName, String newName);
	
	public void updateOptionPrice(String makeName, String ModelName, String OptionSetName, String OptionName, float newPrice);
}
