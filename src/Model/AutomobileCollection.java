package Model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class AutomobileCollection implements CollectionInterface {
	private LinkedHashMap<String, Automobile> autos = new LinkedHashMap<String, Automobile>();

	public AutomobileCollection() {
		super();
	}

	public void put(String name, Automobile value) {
		autos.put(name, value);
	}

	public Automobile get(String name) {
		return autos.get(name);
	}

	public void update(String name, Automobile value) {
		autos.put(name, value);
	}

	public void delete(String name) {
		autos.remove(name);

	}

	public Iterator<Entry<String, Automobile>> iterator() {
		return autos.entrySet().iterator();
	}

}
