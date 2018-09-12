/** written with inspiration from: https://github.com/xerial/sqlite-jdbc
 * 
 */
package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author cw222kq
 *
 */
public class DB {

	Connection connection;
	Statement statement;
	ResultSet rs;
	
	public DB() {
		connection = null;
	}
	public void connect() {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:database/boatMembers.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		createTables(statement);	
	}
	private void createTables(Statement statment){
		// create tables if they not already exists
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Member(id INTEGER PRIMARY KEY AUTOINCREMENT, SSN TEXT NOT NULL, name TEXT NOT NULL, password TEXT NOT NULL)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Boat(id INTEGER PRIMARY KEY AUTOINCREMENT, size INTEGER NOT NULL, type TEXT NOT NULL, member_id INTEGER NOT NULL, FOREIGN KEY(member_id) REFERENCES Member(id) ON UPDATE CASCADE)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Image(id INTEGER PRIMARY KEY AUTOINCREMENT, path TEXT NOT NULL, boat_id INTEGER NOT NULL, FOREIGN KEY(boat_id) REFERENCES boat(id) ON UPDATE CASCADE)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//********** QUERIES **********
	// INPUT VALUES INTO THE TABLES
	// Inserts data into the different tables that is in the db
		public void insert(model.Member m_member, model.Boat m_boat,String table){
			try{
				connection.setAutoCommit(false);
				// member
				if(table == "member"){
					//if(isInTheDB) {return;}
					if(m_member.getName() == null){return;}
					statement.executeUpdate("INSERT INTO Member(SSN,name,password)VALUES(" + "'" + m_member.getSSN() + "'" +  ", '" + m_member.getName() + "'" +  ", '" + m_member.getPassword() + "'" + ")");
				}
				// boat
				if(table == "boat"){
					//if(isInTheDB) {return;}
					if(m_boat.getType() == null){return;}
					statement.executeUpdate("INSERT INTO Boat(size,type) VALUES(" + "'" + m_boat.getSize() + "'" + ", '" + m_boat.getType() + "'" +")");
				}
				// image
				if(table == "image"){
					if(m_boat.getImagePath() == null){return;}
					statement.executeUpdate("INSERT INTO Image(path, boat_id) VALUES(" + "'" + m_boat.getImagePath() + "'" + ", " + m_boat.getId() + ")" );
				}
				//isInTheDB = false;
				connection.commit(); 
			}catch(Exception e){
				e.printStackTrace();	
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// METOD SOM SÄGER ATT COMMMITEN LYCKADES
		}
	
	// Closes the result set and the connection. Executed when user wants to quit the application
	public void closeConnection(){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
