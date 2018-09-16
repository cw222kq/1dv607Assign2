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
	ResultSet rs2;
	
	
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
		setConfig();
		createTables(statement);	
	}
	//********** QUERIES **********
	private void createTables(Statement statment){
		// create tables if they not already exists
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Member(id INTEGER PRIMARY KEY AUTOINCREMENT, SSN TEXT NOT NULL, name TEXT NOT NULL, password TEXT NOT NULL)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Boat(id INTEGER PRIMARY KEY AUTOINCREMENT, size INTEGER NOT NULL, type TEXT NOT NULL, member_id INTEGER NOT NULL, FOREIGN KEY(member_id) REFERENCES Member(id) ON UPDATE CASCADE ON DELETE CASCADE)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Image(id INTEGER PRIMARY KEY AUTOINCREMENT, path TEXT NOT NULL, boat_id INTEGER NOT NULL, FOREIGN KEY(boat_id) REFERENCES boat(id) ON UPDATE CASCADE ON DELETE CASCADE)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	// Commit transaction
	public void commitTransaction(){
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
	}
	
	// INPUT VALUES INTO THE TABLES
	// Inserts data into the different tables that is in the db
	public void insert(model.Member m_member, model.Boat m_boat,String table){
		try{
			//connection.setAutoCommit(false);
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
				statement.executeUpdate("INSERT INTO Boat(size,type, member_id) VALUES(" + "'" + m_boat.getSize() + "'" + ", '" + m_boat.getType() + "'" + ", '" + m_member.getId() + "'" + ")");
			}
			// image
			if(table == "image"){
				if(m_boat.getImagePath() == null){return;}
				statement.executeUpdate("INSERT INTO Image(path, boat_id) VALUES(" + "'" + m_boat.getImagePath() + "'" + ", " + m_boat.getId() + ")" );
			}
			//isInTheDB = false;
			// commiten och rollbacken i en egen metod?
		//	connection.commit(); 
		}catch(Exception e){
			e.printStackTrace();	
		/*	try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}*/
		}
		// METOD SOM SÄGER ATT COMMMITEN LYCKADES BEHÖVS
	}
	// FETCH DATA FROM DB TO SUPPORT THE INSERT METODS
	// Method that returns the member id from the social security number TROR DENNA KAN TAS BORT!!!!!!!!!!!!!
	public int getMemberId(String SSN) { 
		rs = null;
		int id = 0;
		try {
			rs = statement.executeQuery("SELECT id FROM Member WHERE SSN = '" + SSN + "'");
			while(rs.next()){ 
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;		
	}
	// Method that returns the latest added boats id of the specific member (used when the user put in values into the image table)
	public int getMembersLatestAddedBoatId(int memberId){
		rs = null;
		int boatId = 0;
		try {
			rs = statement.executeQuery("SELECT MAX(id) FROM Boat WHERE member_id = '" + memberId + "'");
			while(rs.next()){ 
				boatId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boatId;	
	}
	// GETTING DATA FROM THE DB
	// Get compact list (name, member id and number of boats of all members)
	public ResultSet getCompactList() {
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Member.name AS 'member name', Member.id AS 'member id', COUNT(Boat.member_id) AS 'number of boats' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) GROUP BY Boat.member_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;	
	}
	// Get verbose list (name, SSN, member id and boats with boat information of all members)
	public ResultSet getVerboseList() {
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Member.name AS 'member name', Member.ssn AS 'social security number', Member.id AS 'member id', Boat.id AS 'boat id', Boat.size AS 'boat size', Boat.type AS 'boat type', Image.path AS 'image path' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) LEFT JOIN Image ON (Boat.id = Image.boat_id)");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;	
	}
	// Look at a specific members information
	public ResultSet getMemberInformation(String SSN){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Member.name AS 'member name', Member.ssn AS 'social security number', Member.id AS 'member id', Member.password AS 'member password', Boat.id AS 'boat id', Boat.size AS 'boat size', Boat.type AS 'boat type', Image.path AS 'image path' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.SSN = " + "'" + SSN + "'" );
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;		
	}
	// Delete a member
	public void deleteMember(String SSN){
		try {
			statement.executeUpdate("DELETE FROM Member WHERE SSN =" + "'" + SSN + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
	// KONTROLL METOD TAS BORT I SLUTGILTIGA VERSIONEN HÄMTAR UT INNEHÅLLET I BOAT TABELLEN ******************************************************
	public ResultSet getBoat(){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT id, size, type, member_id FROM Boat");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;		
	}
	// KONTROLL METOD TAS BORT I SLUTGILTIGA VERSIONEN HÄMTAR UT INNEHÅLLET I IMAGE TABELLEN **********************************************************
	public ResultSet getImage(){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT id, path, boat_id FROM Image");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;		
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
