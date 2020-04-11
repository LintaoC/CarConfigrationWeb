package Client;

import java.util.Properties;

import Util.FileIO;

public class CarModelOptionsIO {
	private final static FileIO fileIO = new FileIO();

	public Properties uploadPropertiesFile(String filename) {
		return fileIO.getpropertiesFromFile(filename);
	}
}
