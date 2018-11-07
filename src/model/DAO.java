/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import storage.DB;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author cw222kq
 *
 */
class DAO {

	public DAO() {
		connect = m_db.getConnection();
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
		
		//tillagt nu i kompleteringen
		listOfMembers = new ArrayList();
		
	}
	
	private Connection connect;
	private Statement statement;
	private Statement state;
	private ResultSet rs;
	private DB m_db = new DB();
	private Member m_member = new Member();
	
	//tillagt nu i kompletteringen
	private ArrayList listOfMembers;
				
	//////////////////////////////////////
	
	// INPUT VALUES INTO THE TABLES
	public void insertMember(model.Member a_member){
		if(a_member.getName() == null){return;}
		try {
			statement.executeUpdate("INSERT INTO Member(SSN,name,password)VALUES(" + "'" + a_member.getSSN() + "'" +  ", '" + a_member.getName() + "'" +  ", '" + a_member.getPassword() + "'" + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertBoat(model.Boat a_boat, model.Member a_member){
		if(a_boat.getType() == null){return;}
		try {
			statement.executeUpdate("INSERT INTO Boat(size,type, member_id) VALUES(" + "'" + a_boat.getSize() + "'" + ", '" + a_boat.getType() + "'" + ", '" + a_member.getId() + "'" + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertImage(model.Boat a_boat){
		if(a_boat.getImagePath() == null){return;}
		try {
			statement.executeUpdate("INSERT INTO Image(path, boat_id) VALUES(" + "'" + a_boat.getImagePath() + "'" + ", " + a_boat.getId() + ")" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createTables(){
	
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
/*	public ResultSet getCompactList() {
		rs = null;		
		try {
			rs = statement.executeQuery("SELECT Member.name AS 'member name', Member.id AS 'member id', COUNT(Boat.member_id) AS 'number of boats' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) GROUP BY Boat.member_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;	
	}*/
	
	// tillagt nu i kompletteringen
	public ArrayList<Member> getCompactList(){
		listOfMembers.clear();
		rs = null;		
		try {
			rs = statement.executeQuery("SELECT Member.name AS 'member name', Member.id AS 'member id', COUNT(Boat.member_id) AS 'number of boats' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) GROUP BY Boat.member_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				//System.out.println("Member name: " + r.getString("member name") + ", Member id: " + r.getInt("member id") + ", Number of boats: " + r.getInt("number of boats"));
				listOfMembers.add(new Member(rs.getInt("member id"), null, rs.getString("member name"), null, rs.getInt("number of boats")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfMembers;	
	}
	///////////////////////////////////////////////////////
	
	// Get verbose list (name, SSN, member id and boats with boat information of all members)		RADERA
	/*public ResultSet getVerboseList() {
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Member.name AS 'member name', Member.ssn AS 'social security number', Member.id AS 'member id', Boat.id AS 'boat id', Boat.size AS 'boat size', Boat.type AS 'boat type', Image.path AS 'image path' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) LEFT JOIN Image ON (Boat.id = Image.boat_id)");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;
	}*/
	
	// tillagt nu i kompletteringen	
	public ArrayList getVerboseList() {
		listOfMembers.clear();
		m_member.getBoats().clear();
		int currentMemberID = 0;
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Member.name AS 'member name', Member.ssn AS 'social security number', Member.id AS 'member id', Boat.id AS 'boat id', Boat.size AS 'boat size', Boat.type AS 'boat type', Image.path AS 'image path' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) LEFT JOIN Image ON (Boat.id = Image.boat_id)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			while(rs.next()){
				if(rs.getInt("member id") != currentMemberID) {
					m_member = new Member();
				}
				m_member.addBoat(rs.getInt("boat id"), rs.getInt("boat size"), rs.getString("boat type"), rs.getString("image path"));
				listOfMembers.add(new Member(rs.getInt("member id"), rs.getString("social security number"), rs.getString("member name"), null, 0, m_member.getBoats()));
				currentMemberID = rs.getInt("member id");		
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return listOfMembers;
	}
	
	
	/////////////////////////////////////////////////////
	
	// M�STE �NDRAS
	
	// Look at a specific members information 
	public ResultSet getMemberAndBoatsInformation(String SSN) {		//Radera
		rs = null;
		try {
			rs =statement.executeQuery("SELECT Member.name AS 'member name', Member.ssn AS 'social security number', Member.id AS 'member id', Member.password AS 'member password', Boat.id AS 'boat id', Boat.size AS 'boat size', Boat.type AS 'boat type', Image.path AS 'image path' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.SSN = " + "'" + SSN + "'" );
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;		
	}
	
	// tillagt nu i kompletteringen			
	public ArrayList getMemberAndBoatsInformationTest(String SSN) {	
		listOfMembers.clear();
		m_member.getBoats().clear();
		rs = null;
		try {
			rs =statement.executeQuery("SELECT Member.name AS 'member name', Member.ssn AS 'social security number', Member.id AS 'member id', Member.password AS 'member password', Boat.id AS 'boat id', Boat.size AS 'boat size', Boat.type AS 'boat type', Image.path AS 'image path' FROM Member LEFT JOIN Boat ON (Member.id = Boat.member_id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.SSN = " + "'" + SSN + "'" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			while(rs.next()){
				m_member.addBoat(rs.getInt("boat id"), rs.getInt("boat size"), rs.getString("boat type"), rs.getString("image path"));
				listOfMembers.add(new Member(rs.getInt("member id"), rs.getString("social security number"), rs.getString("member name"), null, 0, m_member.getBoats()));		
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return listOfMembers;		
	}
	
	
	// Change membersInformation 					FORTS�TT H�R!!!!!*************
	// Getting the members information from the ssn	TAR UT ALLT OM MEDLEMEN
	public ResultSet getMembersInformation(model.Member a_member) {
		listOfMembers.clear();
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Member.id AS 'member id', Member.ssn AS 'social security number', Member.name AS 'member name', Member.password AS 'member password' FROM Member WHERE Member.SSN = " + "'" + a_member.getSSN()+ "'" );  
			if(rs.isClosed()){
				return null;
			}
			a_member.setId(rs.getInt("member id"));
		} catch (SQLException e) {
			e.printStackTrace(); 
		} 
		try {
			while(rs.next()){
				//System.out.println("Member name: " + r.getString("member name") + ", Member id: " + r.getInt("member id") + ", Number of boats: " + r.getInt("number of boats"));
				listOfMembers.add(new Member(rs.getInt("member id"), rs.getString("social security number"), rs.getString("member name"), rs.getString("password"), rs.getInt("number of boats")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	// tillagt i kompletteringen 
	public ArrayList getMembersInformationTest(model.Member a_member) {
		listOfMembers.clear();
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Member.id AS 'member id', Member.ssn AS 'social security number', Member.name AS 'member name', Member.password AS 'member password' FROM Member WHERE Member.SSN = " + "'" + a_member.getSSN()+ "'" );  
			if(rs.isClosed()){
				return null;
			}
			a_member.setId(rs.getInt("member id"));
		} catch (SQLException e) {
			e.printStackTrace(); 
		} 
		try {
			while(rs.next()){
				//System.out.println("Member name: " + r.getString("member name") + ", Member id: " + r.getInt("member id") + ", Number of boats: " + r.getInt("number of boats"));
				listOfMembers.add(new Member(rs.getInt("member id"), rs.getString("social security number"), rs.getString("member name"), rs.getString("member password"), 0, null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfMembers;
	}
	////////////////////////////////////////////////////////////
	
	public void deleteMember(String SSN){
		try {
			statement.executeUpdate("DELETE FROM Member WHERE SSN =" + "'" + SSN + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void deleteBoat(int id){
		try {
			statement.executeUpdate("DELETE FROM Boat WHERE id =" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	// M�STE �NDRAS
	public ResultSet getMembersBoats(String SSN){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Boat.id, Boat.size, Boat.type, Image.path FROM Boat LEFT JOIN Member ON (Boat.member_id = Member.id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.SSN =" + "'" + SSN + "'"); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;		
	}
	
	// tillagt i kompletteringen	
	public ArrayList getMembersBoatsTest(String SSN){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Boat.id, Boat.size, Boat.type, Image.path FROM Boat LEFT JOIN Member ON (Boat.member_id = Member.id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.SSN =" + "'" + SSN + "'"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				m_member.addBoat(rs.getInt("id"), rs.getInt("size"), rs.getString("type"), rs.getString("path"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m_member.getBoats();		
	}
	
	public ResultSet getASpecificBoat(int id){	//RADERA
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Boat.id, Boat.size, Boat.type, Image.path FROM Boat LEFT JOIN Member ON (Boat.member_id = Member.id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.id =" + id);  
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;	
	}
	
	// tillagt i kompletteringen
	public ArrayList getASpecificBoatTest(int id){
		rs = null;
		try {
			rs = statement.executeQuery("SELECT Boat.id, Boat.size, Boat.type, Image.path FROM Boat LEFT JOIN Member ON (Boat.member_id = Member.id) LEFT JOIN Image ON (Boat.id = Image.boat_id) WHERE Member.id =" + id);  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				m_member.addBoat(rs.getInt("id"), rs.getInt("size"), rs.getString("type"), rs.getString("path"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m_member.getBoats();	
	}
	
	////////////////////////////////////////////////
	
	public void updateMember(model.Member a_member){
		try {
			statement.executeUpdate("UPDATE Member SET SSN ="+ "'" + a_member.getSSN() + "', name =" + "'" + a_member.getName() + "', password =" + "'" + a_member.getPassword()+ "' WHERE id =" + a_member.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateBoat(model.Boat a_boat){
		try {
			statement.executeUpdate("UPDATE Boat SET size =" + a_boat.getSize() + ", type =" + "'" + a_boat.getType() + "' WHERE id=" + a_boat.getId());
			state.executeUpdate("UPDATE Image SET path =" + "'" + a_boat.getImagePath()+ "' WHERE boat_id =" + a_boat.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
	}
	
	public void startTransaction(){
		m_db.startTransaction();
	}
	
	public void commitTransaction() throws SQLException{
		m_db.commitTransaction();
	}
	
	public void rollback(){
		m_db.rollback();
	}
	
	public void closeConnection(){
		m_db.closeConnection();
	}
	//tillagt nu i kompletteringen
	public void addMemberToList(Member a_member){
		listOfMembers.add(a_member);
	}
	public void removeAllMembersFromList(){
		listOfMembers.clear();
	}
	public ArrayList<Member> getMembers(){
		return listOfMembers;
	}
}
