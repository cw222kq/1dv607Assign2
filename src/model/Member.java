/**
 * 
 */
package model;

/**
 * @author cw222kq
 *
 */
class Member {

	private int id;
	private String SSN;
	private String name;
	private String password;
		
	public Member(){}
	// setters
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
