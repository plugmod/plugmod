package plugmod.dataplace.impl;

import java.util.logging.Level;

import org.bukkit.Bukkit;

/***
 * Represents the different database types the datastore can implement.
 */
public enum DBType {
	/**
	 * Simple (and slow) flatfile relational database, 
	 * default because it requires no connection.
	 */
	SQLITE(true, true), 
	/**
	 * Remote relational database, recommended option.
	 */
	MYSQL(false, true), 
	/**
	 * Remote non-relational database, experimental.
	 */
	CASSANDRA(false, false);
	
	private final boolean flatfile;
	private final boolean relational;
	
	private DBType(boolean flatfile, boolean relational) {
		this.flatfile = flatfile;
		this.relational = relational;
	}
	
	/**
	 * @return true, if the database uses a flatfile for storage.
	 */
	public boolean isFlatFile() {
		return flatfile;
	}
	
	/**
	 * @return true, if the database is relational.
	 */
	public boolean isRelational() {
		return relational;
	}
	
	/**
	 * Gets a database type from string, 
	 * returns default (sqlite) and logs error with malformatted type.
	 * @param dbType
	 * @return the DBType fromString.
	 */
	public static DBType fromString(String dbType) {
		try {
			return DBType.valueOf(dbType.toUpperCase());
		} catch (IllegalArgumentException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Error - Unsupported database type - " + dbType, e);
			return DBType.SQLITE;
		}
		
	}
	
}
