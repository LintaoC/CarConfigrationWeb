package Model;

public interface CollectionInterface {

	public void put(String name, Automobile value);

	public Automobile get(String name);

	public void update(String name, Automobile value);

	public void delete(String name);

}
