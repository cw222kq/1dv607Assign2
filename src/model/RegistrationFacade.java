/**
 * 
 */
package model;

import java.sql.ResultSet;

/**
 * @author cw222kq
 *
 */
public class RegistrationFacade {

	private Member m_member;
	private Boat m_boat;
	private DAO m_dao;
	
	public RegistrationFacade() {
		
		m_member = new Member();
		m_boat = new Boat();
		m_dao = new DAO();
	}
	//Member methods
	public Member getMember(){
		return m_member;
	}
	//setters
	public void setMemberId(int valueId){
		m_member.setId(valueId);
	}
	public void setMemberSSN(String valueSSN){
		m_member.setSSN(valueSSN);
	}
	public void setMemberName(String valueName){
		m_member.setName(valueName);
	}
	public void setMemberPassword(String valuePassword){
		m_member.setPassword(valuePassword);
	}
	// getters
	public int getMemberId(){
		return m_member.getId();
	}
	public String getMemberSSN(){
		return m_member.getSSN();
	}	
	public String getMemberName(){
		return m_member.getName();
	}
	public String getMemberPassword(){
		return m_member.getPassword();
	}
	// Boat methods
	public Boat getBoat(){
		return m_boat;
	}
	// setters
	public void setBoatId(int valueId){
		m_boat.setId(valueId);
	}
	public void setBoatSize(int valueSize){
		m_boat.setSize(valueSize);
	}
	public void setType(String valueType){
		m_boat.setType(valueType);
	}
	public void setImagePath(String valueImagePath){
		m_boat.setImagePath(valueImagePath);
	}
	// getters
	public int getBoatId(){
		return m_boat.getId();
	}
	public int getBoatSize(){
		return m_boat.getSize();
	}
	public String getBoatType(){
		return m_boat.getType();
	}
	public String getBoatImagePath(){
		return m_boat.getImagePath();
	}
	// DAO methods
	public void insert(String table){ 
		m_dao.insert(m_member, m_boat, table);
		// METOD SOM SÄGER ATT COMMMITEN LYCKADES BEHÖVS !!!!!!!!!!!!!!!
	}
	public int getMemberId(String SSN) { 
		return m_dao.getMemberId(SSN);	
	}
	public int getMembersLatestAddedBoatId(int memberId){
		return m_dao.getMembersLatestAddedBoatId(memberId);
	}
	public ResultSet getCompactList() {
		return m_dao.getCompactList();
	}
	public ResultSet getVerboseList() {
		return m_dao.getVerboseList();
	}
	public ResultSet getMemberAndBoatsInformation(String SSN) {
		return m_dao.getMemberAndBoatsInformation(SSN);	
	}
	public ResultSet getMembersInformation() {
		return m_dao.getMembersInformation(m_member);
	}
	public void deleteMember(String SSN){
		m_dao.deleteMember(SSN);
	}
	public void deleteBoat(int id){
		m_dao.deleteBoat(id);	
	}
	public ResultSet getMembersBoats(String SSN){
		return m_dao.getMembersBoats(SSN);	
	}	
	public ResultSet getASpecificBoat(int id){
		return m_dao.getASpecificBoat(id);
	}
	public void updateMemberInformation(){
		m_dao.updateMemberInformation(m_member);
	}
	public void updateBoatInformation(){
		m_dao.updateBoatInformation(m_boat);
	}
	public void startTransaction(){
		m_dao.startTransaction();
	}
	public void commitTransaction(){
		m_dao.commitTransaction();
	}
	public void closeConnection(){
		m_dao.closeConnection();
	}


}
