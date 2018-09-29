/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import storage.DB;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author cw222kq
 *
 */
public class DAO {

	/**
	 * 
	 */
	public DAO() {
		connect = s_db.getConnection();
		try {
			statement = connect.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			state = connect.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		createTables();
	}
	
	private Connection connect;
	private Statement statement;
	private Statement state;
	private ResultSet rs;
	private DB s_db = new DB();
	
	// INPUT VALUES INTO THE TABLES
	// Inserts data into the different tables that is in the db
	public void insert(model.Member m_member, model.Boat m_boat, String table){ 
		try{
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
		
		}catch(Exception e){
			e.printStackTrace();	
		}
		// METOD SOM SÄGER ATT COMMMITEN LYCKADES BEHÖVS
	}
	private void createTables(){
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
	// FETCH DATA FROM DB TO SUPPORT THE INSERT METODS
	// Method that returns the member id from the social security number
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
			rs =statement.executeQuery("SELECT Member.name AS 'member name', Member.ssn AS 'social security number', Member.id AS 'member id', Member.password AS 'member password', Boat.id AS 'boat id', Boat.size AS 'boat size', Boat.type AS 'boat type', Image.path AS 'image path' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.SSN = " + "'" + SSN + "'" ); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;		
	}
	// Change membersInformation 
	// Getting the members information from the ssn
	public ResultSet getMembersInformation(model.Member m_member){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Member.id AS 'member id', Member.ssn AS 'social security number', Member.name AS 'member name', Member.password AS 'member password' FROM Member WHERE Member.SSN = " + "'" + m_member.getSSN()+ "'" );  
			m_member.setId(rs.getInt("member id"));
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
	// Delete a boat
	public void deleteBoat(int id){
		try {
			statement.executeUpdate("DELETE FROM Boat WHERE id =" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}
	// Getting the members boats from the db when the user input members SSN
	public ResultSet getMembersBoats(String SSN){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Boat.id, Boat.size, Boat.type, Image.path FROM Boat LEFT JOIN Member ON (Boat.member_id = Member.id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.SSN =" + "'" + SSN + "'"); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;		
	}
	// Getting a specific boat
	public ResultSet getASpecificBoat(int id){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Boat.id, Boat.size, Boat.type, Image.path FROM Boat LEFT JOIN Member ON (Boat.member_id = Member.id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.id =" + id);  
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;	
	}
	// Update the members information
	public void updateMemberInformation(model.Member m_member){
		try {
			statement.executeUpdate("UPDATE Member SET SSN ="+ "'" + m_member.getSSN() + "', name =" + "'" + m_member.getName() + "', password =" + "'" + m_member.getPassword()+ "' WHERE id =" + m_member.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
	// Update the boats information
	public void updateBoatInformation(model.Boat m_boat){
		try {
			statement.executeUpdate("UPDATE Boat SET size =" + m_boat.getSize() + ", type =" + "'" + m_boat.getType() + "' WHERE id=" + m_boat.getId());
			state.executeUpdate("UPDATE Image SET path =" + "'" + m_boat.getImagePath()+ "' WHERE boat_id =" + m_boat.getId()); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
	public void startTransaction(){
		s_db.startTransaction();
	}
	public void commitTransaction(){
		s_db.commitTransaction();
	}
	public void closeConnection(){
		s_db.closeConnection();
	}

}
