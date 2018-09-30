/** written with inspiration from: https://github.com/xerial/sqlite-jdbc
 * 
 */
package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author cw222kq
 *
 */
public class DB {

	Connection connection;
	Statement statement;
	
	public DB() {
		this.connection = null;
	}
	public Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			
			this.connection = DriverManager.getConnection("jdbc:sqlite:database/boatMembers.db");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			this.statement = this.connection.createStatement();
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		setConfig();
		
		return this.connection;
	}
	private void setConfig(){
		
		org.sqlite.SQLiteConfig config = new org.sqlite.SQLiteConfig();
	    config.enforceForeignKeys(true);
	    try {
			statement.execute("PRAGMA foreign_keys = ON");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	// Start transaction
	public void startTransaction(){
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	// Commit transaction
	public void commitTransaction(){
		try {
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
	}
	// Closes the result set and the connection. Executed when user wants to quit the application
	public void closeConnection(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
