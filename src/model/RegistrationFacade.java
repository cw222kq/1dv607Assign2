/**
 * 
 */
package model;

import java.util.ArrayList;

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
	// MEMBER METHODS	
	// setters
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
	
	// BOAT METHODS
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
	
	public void setBoatType(String valueType){
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
	
	// DAO METHODS
	public void insertMember(){
		m_dao.insertMember(m_member);
	}
	
	public void insertBoat(){
		m_dao.insertBoat(m_boat, m_member);
	}
	
	public void insertImage(){
		m_dao.insertImage(m_boat);
	}
	
	public int getMemberId(String SSN) { 
		return m_dao.getMemberId(SSN);	
	}
	
	public int getMembersLatestAddedBoatId(int memberId){
		return m_dao.getMembersLatestAddedBoatId(memberId);
	}
	
	// changed by me referred to as 1 in the changes.txt
	public ArrayList<Object> getCompactList() {
		return m_dao.getCompactList();
	}
	
	// changed by me referred to as 1 in the changes.txt
	public ArrayList<Object> getVerboseList() {
		return m_dao.getVerboseList();
	}
	
	// changed by me referred to as 1 in the changes.txt
	public ArrayList<Object> getMemberAndBoatsInformation(String SSN) {	
		return m_dao.getMemberAndBoatsInformation(SSN);	
	}
	
	// changed by me referred to as 1 in the changes.txt
	public ArrayList<Object> getMembersInformation() {
		return m_dao.getMembersInformation(m_member);
	}
	
	// changed by me referred to as 1 in the changes.txt
	public ArrayList<Object> getMembersBoats(String SSN){
		return m_dao.getMembersBoats(SSN);	
	}
	
	// changed by me referred to as 1 in the changes.txt
	public ArrayList<Object> getASpecificBoat(int id){
		return m_dao.getASpecificBoat(id);
	}
	
	public void deleteMember(String SSN){
		m_dao.deleteMember(SSN);
	}
	
	public void deleteBoat(int id){
		m_dao.deleteBoat(id);	
	}
	
	public void updateMember(){
		m_dao.updateMember(m_member);
	}
	
	public void updateBoat(){
		m_dao.updateBoat(m_boat);
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
	
	// changed by me referred to as 4 in the changes.txt
	public void setEventSucceededToFalse(){
		m_dao.setEventSucceededToFalse();
	}
	
	// changed by me referred to as 4 in the changes.txt
	public boolean getEventSucceeded(){
		return m_dao.getEventSucceeded();	
	}
}
