package plugmod.dataplace;

public class DataPlace {

	private DataStore store;
	private final DataPlace INSTANCE = new DataPlace();
	private DataPlace() { }
	
	public DataPlace getInstance() {
		return INSTANCE;
	}
	
	public void init() {
		
	}
	
	public DataStore getStore() {
		return store;
	}
	
}
