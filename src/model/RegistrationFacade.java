/**
 * 
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author cw222kq
 *
 */
public class RegistrationFacade {

	private Member m_member;
	private Boat m_boat;
	private DAO m_dao;
	
	//tillagt nu i kompletteringen	OS�KER OM DENNA SKA LIGGA H�R ELLER I DAO
	//private ArrayList<Member> listOfMembers;
			
	//////////////////////////////////////
	
	public RegistrationFacade() {
		
		m_member = new Member();
		m_boat = new Boat();
		m_dao = new DAO();
		
		//tillagt nu i kompleteringen
		//listOfMembers = new ArrayList<Member>();
	}
	//tillagt nu i kompletteringen
	/*public void addMemberToList(Member a_member){
		listOfMembers.add(a_member);
	}
	public void removeAllMembersFromList(){
		listOfMembers.clear();
	}
	public Iterable<Member> getMembers(){
		return listOfMembers;
	}*/
	//////////////////////////////////////
	// MEMBER METHODS	
	public Object getMember(){
		return this.m_member;
	}
	//tillagt nu i kompletteringen 
	public void addBoat(int id, int size, String type, String imagePath){
		m_member.addBoat(id, size, type, imagePath);
	}
	//////////////////////////////////////
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
	
	/*public ResultSet getCompactList() {
		return m_dao.getCompactList();	RADERA
	}*/
	//tillagt nu i kompletteringen
	public ArrayList<Member> getCompactList() {
		return m_dao.getCompactList();
	}
	
	/*public ResultSet getVerboseList() {
		return m_dao.getVerboseList();
	}*/
	
	public ArrayList getVerboseList() {
		return m_dao.getVerboseList();
	}
	
	public ArrayList getMemberAndBoatsInformation(String SSN) {	//�ndrad fr�n resultset till arraylist i returntyp
		return m_dao.getMemberAndBoatsInformationTest(SSN);	
	}
	
	public ResultSet getMembersInformation() {
		return m_dao.getMembersInformation(m_member);
	}
	
	public ArrayList getMembersInformationTest() {
		return m_dao.getMembersInformationTest(m_member);
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
	
	public ArrayList getMembersBoatsTest(String SSN){
		return m_dao.getMembersBoatsTest(SSN);	
	}
	
	public ResultSet getASpecificBoat(int id){
		return m_dao.getASpecificBoat(id);
	}
	
	public ArrayList getASpecificBoatTest(int id){
		return m_dao.getASpecificBoatTest(id);
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
	
	public void commitTransaction() throws SQLException{
		m_dao.commitTransaction();
	}
	
	public void rollback(){
		m_dao.rollback();
	}
	
	public void closeConnection(){
		m_dao.closeConnection();
	}
	//tillagt nu i kompletteringen
	public void addMemberToList(Member a_member){
		m_dao.addMemberToList(a_member);
	}
	public void removeAllMembersFromList(){
		m_dao.removeAllMembersFromList();
	}
	public ArrayList<Member> getMembers(){
		return m_dao.getMembers();
	}
}
