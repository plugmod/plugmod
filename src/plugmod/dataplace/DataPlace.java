package plugmod.dataplace;

import plugmod.dataplace.impl.DBConfig;

public class DataPlace {

	private static long apiVersion = 1;
	public static long getApiVersion() { return apiVersion; }
	
	private static final DataPlace INSTANCE = new DataPlace();
	private DataPlace() { }
	public static DataPlace getInstance() {
		return INSTANCE;
	}
	
	private DataStore store;
	private DBConfig dbConfig;
	
	public void init(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
	}
	
	public DataStore getStore() {
		return store;
	}
	
	/**
	 * @return the database config.
	 */
	public DBConfig getDBConfig() {
		return dbConfig;
	}
	
}
