package Server;

import Model.Automobile;

public interface AutoServer {
	void addModelToSystem(Automobile auto);

	String getModelNameList();

	Automobile getAuto(String makeName, String modelName);
}
