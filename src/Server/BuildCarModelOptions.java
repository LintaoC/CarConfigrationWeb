package Server;

import java.util.Properties;

import Model.Automobile;
import Util.FileIO;

public class BuildCarModelOptions {
	private final static FileIO fileIO = new FileIO();

	public Automobile parseAuto(Properties prop) {
		return fileIO.parseProperties(prop);
	}

	public void addModelToSystem(AutoServer proxy, Automobile auto) {
		proxy.addModelToSystem(auto);
	}
}
