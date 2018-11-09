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
	
	// changed by me referred to as 1 in the changes.txt
	private ArrayList<Object> listOfBoats = new ArrayList<Object>();
		
	public Member(){
		
		// changed by me referred to as 1 in the changes.txt
		listOfBoats = new ArrayList<Object>();
	}
	// changed by me referred to as 2 in the changes.txt
	public Member(int id, String SSN, String name, String password, int numberOfBoats, ArrayList<Object> arr){
		
		this.id = id;
		this.SSN = SSN;
		this.name = name;
		this.password = password;
		this.numberOfBoats = numberOfBoats;
		
		this.listOfBoats = arr;
		
		
	}
	
	// changed by me referred to as 1 in the changes.txt
	public void addBoat(int id, int size, String type, String imagePath){
		listOfBoats.add(new Boat(id, size, type, imagePath));
	}
	// changed by me referred to as 1 in the changes.txt
	public ArrayList<Object> getBoats(){
		return listOfBoats;
	}
	
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
	
	// changed by me referred to as 1 in the changes.txt
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
	
	// changed by me referred to as 1 in the changes.txt
	public int getNumberOfBoats(){
		return this.numberOfBoats;
	}

}
