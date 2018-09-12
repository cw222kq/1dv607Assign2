/**
 * 
 */
package model;

/**
 * @author cw222kq
 *
 */
public class Boat {

	// create variables
	private int id;
	private int size;
	private String type;
	private String path;
	
	
	// the constructor sets the variables (used when creating a new boat (auto increment id));
	public Boat(int valueSize, String valueType, String valuePath) {
		
		this.size = valueSize;
		this.type = valueType;
		this.path = valuePath;
	}
	public Boat(){}
	// setters (used when updating the information about the boat)
	// tror setId kan tas bort iom att vi aldrig sätter det själva
	public void setId(int valueId){
		this.id = valueId;
	}
	public void setSize(int valueSize){
		this.size = valueSize;
	}
	public void setType(String valueType){
		this.type = valueType;
	}
	public void setPath(String valuePath){
		this.path = valuePath;
	}
	// getters
	public int getId(){
		return this.id;
	}
	public int getSize(){
		return this.size;
	}
	public String getType(){
		return this.type;
	}
	public String getPath(){
		return this.path;
	}
}
