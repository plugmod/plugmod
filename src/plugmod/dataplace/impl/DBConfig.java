package plugmod.dataplace.impl;

public class DBConfig {
	
	private final DBType type;
	private final String database;
	private final DBRemoteConfig remote;
	
	public DBConfig(DBType type, String database, DBRemoteConfig remote) {
		this.type = type;
		this.database = database;
		this.remote = remote;
	}
	
	/**
	 * @return the database type.
	 */
	public DBType getType() {
		return type;
	}

	/**
	 * @return the database name.
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @return the remote config.
	 */
	public DBRemoteConfig getRemote() {
		return remote;
	}

	public static class DBRemoteConfig {
		private final String host;
		private final int port;
		private final String username;
		private final String password;
		
		public DBRemoteConfig(String host, int port, String username, String password) {
			this.host = host;
			this.port = port;
			this.username = username;
			this.password = password;
		}

		/**
		 * @return the database host.
		 */
		public String getHost() {
			return host;
		}

		/**
		 * @return the database port.
		 */
		public int getPort() {
			return port;
		}

		/**
		 * @return the database username.
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @return the database password.
		 */
		public String getPassword() {
			return password;
		}
		
	}
	
}
