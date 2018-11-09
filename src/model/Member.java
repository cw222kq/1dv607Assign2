/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author cw222kq
 *
 */
public class Member {

	private int id;
	private String SSN;
	private String name;
	private String password;
	private int numberOfBoats;
	
	//tillagt nu i kompletteringen
	private ArrayList<Object> listOfBoats = new ArrayList<Object>();
		
	public Member(){
		
		//tillagt nu i kompletteringen
		listOfBoats = new ArrayList<Object>();
	}
	//ctor tillagt nu i kompletteringen
	public Member(int id, String SSN, String name, String password, int numberOfBoats, ArrayList<Object> arr){
		
		this.id = id;
		this.SSN = SSN;
		this.name = name;
		this.password = password;
		this.numberOfBoats = numberOfBoats;
		
		this.listOfBoats = arr;
		
		
	}

	public void addBoat(int id, int size, String type, String imagePath){
		listOfBoats.add(new Boat(id, size, type, imagePath));
	}
	public ArrayList<Object> getBoats(){
		return listOfBoats;
	}
	
	///////////////////////////////////////
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
	
	// tillagt nu i kompletteringen
	public void setNumberOfBoats(int valueNumberOfBoats){
		this.numberOfBoats = valueNumberOfBoats;
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
	
	// tillagt nu i kompletteringen
	public int getNumberOfBoats(){
		return this.numberOfBoats;
	}

}
