/**
 * 
 */
package model;

/**
 * @author cw222kq
 *
 */
public class Member {

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
	// setters (used when updating the information about the member)
	// tror setId kan tas bort iom att vi aldrig sätter det själva
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
