/**
 * 
 */
package model;

/**
 * @author cw222kq
 *
 */
class Member {

	// create variables
	private int id;
	private String SSN;
	private String name;
	private String password;
	
	// the constructor sets the variables (used when creating a new member)
	public Member(String valueSSN, String valueName, String valuePassword) {
		this.SSN = valueSSN;
		this.name = valueName;
		this.password = valuePassword;
	}
	
	public Member(){}
	// setters (used when updating the information about the member)
	public void setId(int valueId){
		this.id = valueId;
	}
	
	public void setSSN(String valueSSN){
		this.SSN = valueSSN;
	}
	
	public void setName(String valueName){
		this.name = valueName;
	}
	
	public void setPassword(String valuePassword){
		this.password = valuePassword;
	}
	
	// getters
	public int getId(){
		return this.id;
	}
	
	public String getSSN(){
		return this.SSN;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPassword(){
		return this.password;
	}

}
